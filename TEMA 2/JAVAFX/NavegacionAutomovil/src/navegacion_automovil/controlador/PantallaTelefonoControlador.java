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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PantallaTelefonoControlador implements Initializable {

    @FXML
    private Label labelHora;
    @FXML
    private ImageView iconoCasa;
    @FXML
    private TextField marcadorNumeros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarHora();
    }    

    @FXML
    private void cerrarApp(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void irPantallaPrincipal(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/navegacion_automovil/vista/pantallaPrincipal.fxml"));

        Parent root = loader.load();
        Scene escena = new Scene(root);

        // Obtener el Stage actual desde el botón
        Stage stage = (Stage) iconoCasa.getScene().getWindow();

        // Reemplazar la escena actual
        stage.setScene(escena);
        stage.setTitle("Android Auto");
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

    @FXML
    private void escribir2(MouseEvent event) {
        escribirCaracteres("2");

    }

    @FXML
    private void escribir1(MouseEvent event) {
        escribirCaracteres("1");
    }

    @FXML
    private void escribir3(MouseEvent event) {
        escribirCaracteres("3");
    }

    @FXML
    private void escribir4(MouseEvent event) {
        escribirCaracteres("4");
    }

    @FXML
    private void escribir5(MouseEvent event) {
        escribirCaracteres("5");
    }

    @FXML
    private void escribir6(MouseEvent event) {
        escribirCaracteres("6");
    }

    @FXML
    private void escribir7(MouseEvent event) {
        escribirCaracteres("7");
    }

    @FXML
    private void escribir8(MouseEvent event) {
        escribirCaracteres("8");
    }

    @FXML
    private void escribir9(MouseEvent event) {
        escribirCaracteres("9");
    }

    @FXML
    private void escribirAsterisco(MouseEvent event) {
        escribirCaracteres("*");
    }

    @FXML
    private void escribir0(MouseEvent event) {
        escribirCaracteres("0");
    }

    @FXML
    private void escribirAlmohadilla(MouseEvent event) {
        escribirCaracteres("#");
    }

    @FXML
    private void eliminarUltimo(MouseEvent event) {
    String textoActual = marcadorNumeros.getText();

    if (textoActual != null && textoActual.length() > 0) {
        marcadorNumeros.setText(textoActual.substring(0, textoActual.length() - 1));
        }
    }
    
    private void escribirCaracteres(String caracter){
        marcadorNumeros.setText(marcadorNumeros.getText() + caracter);
    }
}
