
package frigorifico_inteligente.controlador;

import frigorifico_inteligente.DAO.AlimentoDAO;
import frigorifico_inteligente.modelo.Alimento;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PantallaPrincipalControlador implements Initializable {

    @FXML
    private Label labelHora;
    @FXML
    private Label labelFecha;
    @FXML
    private Slider slideTemperatura;
    @FXML
    private Label labelTemperatura;
    @FXML
    private Label labelPorcentajeCalidad;
    @FXML
    private ImageView imgTabla;
    @FXML
    private ImageView imagenProgreso;
    @FXML
    private Label textoAuxiliar;


    private final AlimentoDAO alimentoDAO = new AlimentoDAO(); // DAO para leer el archivo

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarFechayHora();
        asociarLabelSlide();
        actualizarIndiceCalidad();
    }    

    private void asociarLabelSlide() {
        labelTemperatura.textProperty().bind(
    slideTemperatura.valueProperty().asString("%.1f " + "º")
        );
    }
    
    public void inicializarFechayHora(){
        // Formato de fecha y hora
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Evento que se ejecuta cada segundo
        Timeline reloj = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime dateTimeActual = LocalDateTime.now();
            labelHora.setText(formatoHora.format(dateTimeActual));
            labelFecha.setText(formatoFecha.format(dateTimeActual));
        }));
        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();
    }
    
    
        public void actualizarIndiceCalidad() {
        List<Alimento> lista = alimentoDAO.obtenerAlimentos();

        if (lista == null || lista.isEmpty()) {
            labelPorcentajeCalidad.setText("--");
            imagenProgreso.setImage(null); // o una imagen por defecto
            textoAuxiliar.setText("Sin alimentos registrados");
            return;
        }

        // calcular media ponderada: sum(cantidad * score) / sum(cantidad) * 100
        double numerador = 0.0;
        double denominador = 0.0;
        for (Alimento a : lista) {
            int unidades = Math.max(0, a.getCantidad()); // seguridad
            double score = a.getCategoria().getCalificacion(); 
            numerador += unidades * score;
            denominador += unidades;
        }

        double porcentaje;
        if (denominador == 0) {
            porcentaje = 0.0;
        } else {
            porcentaje = (numerador / denominador) * 100.0;
        }

        // redondear a entero para mostrar
        int pct = (int) Math.round(porcentaje);
        labelPorcentajeCalidad.setText(pct + "%");

        // actualizar imagen según rango
        String rutaImagen = seleccionarImagenPorPorcentaje(pct);
        try {
            Image img = new Image(getClass().getResourceAsStream("/frigorifico_inteligente/imagenes/indice_calidad/" + rutaImagen));
            imagenProgreso.setImage(img);
        } catch (Exception e) {
            imagenProgreso.setImage(null);
        }
    }

    
     // Dado un porcentaje (0..100) devuelve el nombre del fichero que corresponde.
    private String seleccionarImagenPorPorcentaje(int porcentaje) {
        if (porcentaje <= 12) return "0-12.png";
        if (porcentaje <= 24) return "13-24.png";
        if (porcentaje <= 37) return "24-37.png";
        if (porcentaje <= 49) return "38-49.png";
        if (porcentaje <= 60) return "49-60.png";
        if (porcentaje <= 74) return "61-74.png";
        if (porcentaje <= 85) return "74-85.png";
        return "86-100.png"; // pct > 85
    }
    

    @FXML
    private void apagarFrigorifico(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void irGestionAlimentos(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frigorifico_inteligente/vista/pantallaGestionAlimentos.fxml"));

        Parent root = loader.load();
        Scene escena = new Scene(root);
        
        // Obtener el Stage actual desde el botón
        Stage stage = (Stage) imgTabla.getScene().getWindow();

        // Reemplazar la escena actual
        stage.setScene(escena);
        stage.setTitle("Frigorífico Inteligente - Gestión de Alimentos");  
    }

    @FXML
    private void irConfiguracion(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frigorifico_inteligente/vista/pantallaConfiguracion.fxml"));

        Parent root = loader.load();
        Scene escena = new Scene(root);
        
        // Obtener el Stage actual desde el botón
        Stage stage = (Stage) imgTabla.getScene().getWindow();

        // Reemplazar la escena actual
        stage.setScene(escena);
        stage.setTitle("Frigorífico Inteligente - Configuración");  
    }

    
}
