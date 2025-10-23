package frigorifico_inteligente.controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<?> tablaAlimentos;
    @FXML
    private TableColumn<?, ?> columnaTablaAlimento;
    @FXML
    private TableColumn<?, ?> columnaTablaCantidad;
    @FXML
    private TableColumn<?, ?> columnaTablaCategoria;
    @FXML
    private TextField fieldNombre;
    @FXML
    private Spinner<Integer> spinnerCantidad;
    @FXML
    private ComboBox<?> comboboxCategoria;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarFechayHora();
        inicializarSpinnerCantidad();
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
}
