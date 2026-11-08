
package org.jcr.entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HistoriaClinica {
    
    private String numeroHistoria;
    private Paciente paciente;
    private LocalDateTime fechaCreacion;
    private List<String> diagnostico;
    private List<String> tratamientos;
    private List<String> alergias;
    
    
    HistoriaClinica(Paciente paciente){
       this.paciente= paciente;
       this.fechaCreacion = LocalDateTime.now();
       this.numeroHistoria = generarNumeroHistoria(paciente);
       this.diagnostico = new ArrayList<>();
       this.tratamientos=new ArrayList<>();
       this.alergias = new ArrayList<>();
       
    }
    
    private String generarNumeroHistoria(Paciente paciente){
        String numeroHistoriaGenerado = "HC-" + paciente.getDni()+ "-" + fechaCreacion.getYear();
        return numeroHistoriaGenerado;
    }
    
    public void agregarDiagnostico(String diagnostico){
        if(!diagnostico.isEmpty() && diagnostico!= null){
            this.diagnostico.add(diagnostico);
        }
    }

    public void agregarTratamiento(String tratamientos) {
        if(!tratamientos.isEmpty() && tratamientos!=null){
            
            this.tratamientos.add(tratamientos);
        }
    }

    public void agregarAlergia(String alergias) {
        if(!alergias.isEmpty() && alergias!=null){
            
            this.alergias.add(alergias);
        }
    }
    
    
    public String getNumeroHistoria() {
        return numeroHistoria;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public List<String> getDiagnostico() {
        return Collections.unmodifiableList(diagnostico);
    }

    public List<String> getTratamientos() {
        return Collections.unmodifiableList(tratamientos);
    }

    public List<String> getAlergias() {
        return Collections.unmodifiableList(alergias);
    }
    
}
