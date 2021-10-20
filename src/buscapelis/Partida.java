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
 * @author Alumno Tarde
 */
public class Partida {
    
    private boolean[] aciertos;
    private int puntos;
    private String titulo;
    
    public Partida (String ruta){ //constructor para cargar una partida
        
    }
    
    public Partida (){ //Constructor para una partida nueva

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
            //System.out.println(aleatorio);
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
        
        for (int k = 0; k<10000; k++){
            Partida p = new Partida();
            System.out.println(p.getTitulo());
        }
    }
}