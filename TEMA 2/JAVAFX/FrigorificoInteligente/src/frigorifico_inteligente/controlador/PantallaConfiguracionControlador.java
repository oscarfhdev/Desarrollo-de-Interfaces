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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PantallaConfiguracionControlador implements Initializable {

    @FXML
    private Label labelHora;
    @FXML
    private ImageView botonAtras;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarFechayHora();
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
