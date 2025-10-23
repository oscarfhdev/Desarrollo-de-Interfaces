package frigorifico_inteligente;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class FrigorificoInteligente extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/frigorifico_inteligente/vista/pantallaPrincipal.fxml"));
            
            // Cargo el scene
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Frigorifico Inteligente");
            // Seteo la scene y la muestro
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
