package navegacion_automovil.controlador;

import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PantallaMapsControlador implements Initializable {

    @FXML
    private Label labelHora;
    @FXML
    private ImageView iconoCasa;
    @FXML
    private Pane panelMapa;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarHora();
        inicializarMapa();
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

    private void inicializarMapa() {
        MapView mapView = new MapView();

        // Configuración mínima
        mapView.initialize(Configuration.builder()
                .showZoomControls(false) // sin botones de zoom
                .build());

        // Ajustar tamaño al Pane
        mapView.prefWidthProperty().bind(panelMapa.widthProperty());
        mapView.prefHeightProperty().bind(panelMapa.heightProperty());

        // Añadir MapView al Pane
        panelMapa.getChildren().add(mapView);

        // Centrar el mapa en tus coordenadas
        double lat = 37.699583;   // 37°41'58.5"N
        double lon = -5.274361;   // 5°16'27.7"W
        mapView.setCenter(new Coordinate(lat, lon));

        // Zoom inicial
        mapView.setZoom(14);
    }
}
