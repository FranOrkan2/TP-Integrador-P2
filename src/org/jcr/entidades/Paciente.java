
package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.TipoSangre;


public class Paciente extends Persona{
    
    private String telefono;
    private String direccion;
    private HistoriaClinica historiaClinica;
    private Hospital hospital;
    private List<Cita> citas;
    
    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre, String telefono, String direccion) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        Validaciones.textoObligatorio(telefono, "El telefono no puede contener un texto vacio");
        this.telefono = telefono;
        Validaciones.textoObligatorio(direccion, "La direccion no puede contener un texto vacio");
        this.direccion = direccion;
        this.citas= new ArrayList<>();
        this.historiaClinica = new HistoriaClinica(this);
    }
    
    public void addCita(Cita cita){
        Validaciones.noNulo(cita, "La cita no puede ser nula");
        citas.add(cita);
    }
    
    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }
    
    //verificar que el hostiptal cambia al paciente tambien
    public void setHospital(Hospital hospital) {
        Validaciones.noNulo(hospital, "El Hospital no puede ser nulo");
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }
    
    
    
}
