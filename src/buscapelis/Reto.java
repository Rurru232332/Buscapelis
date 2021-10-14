/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscapelis;

import java.util.Scanner;

/**
 *
 * @author Alumno Tarde
 */
public class Reto {

    /**
     * @param args the command line arguments
     */
    
    
    String titulo;
    boolean aciertos[];
    
    public Reto(Partida p){
        titulo = p.getTitulo();
        aciertos = p.getAciertos();
    }
    
    private int comprobar (char letra){
        
        int letrasDesbloqueadas = 0;
        
        for (int k = 0; k<aciertos.length; k++){
            
            if (titulo.charAt(k) == letra){
                aciertos[k] = true;
                letrasDesbloqueadas ++;
            }
        }
        
        return letrasDesbloqueadas;
    }
    
    /*
    public static void main (String[] args){
        
        Reto r = new Reto();
        
        Scanner sc = new Scanner (System.in);
        
        int ac = 0;
        System.out.println(r.comprobar('4'));
        for (int k = 0; k<r.aciertos.length; k++){
            if (r.aciertos[k] == true){
                ac++;
            }
        }
        
        System.out.println(ac);
    }*/
}
