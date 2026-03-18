package quien_es_quien.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AyudaControlador {

    @FXML
    private ListView<String> listaTemas;

    @FXML
    private Label lblTitulo;

    @FXML
    private TextFlow flowContenido;

    @FXML
    public void initialize() {
        // Añadimos los temas a la lista lateral
        listaTemas.getItems().addAll("Reglas del Juego", "Preguntas Frecuentes", "Acerca de");

        // Detectamos cuando el usuario selecciona un tema
        listaTemas.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            cargarContenido(newVal);
        });

        // Seleccionamos el primero por defecto para que no salga vacío
        listaTemas.getSelectionModel().selectFirst();
    }

    // Método encargado de mostrar el texto con formato según el tema
    private void cargarContenido(String tema) {
        if (tema == null)
            return;

        lblTitulo.setText(tema);
        flowContenido.getChildren().clear();

        switch (tema) {
            case "Reglas del Juego":
                agregarTexto("Objetivo:\n", Color.DARKORANGE, true, 16);
                agregarTexto("Adivinar el Personaje Secreto antes de quedarte sin intentos.\n\n", Color.BLACK, false,
                        14);

                agregarTexto("Mecánica:\n", Color.DARKORANGE, true, 16);
                agregarTexto("1. Tienes ", Color.BLACK, false, 14);
                agregarTexto("3 oportunidades", Color.RED, true, 14);
                agregarTexto(" para hacer preguntas clave.\n", Color.BLACK, false, 14);
                agregarTexto("2. Al preguntar, el sistema ", Color.BLACK, false, 14);
                agregarTexto("descartará automáticamente", Color.BLUE, true, 14);
                agregarTexto(" a los personajes que no coincidan.\n\n", Color.BLACK, false, 14);

                agregarTexto("Victoria/Derrota:\n", Color.DARKORANGE, true, 16);
                agregarTexto(
                        "Cuando gastes tus 3 preguntas, o si te sientes con suerte, haz clic en un personaje. Si aciertas, ¡GANAS! Si fallas... perderás la partida.",
                        Color.BLACK, false, 14);
                break;

            case "Preguntas Frecuentes":
                agregarTexto("P: ¿Qué pasa si gasto mis 3 preguntas?\n", Color.DARKBLUE, true, 15);
                agregarTexto(
                        "R: Ya no podrás interrogar al oráculo. Tendrás que jugártela a una carta con la información que tengas.\n\n",
                        Color.BLACK, false, 14);

                agregarTexto("P: ¿Quiénes cuentan como 'Dios'?\n", Color.DARKBLUE, true, 15);
                agregarTexto("R: Personajes divinos como Beerus, Whis o Zamasu.\n\n", Color.BLACK, false, 14);

                agregarTexto("P: ¿Piccolo es verde?\n", Color.DARKBLUE, true, 15);
                agregarTexto("R: Sí, es un namekiano, así que cuenta como verde.", Color.BLACK, false, 14);
                break;

            case "Acerca de":
                agregarTexto("En Busca del Ki\n", Color.DARKORANGE, true, 18);
                agregarTexto("Versión 1.0 - Super Saiyan Edition\n\n", Color.GRAY, true, 12);
                agregarTexto("Desarrollado como práctica de JavaFX.\n", Color.BLACK, false, 14);
                agregarTexto("Tributo a la obra de Akira Toriyama.", Color.BLACK, false, 14);
                break;
        }
    }

    // Utilidad para añadir trozos de texto con estilo al flujo
    private void agregarTexto(String texto, Color color, boolean negrita, double tamano) {
        Text t = new Text(texto);
        t.setFill(color);
        t.setFont(Font.font("System", negrita ? FontWeight.BOLD : FontWeight.NORMAL, tamano));
        flowContenido.getChildren().add(t);
    }
}
