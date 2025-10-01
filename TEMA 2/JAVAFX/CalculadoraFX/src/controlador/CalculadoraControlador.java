
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CalculadoraControlador implements Initializable {

    @FXML
    private Label textoPantalla;
    @FXML
    private Button boton7;
    @FXML
    private Button boton8;
    @FXML
    private Button boton9;
    @FXML
    private Button boton4;
    @FXML
    private Button boton5;
    @FXML
    private Button boton6;
    @FXML
    private Button boton3;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton0;
    @FXML
    private Button botonIgual;
    @FXML
    private Button botonMenos;
    @FXML
    private Button botonMas;
    @FXML
    private Button botonX;
    @FXML
    private Button botonDividir;
    @FXML
    private Button botonReseteer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void escribir3(MouseEvent event) {
        escribirCaracteres("3");
    }

    @FXML
    private void escribir1(MouseEvent event) {
        escribirCaracteres("1");
    }

    @FXML
    private void escribir2(MouseEvent event) {
        escribirCaracteres("2");
    }

    @FXML
    private void escribir0(MouseEvent event) {
        escribirCaracteres("0");
    }

    @FXML
    private void escribirMenos(MouseEvent event) {
        escribirCaracteres("-");
    }

    @FXML
    private void escribirMas(MouseEvent event) {
        escribirCaracteres("+");
    }

    @FXML
    private void escribirMultiplicacion(MouseEvent event) {
        escribirCaracteres("*");
    }

    @FXML
    private void escribirDivision(MouseEvent event) {
        escribirCaracteres("/");
    }

    @FXML
    private void reiniciarPantalla(MouseEvent event) {
        textoPantalla.setText("");
    }
    
    
    @FXML
    private void ejecutarOperacion(MouseEvent event) {
         System.out.println((textoPantalla.getText()));
        
        if (textoPantalla.getText().isEmpty()) {
            System.out.println("Ingresa dígitos para operar");
            return;
        }
        
        if(textoPantalla.getText().contains("+")){
           String[] sumaSeparada = textoPantalla.getText().split("\\+");
           int suma = Integer.parseInt(sumaSeparada[0]) + Integer.parseInt(sumaSeparada[1]);
           textoPantalla.setText(String.valueOf(suma));
        }
        else if (textoPantalla.getText().contains("-")) {
            String[] sumaSeparada = textoPantalla.getText().split("-");
            int resta = Integer.parseInt(sumaSeparada[0]) - Integer.parseInt(sumaSeparada[1]);
            textoPantalla.setText(String.valueOf(resta));
        }
        else if (textoPantalla.getText().contains("/")) {
            String[] sumaSeparada = textoPantalla.getText().split("\\/");
            if(Integer.parseInt(sumaSeparada[0]) < Integer.parseInt(sumaSeparada[1]) || Integer.parseInt(sumaSeparada[1]) == 0){
                textoPantalla.setText("Error");
                return;
            }
            int division = Integer.parseInt(sumaSeparada[0]) / Integer.parseInt(sumaSeparada[1]);

            textoPantalla.setText(String.valueOf(division));
        }
        else if (textoPantalla.getText().contains("*")) {
            String[] sumaSeparada = textoPantalla.getText().split("\\*");
            int multiplicacion = Integer.parseInt(sumaSeparada[0]) * Integer.parseInt(sumaSeparada[1]);
            textoPantalla.setText(String.valueOf(multiplicacion));
        }
    }
    
    
    private void escribirCaracteres(String caracteres){
        textoPantalla.setText(textoPantalla.getText() + caracteres);
    }

}
