
package org.jcr.entidades;

import java.time.LocalDateTime;
import org.jcr.enums.EstadoCita;


public class Cita {
    
    private Paciente paciente;
    private Medico medico;
    private Sala sala;
    private LocalDateTime fechaHora;
    private double costo;
    private EstadoCita estado;
    private String observaciones;

    public Cita(Paciente paciente, Medico medico, Sala sala, LocalDateTime fechaHora, double costo) {
        Validaciones.noNulo(paciente, "El paciente no puede ser nulo");
        Validaciones.noNulo(medico, "El Medico no puede ser nulo");
        Validaciones.noNulo(sala, "La Sala no puede ser nulo");
        Validaciones.noNulo(fechaHora, "La fecha y hora no pueden ser nulos");
        this.paciente = paciente;
        this.medico = medico;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.costo = costo;
        this.estado = EstadoCita.PROGRAMADA;
        this.observaciones ="";
        
    }

    public void setEstado(EstadoCita estado) {
        Validaciones.noNulo(estado, "El estado no puede ser nulo");
        this.estado = estado;
    }

    public void setObservaciones(String observaciones) {
        if(observaciones != null){
            this.observaciones = observaciones;
        }
        else{this.observaciones = "";}
    }
    

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getCosto() {
        return costo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public String getObservaciones() {
        return observaciones;
    }       
}
