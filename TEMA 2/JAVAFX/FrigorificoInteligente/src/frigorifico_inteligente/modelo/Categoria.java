package frigorifico_inteligente.modelo;

public enum Categoria {
    VERDURAS(1.00),
    FRUTAS(0.95),
    LEGUMBRES(0.95),
    CEREALES(0.88),
    LECHE_Y_DERIVADOS(0.85),
    HUEVOS(0.85),
    CARNES_Y_PESCADOS(0.75),
    BEBIDAS_NO_AZUCARADAS(0.90),
    PROCESADOS(0.50),
    DULCES_Y_BOLLERIA(0.30),
    BEBIDAS_AZUCARADAS(0.25); // opcional// opcional

    private final double calificacion;

    Categoria(double calificacion) {
        this.calificacion = calificacion;
    }

    public double getCalificacion() {
        return calificacion;
    }

    @Override
    public String toString() {
        String texto = this.name().replace('_', ' ').toLowerCase(); // Pasamos el nombre a minúscula y sustituimos - por espacios
        String[] palabras = texto.split(" "); // Dividimos la linea mediante los espacios
        StringBuilder sb = new StringBuilder();

        for (String palabra : palabras) { // Recorremos las palabras
            if (palabra.length() > 0) {  // Comprobamos que sea almenos unaú
                sb.append(Character.toUpperCase(palabra.charAt(0))) // Cogemos la primera letra y la ponemos minúscula
                  .append(palabra.substring(1)) // Coge el resto de la palabra
                  .append(" "); // Le añade un espacio
            }
        }

        return sb.toString().trim();
    }

}
