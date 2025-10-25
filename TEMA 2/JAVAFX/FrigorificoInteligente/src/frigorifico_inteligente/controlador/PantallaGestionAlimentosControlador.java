package frigorifico_inteligente.controlador;

import frigorifico_inteligente.DAO.AlimentoDAO;
import frigorifico_inteligente.modelo.Alimento;
import frigorifico_inteligente.modelo.Categoria;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PantallaGestionAlimentosControlador implements Initializable {

    @FXML
    private Label labelHora;
    @FXML
    private ImageView botonAtras;
    @FXML
    private TableView<Alimento> tablaAlimentos;
    @FXML
    private TableColumn<Alimento, String> columnaTablaAlimento;
    @FXML
    private TableColumn<Alimento, Integer> columnaTablaCantidad;
    @FXML
    private TableColumn<Alimento, Categoria> columnaTablaCategoria;
    @FXML
    private TextField fieldNombre;
    @FXML
    private Spinner<Integer> spinnerCantidad;
    @FXML
    private ComboBox<Categoria> comboboxCategoria;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Label lblError;


    private final AlimentoDAO dao = new AlimentoDAO(); // DAO (archivo: alimentos.txt)
    private final ObservableList<Alimento> listaAlimentos = FXCollections.observableArrayList();

    // Registro seleccionado actualmente (null si no hay)
    private Alimento seleccionado = null;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarFechayHora();
        inicializarSpinnerCantidad();
        inicializarComboBoxCategoria();
        configurarColumnasTabla();
        configurarSeleccionTabla();
        cargarAlimentosDesdeDAO();
        configurarVisibilidadBotones(false, false, false, true); // al inicio solo Guardar visible
    }    

    private void inicializarSpinnerCantidad() {
        // Configurar el campo de cantidad
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        spinnerCantidad.setValueFactory(valueFactory);
    }    

    public void inicializarFechayHora(){
        // Formato de fecha y hora
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Evento que se ejecuta cada segundo
        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime dateTimeActual = LocalDateTime.now();
            labelHora.setText(formatoHora.format(dateTimeActual));
        }));
        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();
    }
    
    private void inicializarComboBoxCategoria() {
    comboboxCategoria.getItems().setAll(Categoria.values());
    }
    
    private void configurarColumnasTabla() {
    // PropertyValueFactory llama a getNombre(), getCantidad(), getCategoria()
    columnaTablaAlimento.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    columnaTablaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    columnaTablaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

    tablaAlimentos.setItems(listaAlimentos);
    tablaAlimentos.setPlaceholder(new Label("No hay alimentos registrados aún"));
    }
    
    
    private void configurarSeleccionTabla() {
        tablaAlimentos.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                seleccionado = newSel;
                fieldNombre.setText(newSel.getNombre());
                spinnerCantidad.getValueFactory().setValue(newSel.getCantidad());
                comboboxCategoria.setValue(newSel.getCategoria());
                configurarVisibilidadBotones(true, true, true, false); // mostrar actualizar/eliminar/limpiar
            }
        });
    }
        
    private void cargarAlimentosDesdeDAO() {
        // cargar y volcar en ObservableList
        List<Alimento> leidos = dao.obtenerAlimentos();
        listaAlimentos.clear();
        listaAlimentos.addAll(leidos);
    }
    
    private void configurarVisibilidadBotones(boolean mostrarActualizar, boolean mostrarEliminar, boolean mostrarLimpiar, boolean mostrarGuardar) {
        btnActualizar.setVisible(mostrarActualizar);
        btnEliminar.setVisible(mostrarEliminar);
        btnLimpiar.setVisible(mostrarLimpiar);
        btnGuardar.setVisible(mostrarGuardar);
    }
    
   
    
    @FXML
    private void apagarFrigorifico(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void irPantallaPrincipal(MouseEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/frigorifico_inteligente/vista/pantallaPrincipal.fxml"));

        Parent root = loader.load();
        Scene escena = new Scene(root);
        
        // Obtener el Stage actual desde el botón
        Stage stage = (Stage) botonAtras.getScene().getWindow();

        // Reemplazar la escena actual
        stage.setScene(escena);
        stage.setTitle("Frigorífico Inteligente");  
    }

    @FXML
    private void actualizarAlimento(MouseEvent event) {
         if (seleccionado == null) {
            mostrarError("No hay alimento seleccionado para actualizar.");
            return;
        }

        String nombre = fieldNombre.getText().trim();
        Integer cantidad = spinnerCantidad.getValue();
        Categoria categoria = comboboxCategoria.getValue();
 
        // Validaciones
        if (!validarCampos()){
            return;
        }

        // Actualizar el objeto
        seleccionado.setNombre(nombre);
        seleccionado.setCantidad(cantidad);
        seleccionado.setCategoria(categoria);

        dao.actualizarAlimento(seleccionado);
        cargarAlimentosDesdeDAO();
        limpiarCampos();
    }

    @FXML
    private void guardarAlimento(MouseEvent event) {
        String nombre = fieldNombre.getText().trim();
        Integer cantidad = spinnerCantidad.getValue();
        Categoria categoria = comboboxCategoria.getValue();

        // Validaciones
        if (!validarCampos()){
            return;
        }

        // Crear objeto Alimento y guardarlo en DAO
        Alimento nuevo = new Alimento(nombre, cantidad, categoria);
        dao.guardarAlimento(nuevo);
        listaAlimentos.add(nuevo); // añadir a la tabla
        limpiarCampos();
    }

    @FXML
    private void eliminarAlimento(MouseEvent event) {
        if (seleccionado == null) {
            mostrarError("No hay alimento seleccionado para eliminar.");
            return;
        }

        // Confirmación opcional
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de eliminación");
        alert.setHeaderText("¿Desea eliminar el alimento seleccionado?");
        alert.setContentText(seleccionado.getNombre());
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                dao.eliminarAlimento(seleccionado);
                listaAlimentos.remove(seleccionado);
                limpiarCampos();
            }
    });
    }

    @FXML
    private void limpiarCampos() {
        fieldNombre.clear();
        spinnerCantidad.getValueFactory().setValue(1);
        comboboxCategoria.setValue(null);
        tablaAlimentos.getSelectionModel().clearSelection();
        seleccionado = null;
        lblError.setVisible(false);
        configurarVisibilidadBotones(false, false, false, true); // solo guardar visible
    }
   
    private boolean validarCampos() {
        String nombre = fieldNombre.getText().trim();
        if(nombre.isEmpty()) {
            mostrarError("El nombre del alimento no puede estar vacío");
            return false; 
        }
        if(spinnerCantidad.getValue() <= 0) { 
            mostrarError("La cantidad debe ser mayor que 0");
            return false; 
        }
        if(comboboxCategoria.getValue() == null) { 
            mostrarError("Seleccione una categoría"); 
            return false; 
        }
        return true;
    }
    
    private void mostrarError(String mensaje) {
    lblError.setText(mensaje);
    lblError.setVisible(true);
}

}
