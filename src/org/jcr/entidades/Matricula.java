
package org.jcr.entidades;


public class Matricula {
    private String numero;

    public Matricula(String numero) {
        this.numero = validarMatricula(numero);
    }

    public String getNumero() {
        return numero;
    }
    
    private String validarMatricula(String matricula){
        Validaciones.textoObligatorio(matricula, "La matricula no puede estar vacia");
        if(!matricula.startsWith("MP-")){
            throw new IllegalArgumentException("La matricula debe comenzar con 'MP-'");
        }
        if(matricula.length()< 7 || matricula.length()>9){
            throw new IllegalArgumentException("La matricula debe tener entre 4 y 6 caracteres numericos");
        }
        
        String parteNumerica = matricula.substring(3);
        if(!Validaciones.sonDigitos(parteNumerica)){
            throw new IllegalArgumentException("La matricula debe solo caracteres numericos luego de 'MP-'");
        }
        return matricula;
    }
}
