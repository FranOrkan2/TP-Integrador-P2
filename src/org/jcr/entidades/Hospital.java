
package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Hospital {
    private String nombre;
    private String direccion;
    private String telefono;
    
    private List<Departamento> departamentos;
    private List<Paciente> pacientes;

    public Hospital(String nombre, String direccion, String telefono) {
        this.nombre = Validaciones.textoObligatorio(nombre, "El nombre no puede estar vacio");
        this.direccion = Validaciones.textoObligatorio(direccion, "La direccion no estar vacia");
        this.telefono = Validaciones.textoObligatorio(telefono, "El telefono no puede estar vacio");
        this.departamentos = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }
    
   
    public void agregarDepartamento(Departamento departamento){
        Validaciones.noNulo(departamento, "El departamento no puede ser nulo");
        if(!departamentos.contains(departamento)){
            departamentos.add(departamento);
        }
        if(departamento.getHospital() != this){
            departamento.setHospital(this);
        }
    }
    
    public void agregarPaciente(Paciente paciente){
        Validaciones.noNulo(paciente, "El paciente no puede ser nulo");
        if(!pacientes.contains(paciente)){
            pacientes.add(paciente);
        }
        if(paciente.getHospital() != this){
            paciente.setHospital(this);
        }
    }

    public List<Departamento> getDepartamentos() {
        return Collections.unmodifiableList(departamentos);
    }

    public List<Paciente> getPacientes() {
        return Collections.unmodifiableList(pacientes);
    }
    
    List<Departamento> getInternalDepartamentos(){
        return departamentos;
    }
    
    List<Paciente> getInternalPacientes(){
        return pacientes;
    }
}
