package proyectoModulo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Esta clase va a contener diferentes palabras y signos de puntuación en español, para tratar de
tener una lista de palabras más usadas en español
 */
public class Palabras {
    public static int factor;

    private List<String> palabras = new ArrayList<String>();

    public Palabras() {
        cargarPalabras();
    }

    public double contarPalabras(String palabra){
        /*
        Este metodo me devuelve un porcentaje de las palabras recibidas del texto
        y verificado que cantidad de estas pertenecen al idioma español
         */
        double contador = 0;
        String [] palabrasTexto = palabra.split(" ");
        System.out.println(Arrays.toString(palabrasTexto));
        for(String palabraTexto : palabrasTexto){
            String palabraAux = palabraTexto.toLowerCase();

            if(palabraAux.startsWith(palabraTexto) || palabraTexto.startsWith(palabraAux)){
                contador++;
            }
        }
        double resultado = contador/palabrasTexto.length;
        // esto lo debo sacar
        System.out.println("resultado: " + resultado);
        System.out.println("contador: " + contador);
        System.out.println("palabras: " + palabrasTexto.length);
        // hasta aca lo debo sacar
        return resultado;
    }


    public void cargarPalabras(){
        palabras.add("hola");
        palabras.add("tiempo");
        palabras.add("este");
        palabras.add("más");
        palabras.add("pero");
        palabras.add("hacer");
        palabras.add("comer");
        palabras.add("quiero");
        palabras.add("quien");
        palabras.add("como");
        palabras.add("desde");
        palabras.add("hace");
        palabras.add("la");
        palabras.add("al");
        palabras.add("conseguir");
        palabras.add("cambiar");
        palabras.add("establecer");
        palabras.add("ser");
        palabras.add("estar");
        palabras.add(",");
        palabras.add("romper");
        palabras.add("tanto");
        palabras.add("esto");
        palabras.add("valore");
        palabras.add("cambio");
        palabras.add("alguno");
        palabras.add("texto");
        palabras.add("tilde");
        palabras.add("siempre");
        palabras.add("casi");
        palabras.add("aunque");
        palabras.add("niño");
        palabras.add("niña");
        palabras.add("perro");
        palabras.add("fruta");
        palabras.add("casa");
        palabras.add("color");
        palabras.add("caso");
        palabras.add("suma");
        palabras.add("esfuerzo");
        palabras.add("es");
        palabras.add("prueba");
        palabras.add("una");


    }
}
