import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Coches {
    public static void main(String[] args) {
        List<String> lineas;
        try {
            lineas = Files.readAllLines(Paths.get("data/coches.txt"));
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
            return;
        }
        Map<String, List<String>> marcas = new TreeMap<>();
        for (String linea : lineas) {
            String[] partes = linea.split(" ", 2);
            if (partes.length < 2) continue;
            String marca = partes[0];
            String modelo = partes[1];
            if (!marcas.containsKey(marca)) {
                marcas.put(marca, new ArrayList<>());
            }
            marcas.get(marca).add(modelo);
        }
        for (String marca : marcas.keySet()) {
            List<String> modelos = marcas.get(marca);
            Collections.sort(modelos);
            System.out.println(marca + ": " + String.join(", ", modelos));
        }
    }
}