/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package actividadarchivosjava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author edu19
 */
public class ActividadArchivosJava {

    private static final String ARCHIVO_CSV = "Restaurantes.csv";
    private static final String ARCHIVO_COPIA = "RestaurantesCopia.csv";
    private static final String SEPARADOR = ";";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("==== MENU ====");
            System.out.println("1. Revisar ruta (archivo o carpeta)");
            System.out.println("2. Crear archivo CSV (anexo 1)");
            System.out.println("3. Mostrar restaurantes con codigo postal que inicia en 6");
            System.out.println("4. Agregar restaurante al CSV");
            System.out.println("5. Crear copia sin codigos postales que inician en 6");
            System.out.println("6. Borrar un archivo por ruta");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");

            String entrada = scanner.nextLine();
            if (entrada.isEmpty()) {
                continue;
            }

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException ex) {
                System.out.println("Opcion invalida.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("[Accion] Revisar ruta");
                    revisarRuta(scanner);
                    break;
                case 2:
                    System.out.println("[Accion] Crear archivo CSV");
                    crearCsvConAnexo();
                    break;
                case 3:
                    System.out.println("[Accion] Mostrar restaurantes con codigo postal 6");
                    mostrarRestaurantesCodigo6();
                    break;
                case 4:
                    System.out.println("[Accion] Agregar restaurante");
                    agregarRestaurante(scanner);
                    break;
                case 5:
                    System.out.println("[Accion] Crear copia filtrada");
                    crearCopiaSinCodigo6();
                    break;
                case 6:
                    System.out.println("[Accion] Borrar archivo");
                    borrarArchivo(scanner);
                    break;
                case 0:
                    System.out.println("[Exito] Saliendo del programa.");
                    break;
                default:
                    System.out.println("[Error] Opcion invalida.");
                    break;
            }
            System.out.println();
        }
    }

    private static void revisarRuta(Scanner scanner) {
        System.out.print("Ingrese la ruta: ");
        String ruta = scanner.nextLine();

        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.out.println("[Error] El archivo o carpeta NO existe.");
            return;
        }

        System.out.println("[Exito] La ruta existe.");
        if (archivo.isDirectory()) {
            System.out.println("[Info] Es un directorio.");
        } else if (archivo.isFile()) {
            System.out.println("[Info] Es un archivo.");
            System.out.println("[Info] Nombre: " + archivo.getName());
            System.out.println("[Info] Tamano (bytes): " + archivo.length());
            System.out.println("[Info] Permiso lectura: " + archivo.canRead());
            System.out.println("[Info] Permiso escritura: " + archivo.canWrite());
        }
    }

    private static void crearCsvConAnexo() {
        File archivo = getArchivoEnProyecto(ARCHIVO_CSV);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write("Restaurante" + SEPARADOR + "Direccion" + SEPARADOR + "Ciudad" + SEPARADOR
                    + "Estado" + SEPARADOR + "CodigoPostal");
            writer.newLine();
            writer.write("Vietnam Restaurant" + SEPARADOR + "1615 Commerce Parkway" + SEPARADOR
                    + "Bloomington" + SEPARADOR + "IL" + SEPARADOR + "61704");
            writer.newLine();
            writer.write("Chops and Ribs" + SEPARADOR + "71 S. Wacker Drive Chicago" + SEPARADOR
                    + "Chicago" + SEPARADOR + "IL" + SEPARADOR + "60606");
            writer.newLine();
            writer.write("Molinaris" + SEPARADOR + "\"1701 River Drive, Suite 306\"" + SEPARADOR
                    + "Moline" + SEPARADOR + "IL" + SEPARADOR + "61265");
            writer.newLine();
            writer.write("Siam Cusine" + SEPARADOR + "\"3201 W White Oaks Drive, Suite 204\"" + SEPARADOR
                    + "Springfield" + SEPARADOR + "IL" + SEPARADOR + "62704");
            writer.newLine();
            writer.write("Mary's Diner" + SEPARADOR + "\"4916 S Technopolis Drive, Building 2\"" + SEPARADOR
                    + "Sioux Falls" + SEPARADOR + "SD" + SEPARADOR + "57106");
            writer.newLine();
            writer.write("Bistro Bob" + SEPARADOR + "625 32nd Avenue SW" + SEPARADOR
                    + "Cedar Rapids" + SEPARADOR + "IA" + SEPARADOR + "52404");
            writer.newLine();
            writer.write("Wiener House" + SEPARADOR + "\"400 Locust Street, Suite 420 4th Floor\"" + SEPARADOR
                    + "Des Moines" + SEPARADOR + "IA" + SEPARADOR + "50309");
            writer.newLine();
            writer.write("Irfute Place" + SEPARADOR
                    + "\"Federal Trust Building, 134 South 13th Street, 10th Floor, Suite 1000\"" + SEPARADOR
                    + "Lincoln" + SEPARADOR + "NE" + SEPARADOR + "68508");
            writer.newLine();
            writer.write("Estradanas" + SEPARADOR + "\"1111 N 102nd Court, Suite 231\"" + SEPARADOR
                    + "Omaha" + SEPARADOR + "NE" + SEPARADOR + "68114");
            writer.newLine();
            writer.write("Fargo" + SEPARADOR + "\"3310 Fiechtner Drive, Suite 108\"" + SEPARADOR
                    + "Fargo" + SEPARADOR + "ND" + SEPARADOR + "58103");
            writer.newLine();
            System.out.println("[Exito] Archivo CSV creado: " + archivo.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("[Error] No se pudo crear el CSV: " + ex.getMessage());
        }
    }

    private static void mostrarRestaurantesCodigo6() {
        File archivo = getArchivoEnProyecto(ARCHIVO_CSV);
        if (!archivo.exists()) {
            System.out.println("[Error] No existe el archivo " + ARCHIVO_CSV + ". Use la opcion 2.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea = reader.readLine(); // encabezado
            if (linea == null) {
                System.out.println("[Advertencia] El archivo esta vacio.");
                return;
            }

            System.out.println("[Info] Restaurantes con codigo postal que inicia en 6:");
            boolean encontrado = false;
            while ((linea = reader.readLine()) != null) {
                String[] campos = parseCsvLine(linea, SEPARADOR.charAt(0));
                if (campos.length < 5) {
                    continue;
                }
                String codigo = campos[4].trim();
                if (codigo.startsWith("6")) {
                    encontrado = true;
                    System.out.println("- " + campos[0] + " | " + campos[1] + " | " + campos[2]
                            + " | " + campos[3] + " | " + campos[4]);
                }
            }
            if (!encontrado) {
                System.out.println("[Info] No hay restaurantes con codigo postal que inicia en 6.");
            }
        } catch (IOException ex) {
            System.out.println("[Error] Error leyendo el CSV: " + ex.getMessage());
        }
    }

    private static void agregarRestaurante(Scanner scanner) {
        File archivo = getArchivoEnProyecto(ARCHIVO_CSV);
        if (!archivo.exists()) {
            System.out.println("[Error] No existe el archivo " + ARCHIVO_CSV + ". Use la opcion 2.");
            return;
        }

        System.out.print("Nombre del restaurante: ");
        String nombre = scanner.nextLine();
        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Codigo postal: ");
        String codigo = scanner.nextLine();

        String linea = csvField(nombre) + SEPARADOR + csvField(direccion) + SEPARADOR + csvField(ciudad)
                + SEPARADOR + csvField(estado) + SEPARADOR + csvField(codigo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.newLine();
            writer.write(linea);
            System.out.println("[Exito] Registro agregado en " + archivo.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("[Error] No se pudo escribir en el CSV: " + ex.getMessage());
        }
    }

    private static void crearCopiaSinCodigo6() {
        File archivo = getArchivoEnProyecto(ARCHIVO_CSV);
        if (!archivo.exists()) {
            System.out.println("[Error] No existe el archivo " + ARCHIVO_CSV + ". Use la opcion 2.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo));
                BufferedWriter writer = new BufferedWriter(new FileWriter(getArchivoEnProyecto(ARCHIVO_COPIA)))) {
            String linea = reader.readLine();
            if (linea == null) {
                System.out.println("[Advertencia] El archivo esta vacio.");
                return;
            }
            writer.write(linea);
            writer.newLine();

            while ((linea = reader.readLine()) != null) {
                String[] campos = parseCsvLine(linea, SEPARADOR.charAt(0));
                if (campos.length < 5) {
                    continue;
                }
                String codigo = campos[4].trim();
                if (!codigo.startsWith("6")) {
                    writer.write(linea);
                    writer.newLine();
                }
            }

            System.out.println("[Exito] Copia creada: " + getArchivoEnProyecto(ARCHIVO_COPIA).getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("[Error] Error al crear la copia: " + ex.getMessage());
        }
    }

    private static void borrarArchivo(Scanner scanner) {
        System.out.print("Ruta del archivo a borrar: ");
        String ruta = scanner.nextLine();
        File archivo = new File(ruta);

        if (!archivo.exists()) {
            System.out.println("[Error] El archivo no existe.");
            return;
        }

        if (archivo.delete()) {
            System.out.println("[Exito] Archivo borrado.");
        } else {
            System.out.println("[Error] No se pudo borrar el archivo.");
        }
    }

    private static String csvField(String valor) {
        if (valor.contains(SEPARADOR) || valor.contains("\"")) {
            String limpio = valor.replace("\"", "\"\"");
            return "\"" + limpio + "\"";
        }
        return valor;
    }

    private static String[] parseCsvLine(String line, char separador) {
        List<String> campos = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        boolean enComillas = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                enComillas = !enComillas;
            } else if (c == separador && !enComillas) {
                campos.add(actual.toString());
                actual.setLength(0);
            } else {
                actual.append(c);
            }
        }
        campos.add(actual.toString());
        return campos.toArray(new String[0]);
    }

    private static File getArchivoEnProyecto(String nombre) {
        String base = System.getProperty("user.dir");
        return new File(base, nombre);
    }
}
