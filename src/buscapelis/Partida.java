/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscapelis;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alumno Tarde
 */
public class Partida {
    
    private boolean[] aciertos;         //array de booleanos con los caracteres que ha acertado el usuario
    protected boolean[] letrasMarcadas;   //array con las letras y números ya usados
    private int puntos;                 //puntos del usuario
    private int dificultad;             //factor por el que se multiplican los puntos perdidos por cada fallo
    private String titulo;              //título a adivinar
    
    /*
    Debido a la construcción del programa, aciertos representa los aciertos del título
    y letrasMarcadas solo sirve como información para la interfaz gráfica de qué
    teclas tiene que dejar marcadas al cargar una partida
    */
    
    //constructor para cargar una partida
    public Partida (String ruta){
        try (var ois = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(Path.of(ruta), CREATE)))){
            while (true){
                try{
                    aciertos = ((Partida)ois.readObject()).getAciertos();
                    puntos = ((Partida)ois.readObject()).getPuntos();
                    titulo = ((Partida)ois.readObject()).getTitulo();
                    dificultad = ((Partida)ois.readObject()).getDificultad();
                    letrasMarcadas = ((Partida)ois.readObject()).getLetrasMarcadas();
                }
                catch(EOFException e){
                    System.out.println("- - - Fin del fichero - - -");
                    break;
                }
            }
        }
        catch (IOException ex) {
            Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Constructor para una partida nueva
    public Partida (){ 

        BufferedReader br = null;

        int contadorSaltos = 0;


        //System.out.print(aleatorio + " ");
        try {

            //CONTADOR DE LÍNEAS
            br = new BufferedReader(new FileReader("peliculas.txt"));

            while (br.readLine() != null){
                contadorSaltos ++;
            }

            //ELECCIÓN DE TÍTULO
            int aleatorio = (int)(Math.random() * contadorSaltos);
            br = new BufferedReader(new FileReader("peliculas.txt"));

            titulo = br.readLine();
            while (titulo != null && aleatorio!=0){
                aleatorio --;
                if (aleatorio == 0){
                    break;
                }
                titulo = br.readLine();
            }

            aciertos = new boolean[titulo.length()];

            for (int k = 0; k < aciertos.length; k++){
                if (titulo.charAt(k) == ' ' || titulo.charAt(k) == ':' || titulo.charAt(k) == '-'){
                    aciertos[k] = true;
                }
                else{
                    aciertos[k] = false;
                }
            }
        }
        catch (FileNotFoundException ex) {
            //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al abrir fichero");
        }
        catch (IOException ex) {
            //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al leer fichero");
        }
        finally{
            if (br != null){
                try {
                    br.close();
                }
                catch (IOException ex) {
                    //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Error al cerrar fichero");
                }
            }
        }
        /* Relación letrasMarcadas[x] - caracter que representa
        
        A  B  C  D  E  F  G  H  I  J  K  L
        0  1  2  3  4  5  6  7  8  9  10 11
        
        M  N  Ñ  O  P  Q  R  S  T  U  V  W
        12 13 14 15 16 17 18 19 20 21 22 23
        
        X  Y  Z  0  1  2  3  4  5  6  7  8  9
        24 25 26 27 28 29 30 31 32 33 34 35 36
        */
        puntos = 1000;
        dificultad = 1;
        letrasMarcadas = new boolean[37];
        for (int k = 0; k < letrasMarcadas.length; k++){
            letrasMarcadas[k] = false;
        }
        
    }
    
    //getters
    public boolean[] getAciertos(){
        return aciertos;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public int getDificultad(){
        return dificultad;
    }
    
    public boolean[] getLetrasMarcadas(){
        return letrasMarcadas;
    }
    
    public void setDificultad(int n){
        dificultad = n;
    }
    
    public String encriptar(){
        
        String r = "";
        for (int k = 0; k < aciertos.length; k++){
            if (aciertos[k] == true){
                r += titulo.charAt(k);
            }
            else{
                r += "*";
            }
        }
        
        return r;
    }
    
    /*
    public static void main (String[] args){
        
        Partida p = new Partida();
        System.out.println(p.encriptar());
        
        
    }*/
}