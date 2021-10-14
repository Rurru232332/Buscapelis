/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscapelis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno Tarde
 */
public class Partida {
    
    private boolean[] aciertos;
    private int puntos;
    private String titulo;
    
    public Partida (boolean nueva){
        if (nueva){
            
            FileReader fr = null;
            BufferedReader br = null;
            
            //Contador de líneas del archivo
            
            int contadorSaltos = 0;
            try {
                fr = new FileReader ("peliculas.txt");
                
                
                char caracterLeido = (char)fr.read();
                
                while (caracterLeido != '?'){
                    if (caracterLeido == '\r'){
                        contadorSaltos ++;
                        //System.out.println(contadorSaltos);
                    }
                    
                    caracterLeido = (char)fr.read();
                }
            }
            catch (FileNotFoundException ex) {
                //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error al abrir fr");
            }
            catch (IOException ex) {
                //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error al leer fichero fr");
            }
            finally{
                if (fr != null){
                    try {
                        fr.close();
                    }
                    catch (IOException ex) {
                        //Logger.getLogger(Partida.class.getName()).log(Level.SEVERE, null, ex);
                        System.err.println("Error al cerrar fichero fr");
                    }
                }
            }
            
            //Selección de título
            
            int aleatorio = (int)(Math.random() * contadorSaltos) -1;
            //System.out.print(aleatorio + " ");
            try {
                
                br = new BufferedReader(new FileReader("peliculas.txt"));
                titulo = br.readLine();
                while (titulo != null){
                    aleatorio --;
                    if (aleatorio == 0){
                        break;
                    }
                    titulo = br.readLine();
                }
                if (titulo == null){
                    /*
                    Esto es para que si por alguna razón el texto se queda en null,
                    se le asigne un título y el programa no crashee
                    Este título no aparece en el archivo peliculas.txt para que tenga
                    la misma probabilidad de aparecer que el resto
                    */
                    titulo = "LA MOSCA";
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
        }
        
        else{
            //cargar partida
        }
    }
    
    public boolean[] getAciertos(){
        return aciertos;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    public String getTitulo(){
        return titulo;
    }
    
    public static void main (String[] args){
        
            Partida p = new Partida(true);
            System.out.println(p.getTitulo());
    }
}
