package quien_es_quien.controlador;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import quien_es_quien.modelo.BaseDatos;
import quien_es_quien.modelo.Personaje;

public class QuienEsQuienControlador {

    // Elementos de la vista enlazados con FXML
    @FXML
    private GridPane gridPersonajes;

    @FXML
    private ComboBox<String> comboPreguntas;

    @FXML
    private TextArea txtLog;

    // Variables internas del juego
    private List<Personaje> tablero;
    private Personaje personajeOculto;
    private Random random = new Random();

    // Método que se ejecuta al iniciar la ventana
    @FXML
    public void initialize() {
        cargarDatos();
        prepararInterfaz();
    }

    // Carga los personajes y elige uno al azar
    private void cargarDatos() {
        tablero = BaseDatos.getPersonajes();
        // Elegimos al personaje que el jugador debe adivinar
        personajeOculto = tablero.get(random.nextInt(tablero.size()));
        System.out.println("DEBUG (Chivato): El personaje oculto es " + personajeOculto.getNombre());
    }

    // Configura la cuadrícula y los componentes visuales
    private void prepararInterfaz() {
        // Limpiamos la grid por si recargamos partida
        gridPersonajes.getChildren().clear();

        int col = 0;
        int row = 0;

        // Recorremos la lista para crear una tarjeta por cada personaje
        for (Personaje p : tablero) {
            VBox celda = crearCeldaPersonaje(p);
            gridPersonajes.add(celda, col, row);

            // Controlamos las columnas (5 columnas)
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }

        // Añadimos las preguntas posibles al desplegable
        comboPreguntas.getItems().clear();
        comboPreguntas.getItems().addAll(
                "¿Es un Saiyan?",
                "¿Es un Villano?",
                "¿Tiene pelo?",
                "¿Es verde?",
                "¿Es un Androide?",
                "¿Es Terrícola?",
                "¿Es un Dios/Deidad?");

        // Mensaje inicial
        txtLog.setText("¡Bienvenido a 'En Busca del Ki'! He sentido el Ki de un personaje oculto...");
    }

    // Crea la representación visual (tarjeta) de un personaje
    private VBox crearCeldaPersonaje(Personaje p) {
        VBox vbox = new VBox();
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setSpacing(5);
        vbox.setPadding(new javafx.geometry.Insets(5));
        vbox.getStyleClass().add("character-card"); // Clase CSS para estilo
        vbox.setPrefSize(160, 180);

        // Configuración de la imagen
        ImageView imgView = new ImageView();
        try {
            // Ruta relativa a la carpeta de imágenes en el paquete vista
            String imagePath = "/quien_es_quien/vista/imagenes/" + p.getRutaImagen();
            java.net.URL imgURL = getClass().getResource(imagePath);
            if (imgURL != null) {
                imgView.setImage(new Image(imgURL.toExternalForm()));
            } else {
                // Si falla, aquí podríamos poner una imagen por defecto
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + p.getRutaImagen());
        }
        imgView.setFitHeight(120);
        imgView.setFitWidth(120);
        imgView.setPreserveRatio(true);

        // Etiqueta con el nombre
        Label lblNombre = new Label(p.getNombre());
        lblNombre.getStyleClass().add("character-name");

        vbox.getChildren().addAll(imgView, lblNombre);

        // Evento de ratón: al hacer click, descartamos el personaje
        vbox.setOnMouseClicked(e -> {
            onPersonajeClick(vbox, p);
        });

        return vbox;
    }

    // Lógica para descartar (oscurecer) un personaje
    private void onPersonajeClick(VBox nodo, Personaje p) {
        // Si no está ya descartado, lo marcamos
        if (!nodo.getStyleClass().contains("character-card-disabled")) {
            nodo.getStyleClass().add("character-card-disabled"); // Aplicamos estilo CSS de deshabilitado
            nodo.setDisable(true); // Evita mas clicks
            txtLog.appendText("\nHas descartado a " + p.getNombre());
        }
    }

    // Acción del botón 'Preguntar'
    @FXML
    private void onPreguntar() {
        String pregunta = comboPreguntas.getValue();
        if (pregunta == null) {
            txtLog.appendText("\n¡Concentra tu Ki! Debes seleccionar una pregunta.");
            return;
        }

        boolean respuesta = false;

        // Comprobamos la característica según la pregunta
        switch (pregunta) {
            case "¿Es un Saiyan?":
                respuesta = personajeOculto.isEsSaiyan();
                break;
            case "¿Es un Villano?":
                respuesta = personajeOculto.isEsVillano();
                break;
            case "¿Tiene pelo?":
                respuesta = personajeOculto.isTienePelo();
                break;
            case "¿Es verde?":
                respuesta = personajeOculto.isEsVerde();
                break;
            case "¿Es un Androide?":
                respuesta = personajeOculto.isEsAndroide();
                break;
            case "¿Es Terrícola?":
                respuesta = personajeOculto.isEsTerricola();
                break;
            case "¿Es un Dios/Deidad?":
                respuesta = personajeOculto.isEsDios();
                break;
        }

        String textoRespuesta = respuesta ? "¡SÍ!" : "NO";
        txtLog.appendText("\nPregunta: " + pregunta + " -> " + textoRespuesta);
    }

    // Acción del botón Ayuda
    @FXML
    void onAyuda() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Manual del Maestro Roshi");
        alert.setHeaderText("Instrucciones de Combate");
        alert.setContentText("1. He ocultado mi Ki (Personaje Secreto).\n"
                + "2. Usa el menú desplegable para sentir mi energía (Hacer preguntas).\n"
                + "3. Si la respuesta no coincide, descarta a los guerreros haciendo clic en ellos.\n"
                + "4. ¡Encuentra quien soy antes de que se acabe el tiempo!");
        alert.showAndWait();
    }
}
