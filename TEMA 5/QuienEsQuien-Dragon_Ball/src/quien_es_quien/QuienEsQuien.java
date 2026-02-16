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
            // Cargo la vista desde el archivo FXML
            Parent root = FXMLLoader.load(getClass().getResource("/quien_es_quien/vista/QuienEsQuien.fxml"));

            Scene scene = new Scene(root);

            // Añado la hoja de estilos personalizada
            String cssPath = getClass().getResource("/quien_es_quien/styles/quienesquien.css").toExternalForm();
            scene.getStylesheets().add(cssPath);

            // Título de la ventana
            primaryStage.setTitle("En Busca del Ki - Dragon Ball Discovery");

            // Intento poner el icono de la aplicación
            try {
                java.net.URL iconUrl = getClass().getResource("/quien_es_quien/vista/icon.png");
                if (iconUrl != null) {
                    primaryStage.getIcons().add(new Image(iconUrl.toExternalForm()));
                } else {
                    // Si no encuentra el icono no pasa nada, sigue sin él
                    System.err.println("No se ha encontrado el icono en /quien_es_quien/vista/icon.png");
                }
            } catch (Exception e) {
                System.err.println("Error al cargar el icono: " + e.getMessage());
            }

            // Muestro la escena
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
