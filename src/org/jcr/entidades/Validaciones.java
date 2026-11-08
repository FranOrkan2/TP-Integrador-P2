
package org.jcr.entidades;


public class Validaciones {
    
    static String textoObligatorio(String valor, String mesajeError){
        if(valor == null || valor.trim().isEmpty()){
            throw new IllegalArgumentException(mesajeError);
        }
        else return valor;
        
    }
    
    static void noNulo(Object valor, String mesajeError){
        if(valor == null){
            throw new IllegalArgumentException(mesajeError);
        }
    }
    
    static boolean sonDigitos(String texto){
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (!Character.isDigit(c)) {
                return false;  
            }
        }
        return true;
    }
}
