
package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;


public class Departamento {
    private String nombre;
    private EspecialidadMedica especialidad;
    private Hospital hospital;
    private List<Medico> medicos;
    private List<Sala> salas;

    public Departamento(String nombre, EspecialidadMedica especialidad) {
        this.nombre = Validaciones.textoObligatorio(nombre, "El nombre no puede estar vacio");
        Validaciones.noNulo(especialidad, "La especialidad no puede ser nula");
        this.especialidad = especialidad;
        this.medicos = new ArrayList<>();
        this.salas = new ArrayList<>();
        
    }
    
    public void setHospital(Hospital hospital){
        Validaciones.noNulo(hospital, "El Hospital no puede ser nulo");
        if(hospital != this.hospital){
            Hospital hospitalAntiguo = this.hospital;
            this.hospital= null;
            this.hospital = hospital;
            if(hospitalAntiguo !=null && hospitalAntiguo.getInternalDepartamentos().contains(this)){
                hospitalAntiguo.getInternalDepartamentos().remove(this);
            }
        }
        if(!hospital.getInternalDepartamentos().contains(this)){
            
            hospital.agregarDepartamento(this);
        }
    }
    
    public void agregarMedico(Medico medico){
        Validaciones.noNulo(medico, "El medico no puede ser nulo");
        if(medico.getEspecialidad() != this.especialidad){
            throw new IllegalArgumentException("El medico debe ser de la misma especialidad que el departamento");
        }
        if(!medicos.contains(medico)){
            medicos.add(medico);
        }
        if(medico.getDepartamento() != this){
            medico.setDepartamento(this);
        }
    }

    public Sala crearSala(String numero, String tipo){
        
        Sala nuevaSala = new Sala(numero, tipo, this);
        this.salas.add(nuevaSala);
        return nuevaSala;
    }
    
    public Hospital getHospital() {
        return hospital;
    }
    
    List<Medico> getInternalMedicos(){
        return medicos;
    }
    
    public List<Medico> getMedicos() {
        return Collections.unmodifiableList(medicos);
    }

    public List<Sala> getSalas() {
        return Collections.unmodifiableList(salas);
    }

    public String getNombre() {
        return nombre;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }
    
    
}
