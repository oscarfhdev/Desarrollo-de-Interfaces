package navegacion_automovil.controlador;

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

public class PantallaPrincipalControlador implements Initializable {

    @FXML
    private Label labelHora;
    @FXML
    private ImageView iconoTelefono;
    @FXML
    private ImageView iconoMaps;
    @FXML
    private ImageView iconoSpotify;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarHora();
    }    

    @FXML
    private void cerrarApp(MouseEvent event) {
       System.exit(0);
    }

    @FXML
    private void abrirTelefono(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/navegacion_automovil/vista/pantallaTelefono.fxml"));

        Parent root = loader.load();
        Scene escena = new Scene(root);

        // Obtener el Stage actual desde el botón
        Stage stage = (Stage) iconoTelefono.getScene().getWindow();

        // Reemplazar la escena actual
        stage.setScene(escena);
        stage.setTitle("Android Auto - Teléfono");  
    }

    @FXML
    private void abrirMaps(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/navegacion_automovil/vista/pantallaMaps.fxml"));

        Parent root = loader.load();
        Scene escena = new Scene(root);

        // Obtener el Stage actual desde el botón
        Stage stage = (Stage) iconoMaps.getScene().getWindow();

        // Reemplazar la escena actual
        stage.setScene(escena);
        stage.setTitle("Android Auto - Maps");  
        
    }

    @FXML
    private void abrirSpotify(MouseEvent event) throws IOException {
        System.out.println("entra");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/navegacion_automovil/vista/pantallaSpotify.fxml"));

        Parent root = loader.load();
        Scene escena = new Scene(root);

        // Obtener el Stage actual desde el botón
        Stage stage = (Stage) iconoMaps.getScene().getWindow();

        // Reemplazar la escena actual
        stage.setScene(escena);
        stage.setTitle("Android Auto - Spotify"); 
    }
    
    public void inicializarHora() {
        // Establecemos el formato de la hora
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        
        // Aquí creamos un timeline para que cada 1 segundo se cambie la hora, con la del equipo
        Timeline reloj = new Timeline(
            new KeyFrame(Duration.seconds(1), e ->
                labelHora.setText(LocalDateTime.now().format(formatoHora))
            )
        );
        reloj.setCycleCount(Timeline.INDEFINITE); // Se debe de repetir hasta la finalización del programa
        reloj.play(); // Le decimos que comience
    }

    
    
}
