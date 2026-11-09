
package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Sala {
    private String numero;
    private String tipo;
    private Departamento departamento;
    private List<Cita> citas;

    Sala(String numero, String tipo, Departamento departamento) {
        this.numero = Validaciones.textoObligatorio(numero, "El numero no puede esta vacio");
        this.tipo = Validaciones.textoObligatorio(tipo, "El tipo no puede estar vacio");
        Validaciones.noNulo(departamento, "El departamento no puede ser nulo");
        this.departamento = departamento;
        this.citas = new ArrayList<>();
    }
    
    
    public void addCita(Cita cita){
        Validaciones.noNulo(cita, "La cita no puede ser nula");
        this.citas.add(cita);
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }
    
    
}
