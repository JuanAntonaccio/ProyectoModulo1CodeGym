package proyectoModulo1;
/*
Proyecto final del Modulo 1
Abril 2024
Autor: Juan Antonaccio - Durazno - Uruguay
Codegym
correo: jmantonaccio@gmail.com
Tutor: Edgar Saldaña
 */


import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        boolean salir = true;
        int opcion = 10;
        Scanner sc = new Scanner(System.in);
        Scanner otro = new Scanner(System.in);
        while (salir) {
            imprimirPantalla();
            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un número entero valido");
                sc.nextLine();
                continue;
            }
            switch (opcion) {
                case 1:
                    leerFactor();
                    break;
                case 2:

                    System.out.println("Ingrese el texto a guadar en el archivo: ");
                    String texto = otro.nextLine();
                    guardarMensaje(texto, "TextoOrigen.txt");
                    break;
                case 3:
                    String texto2 = leerArchivo("TextoOrigen.txt");
                    encriptarArchivo(texto2);
                    break;
                case 4:
                    desencriptarFuerzaBruta();
                    break;
                case 5:
                    criptoanalisis();
                    break;
                case 0:
                    salir = false;
                    sc.close();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
        // Cerramos el scanner al terminar el loop
        sc.close();
        System.out.println();
        System.out.println();
        System.out.println("=".repeat(80));
        System.out.println("             Fin del programa, gracias por su Elección");
        System.out.println("=".repeat(80));

    }

    private static void leerFactor() {
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Ingrese el factor clave Cesar (1-30) ");
        boolean valido = true;
        while (valido) {
            try {
                int num = sc2.nextInt();
                if (num < 1 || num > 30) {
                    System.out.println("Ingrese el factor clave Cesar (1-30) ");
                } else {
                    Palabras.factor = num;
                    valido = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero valido");
            }
        }
        System.out.println("Factor aceptado correctamente");
    }

    private static void criptoanalisis() {
        String textoOrigen = leerArchivo("TextoOrigen.txt");
        String textoEncriptado = leerArchivo("Encriptado.txt");
        Map<Character, Integer> conteoCaracteresOrigen = contarCaracteres(textoOrigen);
        Map<Character, Integer> conteoCaracteresEncrip = contarCaracteres(textoEncriptado);
        int mayorOrigen = 0;
        Character carmayOrigen = 'a';
        for (Character c : conteoCaracteresOrigen.keySet()) {
            if (conteoCaracteresOrigen.get(c) > mayorOrigen) {
                mayorOrigen = conteoCaracteresOrigen.get(c);
                carmayOrigen = c;
            }
        }
        int mayorEncri = 0;
        Character carmayEncri = 'a';
        for (Character c : conteoCaracteresEncrip.keySet()) {
            if (conteoCaracteresEncrip.get(c) > mayorEncri) {
                mayorEncri = conteoCaracteresEncrip.get(c);
                carmayEncri = c;
            }
        }
        if (mayorOrigen == mayorEncri) {
            int valorOrigen = (int) carmayOrigen;
            int valorEncrip = (int) carmayEncri;
            int resultado = valorEncrip - valorOrigen;
            System.out.println("El valor numero fuerza bruta es: " + resultado);
        } else {
            System.out.println("El valor numero fuerza no fue encontrado");
        }


    }

    private static Map<Character, Integer> contarCaracteres(String cadena) {
        Map<Character, Integer> conteoCaracteres = new HashMap<>();

        // Recorrer la cadena
        for (char c : cadena.toCharArray()) {
            // Incrementar el conteo del carácter
            if (conteoCaracteres.containsKey(c)) {
                conteoCaracteres.put(c, conteoCaracteres.get(c) + 1);
            } else {
                conteoCaracteres.put(c, 1);
            }
        }

        return conteoCaracteres;
    }

    private static void desencriptarFuerzaBruta() {

        String textoEncriptado = leerArchivo("Encriptado.txt");
        Palabras pal = new Palabras();

        if (textoEncriptado.length() > 0) {
            for (int i = 1; i <= 30; i++) {
                String result = desencriptar(i, textoEncriptado);
                double porc = pal.contarPalabras(result);
                System.out.println("Porcentaje: " + porc);
                if (porc > 0.6) {
                    System.out.println("Texto desencriptado: " + result);
                    System.out.printf("Porcentaje: %.2f %n ", porc);
                    System.out.printf("Numero es: %d %n ", i);
                    break;
                }
            }

        } else {
            System.out.println("No se encuentra el archivo");
        }
    }

    private static String desencriptar(int numero, String texto) {
        String resultado;
        StringBuilder encriptado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            int valor = texto.charAt(i) - numero;
            char caracter = (char) valor;
            encriptado.append(caracter);
        }
        resultado = encriptado.toString();
        return resultado;
    }

    private static String leerArchivo(String archivo_a_leer) {
        String resultado = null;
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(archivo_a_leer);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del archivo o fichero
            String leer;
            while ((leer = br.readLine()) != null) {
                resultado = leer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return resultado;
    }

    private static void encriptarArchivo(String texto) {
        StringBuilder encri = new StringBuilder("");
        for (int i = 0; i < texto.length(); i++) {
            int valor = texto.charAt(i) + Palabras.factor;
            char caracter = (char) valor;
            encri.append(caracter);
        }
        String nuevoTexto = encri.toString();
        guardarMensaje(nuevoTexto, "Encriptado.txt");

    }

    // Metodo que guarda un String en un archivo
    private static void guardarMensaje(String texto, String archivo) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);

            pw.println(texto);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("Texto guardado en archivo correctamente");
        try {
            Thread.sleep(500); // Esperar medio segundo para poder ver el mensaje
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // mensaje que muestra el menu del programa
    private static void imprimirPantalla() {
        System.out.println();
        System.out.println();
        System.out.println("Menu Principal");
        System.out.println("=".repeat(80));
        System.out.println("1. Definir el Factor clave Cesar");
        System.out.println("2. Generar archivo de texto con mensaje en español");
        System.out.println("3. Encriptar archivo de texto");
        System.out.println("4. Desencriptar archivo ");
        System.out.println("5. Cripto análisis");
        System.out.println("0. Salir");
        System.out.println("Ingrese su opción: ");
    }


}
