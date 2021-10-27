/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscapelis;

import java.io.Serializable;
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
    
    public Reto(Partida p){
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
            puntos = puntos - (50 * dificultad);
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
        
        puntos -= 10 * dificultad;
    }
    
    protected void sinTildes(){
        
        tituloSinTildes = "";
        for (int k = 0; k< tituloOriginal.length(); k++){
            if (tituloOriginal.charAt(k) == 'a' || tituloOriginal.charAt(k) == 'á' || tituloOriginal.charAt(k) == 'Á'){
                tituloSinTildes += "A";
            }
            else if (tituloOriginal.charAt(k) == 'e' || tituloOriginal.charAt(k) == 'é' || tituloOriginal.charAt(k) == 'É'){
                tituloSinTildes += "E";
            }
            else if (tituloOriginal.charAt(k) == 'i' || tituloOriginal.charAt(k) == 'í' || tituloOriginal.charAt(k) == 'Í'){
                tituloSinTildes += "I";
            }
            else if (tituloOriginal.charAt(k) == 'o' || tituloOriginal.charAt(k) == 'ó' || tituloOriginal.charAt(k) == 'Ó'){
                tituloSinTildes += "O";
            }
            else if (tituloOriginal.charAt(k) == 'u' || tituloOriginal.charAt(k) == 'ú' || tituloOriginal.charAt(k) == 'Ú'){
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
