import java.io.*;
import java.util.*;

public class Ordenaciones {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Ordenaciones <nombre_fichero> <tipo_ordenacion>");
            System.out.println("Tipos válidos: asc_case, asc_non_case, desc_case, desc_non_case");
            return;
        }

        String nombreFichero = args[0];
        String tipoOrdenacion = args[1];

        List<String> lineas = new ArrayList<>();

        // Leer líneas del fichero
        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        // Ordenar según el tipo
        Comparator<String> comparador;

        switch (tipoOrdenacion) {
            case "asc_case":
                comparador = Comparator.naturalOrder();
                break;
            case "asc_non_case":
                comparador = String.CASE_INSENSITIVE_ORDER;
                break;
            case "desc_case":
                comparador = Comparator.reverseOrder();
                break;
            case "desc_non_case":
                comparador = String.CASE_INSENSITIVE_ORDER.reversed();
                break;
            default:
                System.out.println("Tipo de ordenación no válido.");
                return;
        }

        Collections.sort(lineas, comparador);

        // Crear nombre del fichero resultado
        String nombreSalida = generarNombreSalida(nombreFichero, tipoOrdenacion);

        // Escribir líneas ordenadas en el nuevo fichero
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreSalida))) {
            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Fichero ordenado creado: " + nombreSalida);
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }

    // Método para generar el nombre del fichero de salida
    private static String generarNombreSalida(String original, String tipoOrdenacion) {
        int punto = original.lastIndexOf('.');
        if (punto == -1) {
            return original + "_" + tipoOrdenacion + ".txt";
        } else {
            String base = original.substring(0, punto);
            String extension = original.substring(punto);
            return base + "_" + tipoOrdenacion + extension;
        }
    }
}