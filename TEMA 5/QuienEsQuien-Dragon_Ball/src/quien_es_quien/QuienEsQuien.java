package quien_es_quien;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class QuienEsQuien extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carga de la interfaz gráfica
            Parent root = FXMLLoader.load(getClass().getResource("/quien_es_quien/vista/QuienEsQuien.fxml"));

            Scene scene = new Scene(root);
            
            // Hoja de estilos
            String cssPath = getClass().getResource("/quien_es_quien/styles/quienesquien.css").toExternalForm();
            scene.getStylesheets().add(cssPath);

            // Configuración de la ventana
            primaryStage.setTitle("En Busca del Ki - Dragon Ball Discovery");

            // Icono de la ventana
            try {
                java.net.URL iconUrl = getClass().getResource("/quien_es_quien/vista/icon.png");
                if (iconUrl != null) {
                    primaryStage.getIcons().add(new Image(iconUrl.toExternalForm()));
                }
            } catch (Exception e) {
                // Si falla el icono no es crítico, seguimos adelante
                System.err.println("No se pudo cargar el icono: " + e.getMessage());
            }

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
