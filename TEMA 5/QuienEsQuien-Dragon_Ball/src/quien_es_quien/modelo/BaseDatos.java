package quien_es_quien.modelo;

import java.util.ArrayList;
import java.util.List;

public class BaseDatos {

    public static List<Personaje> getPersonajes() {
        List<Personaje> lista = new ArrayList<>();

        // Constructor: (nombre, imagen, esSaiyan, esVillano, tienePelo, esVerde,
        // esAndroide, esTerricola, esDios)

        // --- Dragon Ball Original ---
        lista.add(new Personaje("Kid Goku", "goku_kid.png", true, false, true, false, false, false, false));
        lista.add(new Personaje("Bulma", "bulma.png", false, false, true, false, false, true, false));
        lista.add(new Personaje("Muten Roshi", "roshi.png", false, false, false, false, false, true, false));
        lista.add(new Personaje("Yamcha", "yamcha.png", false, false, true, false, false, true, false));
        lista.add(new Personaje("Krillin", "krillin.png", false, false, false, false, false, true, false));
        lista.add(new Personaje("Tenshinhan", "tenshinhan.png", false, false, false, false, false, true, false));
        lista.add(new Personaje("Piccolo Daimao", "piccolo_daimao.png", false, true, false, true, false, false, false)); // El padre de Piccolo

        // --- Dragon Ball Z ---
        lista.add(new Personaje("Piccolo", "piccolo.png", false, false, false, true, false, false, false)); // Piccolo Jr
        lista.add(new Personaje("Vegeta", "vegeta.png", true, false, true, false, false, false, false)); // Ya no es villano puro en Z final/Super
        lista.add(new Personaje("Gohan", "gohan.png", true, false, true, false, false, false, false));
        lista.add(new Personaje("Future Trunks", "trunks.png", true, false, true, false, false, false, false));
        lista.add(new Personaje("Freezer", "freezer.png", false, true, false, false, false, false, false));
        lista.add(new Personaje("Cell", "cell.png", false, true, false, true, true, false, false));
        lista.add(new Personaje("Majin Buu", "buu.png", false, true, false, false, false, false, false));
        lista.add(new Personaje("Androide 18", "androide18.png", false, false, true, false, true, false, false));

        // --- Dragon Ball Super ---
        lista.add(new Personaje("Beerus", "beerus.png", false, false, false, false, false, false, true)); // Dios
        lista.add(new Personaje("Whis", "whis.png", false, false, true, false, false, false, true)); // Angel/Dios
        lista.add(new Personaje("Goku Black", "goku_black.png", true, true, true, false, false, false, true)); // Zamasu en cuerpo Goku (Dios)
        lista.add(new Personaje("Jiren", "jiren.png", false, true, false, false, false, false, false)); // Antagonista
        lista.add(new Personaje("Broly (Super)", "broly.png", true, false, true, false, false, false, false)); // Saiyan legendario

        return lista;
    }
}
