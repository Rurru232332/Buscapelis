/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscapelis;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Alumno Tarde
 */
public class Reto implements Serializable{

    /**
     * @param args the command line arguments
     */
    
    
    private String tituloOriginal;
    private String tituloSinTildes;
    private boolean aciertos[];
    private int puntos;
    private int dificultad;
    private Partida partida;
    private Properties prop;
    
    public Reto(Partida p){
        prop = new Properties();
        try {
            prop.load(Files.newInputStream(Path.of("propiedades.properties")));
        }
        catch (IOException ex) {
            //Logger.getLogger(MainSerializacion.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se pudo abrir el fichero de properties");
        }
        partida = p;
        tituloOriginal = p.getTitulo();
        aciertos = p.getAciertos();
        puntos = p.getPuntos();
        dificultad = p.getDificultad();
        sinTildes();
    }
    
    public boolean comprobarTitulo (String tit){
        
        dificultad = partida.getDificultad();
        if (tit.equals(tituloSinTildes)){
            return true;
        }
        else{
            puntos = puntos - (Integer.parseInt(prop.getProperty("penalizacionBaseComprobar")) * dificultad);
            return false;
        }
    }
    
    public void introducirLetra (char letra){ //por cada letra que marcas te quita puntos
        
        dificultad = partida.getDificultad();
        for (int k = 0; k<aciertos.length; k++){
            
            if (tituloSinTildes.charAt(k) == letra){
                aciertos[k] = true;
            }
        }
        
        puntos -= (Integer.parseInt(prop.getProperty("penalizacionBaseLetra")) * dificultad);
    }
    
    protected void sinTildes(){
        
        tituloSinTildes = "";
        for (int k = 0; k< tituloOriginal.length(); k++){
            if (tituloOriginal.charAt(k) == 'a' || tituloOriginal.charAt(k) == '??' || tituloOriginal.charAt(k) == '??'){
                tituloSinTildes += "A";
            }
            else if (tituloOriginal.charAt(k) == 'e' || tituloOriginal.charAt(k) == '??' || tituloOriginal.charAt(k) == '??'){
                tituloSinTildes += "E";
            }
            else if (tituloOriginal.charAt(k) == 'i' || tituloOriginal.charAt(k) == '??' || tituloOriginal.charAt(k) == '??'){
                tituloSinTildes += "I";
            }
            else if (tituloOriginal.charAt(k) == 'o' || tituloOriginal.charAt(k) == '??' || tituloOriginal.charAt(k) == '??'){
                tituloSinTildes += "O";
            }
            else if (tituloOriginal.charAt(k) == 'u' || tituloOriginal.charAt(k) == '??' || tituloOriginal.charAt(k) == '??'){
                tituloSinTildes += "U";
            }
            else{
                tituloSinTildes += tituloOriginal.charAt(k);
            }
        }
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public String getTituloSinTildes() {
        return tituloSinTildes;
    }

    public boolean[] getAciertos() {
        return aciertos;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getDificultad() {
        return dificultad;
    }
    
    /*
    public static void main (String[] args){
        Partida p = new Partida();
        Reto r = new Reto(p);
        
        System.out.println(r.getTituloOriginal());
        System.out.println(r.getTituloSinTildes());
        System.out.println((int)'9');
    }*/
}
