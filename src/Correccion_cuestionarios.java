import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Correccion_cuestionarios {
    public static void main(String[] args) {
        List<String> lineas;
        try {
            lineas = Files.readAllLines(Paths.get("data/test.txt"));
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
            return;
        }
        if (lineas.isEmpty()) return;

        String correctas = lineas.get(0);
        Map<String, String> resultados = new LinkedHashMap<>();
        Map<String, Integer> resumen = new LinkedHashMap<>();
        String[] categorias = {"excelente", "notable", "bien", "aprobado", "suspenso"};
        for (String c : categorias) resumen.put(c, 0);
        for (int i = 1; i < lineas.size(); i++) {
            String[] partes = lineas.get(i).split(" ", 2);
            if (partes.length < 2) continue;
            String codigo = partes[0];
            String respuestas = limpiarEspacios(partes[1]);
            double nota = calcularNota(correctas, respuestas);
            String calificacion = obtenerCalificacion(nota);
            resultados.put(codigo, calificacion);
            resumen.put(calificacion, resumen.get(calificacion) + 1);
        }
        System.out.println("📋 Resultados por alumno:");
        for (String codigo : resultados.keySet()) {
            System.out.println("Alumno " + codigo + " → " + resultados.get(codigo));
        }
        System.out.println("\n📊 Tabla resumen de calificaciones:");
        int total = resultados.size();
        for (String c : categorias) {
            int cantidad = resumen.get(c);
            double porcentaje = total == 0 ? 0 : cantidad * 100.0 / total;
            System.out.printf("%-10s: %2d alumnos (%.2f%%)%n", c, cantidad, porcentaje);
        }
    }

    static String limpiarEspacios(String texto) {
        String resultado = "";
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c != ' ') resultado += c;
        }
        return resultado;
    }

    static double calcularNota(String correctas, String alumno) {
        double nota = 0;
        for (int i = 0; i < Math.min(correctas.length(), alumno.length()); i++) {
            char r = alumno.charAt(i);
            if (r == 'T' || r == 'F') {
                nota += (r == correctas.charAt(i)) ? 0.5 : -0.15;
            }
        }
        return Math.max(nota, 0);
    }

    static String obtenerCalificacion(double nota) {
        if (nota >= 8.5) return "excelente";
        if (nota >= 7) return "notable";
        if (nota >= 6) return "bien";
        if (nota >= 5) return "aprobado";
        return "suspenso";
    }
}