import java.io.File;
import java.util.Scanner;
public class Version_simple_ls {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce la ruta del directorio: ");
        String ruta = scanner.nextLine();
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.out.println("❌ No es una ruta.");
        } else if (!archivo.isDirectory()) {
            System.out.println("⚠️ La ruta existe pero no es un directorio.");
        } else {
            File[] contenido = archivo.listFiles();
            if (contenido == null) {
                System.out.println("❌ No se pudo acceder al contenido.");
            } else {
                for (File elemento : contenido) {
                    String tipo = elemento.isDirectory() ? "d" : "-";
                    String permisos =
                            (elemento.canRead() ? "r" : "-") +
                                    (elemento.canWrite() ? "w" : "-") +
                                    (elemento.canExecute() ? "x" : "-");
                    System.out.println(tipo + permisos + " " + elemento.getName());
                }
            }
        }
        scanner.close();
    }
}