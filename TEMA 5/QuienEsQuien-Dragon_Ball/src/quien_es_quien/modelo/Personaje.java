package quien_es_quien.modelo;

public class Personaje {
    // Atributos del personaje
    private String nombre;
    private String rutaImagen;

    // Características booleanas para el juego
    private boolean esSaiyan;
    private boolean esVillano;
    private boolean tienePelo;
    private boolean esVerde;
    private boolean esAndroide;
    private boolean esTerricola;
    private boolean esDios; // Añadido para personajes de Super

    // Constructor completo
    public Personaje(String nombre, String rutaImagen, boolean esSaiyan, boolean esVillano, boolean tienePelo,
            boolean esVerde, boolean esAndroide, boolean esTerricola, boolean esDios) {
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        this.esSaiyan = esSaiyan;
        this.esVillano = esVillano;
        this.tienePelo = tienePelo;
        this.esVerde = esVerde;
        this.esAndroide = esAndroide;
        this.esTerricola = esTerricola;
        this.esDios = esDios;
    }

    // Getters para acceder a los datos
    public String getNombre() {
        return nombre;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public boolean isEsSaiyan() {
        return esSaiyan;
    }

    public boolean isEsVillano() {
        return esVillano;
    }

    public boolean isTienePelo() {
        return tienePelo;
    }

    public boolean isEsVerde() {
        return esVerde;
    }

    public boolean isEsAndroide() {
        return esAndroide;
    }

    public boolean isEsTerricola() {
        return esTerricola;
    }

    public boolean isEsDios() {
        return esDios;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
