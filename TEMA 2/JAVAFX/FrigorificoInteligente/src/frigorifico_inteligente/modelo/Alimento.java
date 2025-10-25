package frigorifico_inteligente.modelo;

import java.util.UUID;

public class Alimento {

    private final String id;
    private String nombre;
    private int cantidad;
    private Categoria categoria;

    // Constructor normal para crear alimentos
    public Alimento(String nombre, int cantidad, Categoria categoria) {
        this.id = UUID.randomUUID().toString(); //Utilizamos la clase UUID para generar un id random muy largo, irrepetible
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    // Constructor adicional ccon ID para cargar desde archivo
    public Alimento(String id, String nombre, int cantidad, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // Para guardar fácilmente en el archivo
    @Override
    public String toString() {
        return id + "|" + nombre + "|" + cantidad + "|" + categoria.name();
    }
}
