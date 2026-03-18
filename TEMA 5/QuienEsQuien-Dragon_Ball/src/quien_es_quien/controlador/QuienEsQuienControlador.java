package quien_es_quien.controlador;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.util.List;
import java.util.Random;

import quien_es_quien.modelo.BaseDatos;
import quien_es_quien.modelo.Personaje;

public class QuienEsQuienControlador {

    // Elementos de la interfaz (FXML)
    @FXML
    private GridPane gridPersonajes;

    @FXML
    private ComboBox<String> comboPreguntas;

    @FXML
    private Label lblIntentos;

    @FXML
    private javafx.scene.text.TextFlow flowLog;

    @FXML
    private javafx.scene.control.ScrollPane scrollLog;

    // Estado del juego
    private List<Personaje> tablero;
    private Personaje personajeOculto;
    private Random random = new Random();
    private int preguntasRestantes;

    // Inicialización del controlador
    @FXML
    public void initialize() {
        cargarDatos();
        prepararInterfaz();
    }

    // Carga la lista de personajes y selecciona uno aleatorio
    private void cargarDatos() {
        tablero = BaseDatos.getPersonajes();
        // Elegimos el personaje secreto
        personajeOculto = tablero.get(random.nextInt(tablero.size()));
        System.out.println("El personaje oculto es " + personajeOculto.getNombre());
    }

    // Configura la UI inicial
    private void prepararInterfaz() {
        // Reset de intentos
        preguntasRestantes = 3;
        if (lblIntentos != null) {
            lblIntentos.setText("Oportunidades: " + preguntasRestantes);
        }

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
        flowLog.getChildren().clear();
        agregarTexto("¡Bienvenido a 'En Busca del Ki'!\n", javafx.scene.paint.Color.DARKORANGE, true, 18);
        agregarTexto("He sentido el Ki de un personaje oculto...\n", javafx.scene.paint.Color.BLACK, false, 14);
        agregarTexto("Tienes " + preguntasRestantes + " intentos para detectar su energía.\n",
                javafx.scene.paint.Color.RED, true, 14);
    }

    // Método auxiliar para añadir texto con estilo al log
    private void agregarTexto(String texto, javafx.scene.paint.Color color, boolean negrita, double tamano) {
        javafx.scene.text.Text t = new javafx.scene.text.Text(texto);
        t.setFill(color);
        t.setFont(javafx.scene.text.Font.font("Arial",
                negrita ? javafx.scene.text.FontWeight.BOLD : javafx.scene.text.FontWeight.NORMAL, tamano));
        flowLog.getChildren().add(t);

        // Auto-scroll al final
        scrollLog.layout();
        scrollLog.setVvalue(1.0);
    }

    // Crea la representación visual (tarjeta) de un personaje
    private VBox crearCeldaPersonaje(Personaje p) {
        VBox vbox = new VBox();
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        vbox.setSpacing(5);
        vbox.setPadding(new javafx.geometry.Insets(5));
        vbox.getStyleClass().add("character-card"); // Clase CSS para estilo
        vbox.setPrefSize(140, 160);

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
        imgView.setFitHeight(100);
        imgView.setFitWidth(100);
        imgView.setPreserveRatio(true);

        // Etiqueta con el nombre
        Label lblNombre = new Label(p.getNombre());
        lblNombre.getStyleClass().add("character-name");

        vbox.getChildren().addAll(imgView, lblNombre);

        // Evento de ratón: al hacer click, intentamos adivinar el personaje
        vbox.setOnMouseClicked(e -> {
            onPersonajeClick(vbox, p);
        });

        return vbox;
    }

    // Lógica al hacer clic en un personaje (Intento final)
    private void onPersonajeClick(VBox nodo, Personaje p) {
        // Ignoramos si ya está deshabilitado
        if (nodo.getStyleClass().contains("character-card-disabled")) {
            return;
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);

        if (p == personajeOculto) {
            // ¡Victoria!
            alert.setTitle("¡VICTORIA!");
            alert.setContentText("¡INCREÍBLE! ¡Has encontrado el Ki oculto!\nEra " + p.getNombre() + ".");
        } else {
            // ¡Derrota!
            alert.setAlertType(AlertType.ERROR);
            alert.setTitle("DERROTA");
            alert.setContentText(
                    "¡Fallaste! Ese Ki no coincide.\nEl personaje oculto era " + personajeOculto.getNombre() + ".");
        }
        alert.showAndWait();
        // Reiniciamos el juego tras la alerta por simplicidad
        initialize();
    }

    // Acción del botón 'Preguntar'
    @FXML
    private void onPreguntar() {
        if (preguntasRestantes <= 0) {
            agregarTexto("\n¡Ya no te queda energía para hacer más preguntas! Debes arriesgarte y elegir.",
                    javafx.scene.paint.Color.RED, true, 14);
            return;
        }

        String pregunta = comboPreguntas.getValue();
        if (pregunta == null) {
            agregarTexto("\n¡Concentra tu Ki! Debes seleccionar una pregunta.", javafx.scene.paint.Color.ORANGERED,
                    true, 14);
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

        // Restamos una pregunta
        preguntasRestantes--;
        if (lblIntentos != null) {
            lblIntentos.setText("Oportunidades: " + preguntasRestantes);
        }

        String textoRespuesta = respuesta ? "SÍ" : "NO";
        javafx.scene.paint.Color colorRespuesta = respuesta ? javafx.scene.paint.Color.GREEN
                : javafx.scene.paint.Color.RED;

        agregarTexto("\nPregunta: " + pregunta + " -> ", javafx.scene.paint.Color.BLACK, false, 14);
        agregarTexto(textoRespuesta, colorRespuesta, true, 16);
        agregarTexto("\n(Te quedan " + preguntasRestantes + " preguntas)", javafx.scene.paint.Color.DARKGRAY, false,
                12);

        // Lógica de Descarte Automático
        descartarAutomaticamente(pregunta, respuesta);

        // Comprobamos si nos hemos quedado sin intentos
        if (preguntasRestantes == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("¡Atención!");
            alert.setHeaderText("Has agotado tus preguntas");
            alert.setContentText("Ya no puedes sentir más energía.\n¡AHORA DEBES ARRIESGARTE Y ELEGIR UN PERSONAJE!");
            alert.showAndWait();

            agregarTexto("\n[SISTEMA] ¡FASE FINAL! Haz clic en el personaje que creas que es.",
                    javafx.scene.paint.Color.DARKMAGENTA, true, 15);

            // Deshabilitamos el botón de preguntar para que quede claro (opcional, pero
            // visualmente útil)
            // comboPreguntas.setDisable(true); // Si quieres bloquear la ui
        }
    }

    // Deshabilita los personajes que NO coinciden con la respuesta obtenida
    private void descartarAutomaticamente(String pregunta, boolean respuestaCorrecta) {
        int contadorDescartados = 0;

        // Iteramos sobre todos los nodos de la grid
        for (Node nodo : gridPersonajes.getChildren()) {
            if (nodo instanceof VBox) {
                VBox card = (VBox) nodo;
                // Buscamos qué personaje corresponde a esta carta
                // Una forma sencilla es buscarlo en el tablero por nombre,
                // ya que el Label tiene el nombre.
                String nombrePersonaje = ((Label) card.getChildren().get(1)).getText();
                Personaje p = tablero.stream()
                        .filter(personaje -> personaje.getNombre().equals(nombrePersonaje))
                        .findFirst()
                        .orElse(null);

                if (p != null) {
                    boolean propiedadPersonaje = false;
                    switch (pregunta) {
                        case "¿Es un Saiyan?":
                            propiedadPersonaje = p.isEsSaiyan();
                            break;
                        case "¿Es un Villano?":
                            propiedadPersonaje = p.isEsVillano();
                            break;
                        case "¿Tiene pelo?":
                            propiedadPersonaje = p.isTienePelo();
                            break;
                        case "¿Es verde?":
                            propiedadPersonaje = p.isEsVerde();
                            break;
                        case "¿Es un Androide?":
                            propiedadPersonaje = p.isEsAndroide();
                            break;
                        case "¿Es Terrícola?":
                            propiedadPersonaje = p.isEsTerricola();
                            break;
                        case "¿Es un Dios/Deidad?":
                            propiedadPersonaje = p.isEsDios();
                            break;
                    }

                    // Si la propiedad del personaje NO coincide con la respuesta correcta (la del
                    // oculto),
                    // entonces este personaje NO es el oculto -> DESCHARTAR
                    if (propiedadPersonaje != respuestaCorrecta) {
                        if (!card.getStyleClass().contains("character-card-disabled")) {
                            card.getStyleClass().add("character-card-disabled");
                            card.setDisable(true);
                            contadorDescartados++;
                        }
                    }
                }
            }
        }
        if (contadorDescartados > 0) {
            agregarTexto("\n[Auto] Se han descartado " + contadorDescartados + " personajes.",
                    javafx.scene.paint.Color.BLUE, false, 13);
        }
    }

    // Acción del botón Ayuda
    @FXML
    void onAyuda() {
        try {
            // Cargamos la vista de la ayuda
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(
                    getClass().getResource("/quien_es_quien/vista/Ayuda.fxml"));
            javafx.scene.Parent root = loader.load();

            // Abrimos la ayuda en una ventana modal nueva
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Manual del Maestro Roshi");
            stage.setScene(new javafx.scene.Scene(root));

            // Bloqueamos la ventana principal mientras la ayuda esté abierta
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);

            stage.getIcons().add(new Image(getClass().getResource("/quien_es_quien/vista/icon.png").toExternalForm()));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            // Si falla algo crítico, mostramos un error básico
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ayuda");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
