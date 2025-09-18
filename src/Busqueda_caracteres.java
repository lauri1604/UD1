import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Método para contar cuántas veces aparece un carácter en el fichero
public static int contarCaracter(String rutaFichero, char objetivo) {
    int contador = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
        int c;
        while ((c = br.read()) != -1) {
            if ((char) c == objetivo) {
                contador++;
            }
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
    }
    return contador;
}

        // Método para encontrar el carácter más usado en el fichero
        public static char caracterMasUsado(String rutaFichero) {
            Map<Character, Integer> frecuencias = new HashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
                int c;
                while ((c = br.read()) != -1) {
                    char caracter = (char) c;
                    frecuencias.put(caracter, frecuencias.getOrDefault(caracter, 0) + 1);
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
            char masFrecuente = ' ';
            int maxFrecuencia = 0;
            for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
                if (entrada.getValue() > maxFrecuencia) {
                    maxFrecuencia = entrada.getValue();
                    masFrecuente = entrada.getKey();
                }
            }
            return masFrecuente;
        }

        // Método principal
        public static void main(String[] args) {
            String ruta = "data/palabras.txt"; // ← Aquí se usa el archivo que mencionaste
            char buscado = 'a';           // ← Puedes cambiar este carácter por el que quieras buscar
            int ocurrencias = contarCaracter(ruta, buscado);
            System.out.println("El carácter '" + buscado + "' aparece " + ocurrencias + " veces en el archivo.");
            char masUsado = caracterMasUsado(ruta);
            System.out.println("El carácter más usado en el archivo es: '" + masUsado + "'");
        }