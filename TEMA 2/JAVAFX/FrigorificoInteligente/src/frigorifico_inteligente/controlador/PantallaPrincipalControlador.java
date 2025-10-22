
package frigorifico_inteligente.controlador;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarFechayHora();
        asociarLabelSlide();
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

    @FXML
    private void apagarFrigorifico(MouseEvent event) {
        System.exit(0);
    }

    
}
