package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Vista1Controller implements Initializable {

    @FXML
    private Button buton1;
    @FXML
    private Label texto1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(ActionEvent event) {
        texto1.setText("has pulsado el boton");
    }
    
}
