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
                    // leer el número que identifica el factor de cifrado
                    leerFactor();
                    break;
                case 2:
                    // Se lee el  texto a guardar en el archivo de texto a ser encriptado
                    System.out.println("Ingrese el texto a guadar en el archivo: ");
                    String texto = otro.nextLine();
                    guardarMensaje(texto, "TextoOrigen.txt");
                    break;
                case 3:
                    // Se encripta el archivo de texto, en uno ecriptado por el factor Cesar
                    String textoAEncriptar = leerArchivo("TextoOrigen.txt");
                    encriptarArchivo(textoAEncriptar);
                    break;
                case 4:
                    // Desencriptar el archivo por fuerza bruta
                    desencriptarFuerzaBruta();
                    break;
                case 5:
                    // Hace un cripto análisis de los caracteres más usados en el texto
                    // original y texto ecnriptado, y hace la diferencia para conocer el factor.
                    criptoanalisis();
                    break;
                case 0:
                    // Opción para salir
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

    // Métodos usados para las opciones del menú


    // Método para leer el factor
    // Se encapsula el factor entre números enteros entre 1 y 30.
    // Lo guarda en una variable estatica en la clase Palabras
    private static void leerFactor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el factor clave Cesar (1-30) ");
        boolean valido = true;
        while (valido) {
            try {
                int factorCesar = scanner.nextInt();
                if (factorCesar < 1 || factorCesar > 30) {
                    // Si el numero ingresado esta fuera de rango se solicita nuevamente
                    System.out.println("Ingrese el factor clave Cesar (1-30) ");
                } else {
                    // Si el factor esta en el rango, se guarda en la
                    // variable estatica de la clase Palabras y se sale del while
                    Palabras.factor = factorCesar;
                    valido = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero valido");
            }
        }
        System.out.println("Factor aceptado correctamente");
        esperarMiliSegundos(1000);
    }

    // Metodo para realizar el criptoanalisis
    // Se leen los dos archivos el origen y el encriptado y se analizan
    // en cada uno de ellos cual es el caracter que más veces aparece en cada
    // uno de los textos, luego se hace una diferencia para saber de esa
    // forma cual es el factor
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
        // Al final de este bucle sabemos cual es el caracter que aparece más veces
        // en el texto origen
        int mayorEncri = 0;
        Character carmayEncri = 'a';
        for (Character c : conteoCaracteresEncrip.keySet()) {
            if (conteoCaracteresEncrip.get(c) > mayorEncri) {
                mayorEncri = conteoCaracteresEncrip.get(c);
                carmayEncri = c;
            }
        }
        // Al final de este bucle sabemos cual es el caracter que aparece más veces
        // en el texto encriptado

        // Si son iguales la cantidad de veces que se repiten en los diferentes textos
        // hacemos la diferencia para saber el factor obtenido
        if (mayorOrigen == mayorEncri) {
            int valorOrigen = (int) carmayOrigen;
            int valorEncrip = (int) carmayEncri;
            int resultado = valorEncrip - valorOrigen;
            System.out.println("El valor número fuerza bruta es: " + resultado);
            esperarMiliSegundos(1000);
        } else {
            System.out.println("El valor numero fuerza no fue encontrado");
        }


    }

    // Metodo auxiliar usado por el método de criptoanalisis que nos devuelve
    // un mapa con cada caracter que contiene en el texto y las veces que este aparece
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

    // Metodo para desencriptar por fuerza bruta, lo que hace es recorrer todos los factores
    // posibles entre 1 y 30 (ya que fueron acotados al principio el factor a seleccionar)
    private static void desencriptarFuerzaBruta() {
        // Lee el archivo encriptado
        String textoEncriptado = leerArchivo("Encriptado.txt");

        // Creo una instancia de la clase Palabras, en donde tengo una serie de
        // métodos a trabajar con palabras
        Palabras pal = new Palabras();

        if (textoEncriptado.length() > 0) {
            for (int i = 1; i <= 30; i++) {
                // llama al método de desencritar con el indice del factor que estoy
                // iterando en el bucle for

                // llama al método desencriptar pasando el factor y el texto encriptado
                String result = desencriptar(i, textoEncriptado);
                double porc = pal.contarPalabras(result);
                System.out.println("Porcentaje: " + porc);
                // De acuerdo a las pruebas realizadas, si tenemos un porcentaje de acierto
                // mayor al 60% , estamos en condiciones de afirmar que encontramos el factor
                // adecuado para desencriptar.
                if (porc > 0.6) {
                    System.out.println("Texto desencriptado: " + result);
                    System.out.printf("Porcentaje: %.2f %n ", porc);
                    System.out.printf("Numero es: %d %n ", i);
                    esperarMiliSegundos(1000);
                    break;
                }
            }

        } else {
            System.out.println("No se encuentra el archivo");
        }
    }

    // Metodo que descencrita el texto pasando el factor y el texto a desencriptar
    private static String desencriptar(int numero, String texto) {
        String resultado;
        // Utilizamos el StringBuilder para ir agregando caracter a caracter del
        // texto desencriptado
        StringBuilder encriptado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            int valor = texto.charAt(i) - numero;
            char caracter = (char) valor;
            encriptado.append(caracter);
        }
        resultado = encriptado.toString();
        return resultado;
    }

    // Metodo para leer y devolver el contenido de un archivo de texto
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

    // Metodo para encriptar un archivo
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
        esperarMiliSegundos(600);
    }

    // método que muestra el menu del programa
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

    // metodo para detener la ejecución del programa por medio segundo
    private static void esperarMiliSegundos(int tiempo){
        // Detiene el ejución para mostrar mensajes
        // durante medio segundo
        try {
            Thread.sleep(tiempo); // Esperar medio segundo para poder ver el mensaje
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
