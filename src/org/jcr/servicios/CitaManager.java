
package org.jcr.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.jcr.entidades.Cita;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Sala;
import org.jcr.excepciones.CitaException;


public class CitaManager implements CitaService {
    
    private static final int HORAS_ENTRE_CITAS = 2;
    private List<Cita> citas;
    
    
    public CitaManager() {
        this.citas = new ArrayList<>();
    }
    
    private void validarCita(LocalDateTime fechaHora, double costo) throws CitaException{
        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new CitaException("La fecha y hora ya pasaron la cita no esta disponible");
        }
        if(costo<= 0){throw new CitaException("El costo de la cita no puede ser 0 o menor");}
    }
    
    private boolean esMedicoDisponible(Medico medico, LocalDateTime fechaHora){
        
        for(Cita medicoCita: medico.getCitas()){
            if(seSuperponen(medicoCita.getFechaHora(), fechaHora)){
                return false;
            }
        }
        return true;
    }
    
    private boolean esSalaDisponible(Sala sala, LocalDateTime fechaHora){
        for(Cita salaCita : sala.getCitas()){
            if(seSuperponen(salaCita.getFechaHora(), fechaHora)){
                return false;
            }
        }
        return true;
    }
    
    private boolean seSuperponen(LocalDateTime existente, LocalDateTime nueva){
        
        LocalDateTime dosHorasDespues, dosHorasAntes;
        dosHorasDespues = existente.plusHours(HORAS_ENTRE_CITAS);
        dosHorasAntes= existente.minusHours(HORAS_ENTRE_CITAS);
        
        if(nueva.isAfter(dosHorasDespues) || nueva.isEqual(dosHorasDespues)){
            return false;
        }
        if(nueva.isBefore(dosHorasAntes) || nueva.isEqual(dosHorasAntes)){
            return false;
        }
        
        return true;
    }
    
    @Override
    public Cita programarCita(Paciente paciente, Medico medico, Sala sala, LocalDateTime fechaHora, double costo) throws CitaException {
        if (paciente == null) {
            throw new CitaException("El paciente no puede ser nulo");
        }
        if (medico == null) {
            throw new CitaException("El médico no puede ser nulo");
        }
        if (sala == null) {
            throw new CitaException("La sala no puede ser nula");
        }
        if (fechaHora == null) {
            throw new CitaException("La fecha y hora no pueden ser nulas");
        }
        
        if(medico.getEspecialidad() != sala.getDepartamento().getEspecialidad()){
            throw new CitaException("El medico debe ser de la misma especialidad que la sala");
        }
        
        validarCita(fechaHora, costo);
        
        if(!esMedicoDisponible(medico, fechaHora)){
            throw new CitaException("El medico no esta disponible para el horaio seleccionado");
        }
        
        if(!esSalaDisponible(sala, fechaHora)){
            throw new CitaException("La sala no esta disponible para el horaio seleccionado");
        }
        
        
        Cita nuevaCitaValida = new Cita(paciente, medico, sala, fechaHora, costo);
        
        citas.add(nuevaCitaValida);
        medico.addCita(nuevaCitaValida);
        paciente.addCita(nuevaCitaValida);
        sala.addCita(nuevaCitaValida);
        
        return nuevaCitaValida;
         
    }

    @Override
    public List<Cita> getCitasPorPaciente(Paciente paciente) {
        return paciente.getCitas();
    }

    @Override
    public List<Cita> getCitasPorMedico(Medico medico) {
        return medico.getCitas();
    }

    @Override
    public List<Cita> getCitasPorSala(Sala sala) {
        return sala.getCitas();
    }
    
    
    
}
