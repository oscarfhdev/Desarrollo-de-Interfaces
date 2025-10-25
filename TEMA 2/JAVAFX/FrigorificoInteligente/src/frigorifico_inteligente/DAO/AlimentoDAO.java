package frigorifico_inteligente.DAO;

import frigorifico_inteligente.modelo.Alimento;
import frigorifico_inteligente.modelo.Categoria;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAO {

    private static final String RUTA_ARCHIVO = "src/frigorifico_inteligente/alimentos.txt";

    // Constructor: crea el archivo si no existe
    public AlimentoDAO() {
        File archivo = new File(RUTA_ARCHIVO);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Devuelve una lista con todos los alimentos del archivo
    public List<Alimento> obtenerAlimentos() {
        List<Alimento> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] datos = linea.split("\\|");
                if (datos.length == 4) {
                    String id = datos[0];
                    String nombre = datos[1];
                    int cantidad = Integer.parseInt(datos[2]);
                    Categoria categoria = Categoria.valueOf(datos[3]);
                    lista.add(new Alimento(id, nombre, cantidad, categoria));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Guarda un alimento nuevo al final del archivo 
    public void guardarAlimento(Alimento alimento) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            bw.write(alimento.toString());
            bw.newLine(); // salto de línea para que cada alimento quede en una línea separada
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Actualiza un alimento existente (por id)
    public void actualizarAlimento(Alimento alimentoActualizado) {
        List<Alimento> lista = obtenerAlimentos();
        for (int i = 0; i < lista.size(); i++) {
        if (lista.get(i).getId().equals(alimentoActualizado.getId())) {
            lista.set(i, alimentoActualizado); // reemplaza el alimento existente
            break;
        }
    }
    sobrescribirArchivo(lista);
}
    
    

    // Elimina un alimento del archivo
    public void eliminarAlimento(Alimento alimento) {
        List<Alimento> lista = obtenerAlimentos();
        lista.removeIf(a -> a.getId().equals(alimento.getId()));
        sobrescribirArchivo(lista);
    }

    // Sobrescribe todo el archivo con la lista actual
    private void sobrescribirArchivo(List<Alimento> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            for (Alimento a : lista) {
                bw.write(a.toString());
                bw.newLine(); // salto de línea
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}