package proyectoModulo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Esta clase va a contener diferentes palabras y signos de puntuación en español, para tratar de
tener una lista de palabras más usadas en español
 */
public class Palabras {
    // Atributo estatico que guarda el factor Cesar seleccionado en el menu
    public static int factor;

    // Creo una atributo que es una lista de String llamados palabras va
    // a contener la lista de palabras del diccionario a cargar de palabras en
    // español.
    private List<String> palabras = new ArrayList<String>();

    // constructor sin atributos que al instanciar carga la lista de palabras
    public Palabras() {
        cargarPalabras();
    }

    // Este método cuenta las palabras que se parecen al idioma español
    public double contarPalabras(String palabra){
        /*
        Este metodo me devuelve un porcentaje de las palabras recibidas del texto
        y verificado que cantidad de estas pertenecen al idioma español
         */
        double contador = 0;
        // con el metodo split de la clase String, hago un array de String de las diferentes
        // palabras conseguidas separadas con el patron de espacio entre las mismas.
        String [] palabrasTexto = palabra.split(" ");
        System.out.println(Arrays.toString(palabrasTexto));
        for(String palabraTexto : palabrasTexto){
            String palabraAux = palabraTexto.toLowerCase();

            if(palabraAux.startsWith(palabraTexto) || palabraTexto.startsWith(palabraAux)){
                contador++;
            }
        }
        double resultado = contador/palabrasTexto.length;
        // esto lo coloque a modo ilustrativo que en cada vez que se usa este método
        // muestre por consola el resultado en porcentaje de que aproximación tiene respecto
        // a palabras encontradas total o parcialmente en leguaje español
        System.out.println("resultado: " + resultado);
        System.out.println("contador: " + contador);
        System.out.println("palabras: " + palabrasTexto.length);
        // Lo dejo como ilustrativo pero si es necesario lo puedo sacar
        return resultado;
    }


    public void cargarPalabras(){
        // Cargo una serie de palabras en español como parte del diccionario a comparar con las
        // palabras desencriptadas

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
