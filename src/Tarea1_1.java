import java.io.File;
import java.util.Scanner;

public class Tarea1_1 {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la ruta del directorio: ");
        String rutaDirectorio = scanner.nextLine();
        scanner.close();

        File directorio = new File(rutaDirectorio);

        if (!directorio.exists()) {
            System.out.println("El directorio no existe.");
            return;
        }

        if (!directorio.isDirectory()) {
            System.out.println("La ruta proporcionada no es un directorio.");
            return;
        }

        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                // Construir el string de permisos
                StringBuilder permisos = new StringBuilder();
                // Primero indicamos si es directorio (d) o archivo (-)
                permisos.append(archivo.isDirectory() ? "d" : "-");
                // Permisos de lectura (r)
                permisos.append(archivo.canRead() ? "r" : "-");
                // Permisos de escritura (w)
                permisos.append(archivo.canWrite() ? "w" : "-");
                // Permisos de ejecución (x)
                permisos.append(archivo.canExecute() ? "x" : "-");

                // Mostrar permisos y nombre
                System.out.println(permisos + " " + archivo.getName());
            }
        } else {
            System.out.println("Error al listar el contenido del directorio.");
        }
    }
}
