
package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;


public class Medico extends Persona{
    
    private Matricula matricula;
    private EspecialidadMedica especialidad;
    private Departamento departamento;
    private List<Cita> citas;

    public Medico(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre, String numeroMatricula, EspecialidadMedica especialidad){
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.matricula = new Matricula(numeroMatricula);
        Validaciones.noNulo(especialidad, "La especialidad no puede ser nula");
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
    }

    public void setDepartamento(Departamento departamento) { 
        Validaciones.noNulo(departamento, "El departamento no puede ser nulo");
        if(this.especialidad != departamento.getEspecialidad()){
            throw new IllegalArgumentException("El Departamento debe ser de la misma especialidad que el medico");
        } 
        if(departamento != this.departamento){
            Departamento departamentoAntiguo = this.departamento;
            this.departamento = null;
            this.departamento = departamento;
            if(departamentoAntiguo != null && departamentoAntiguo.getInternalMedicos().contains(this)){
                departamentoAntiguo.getInternalMedicos().remove(this);
            }
        }
        if(!departamento.getInternalMedicos().contains(this)){
            departamento.agregarMedico(this);
        }
    }
    
    public Matricula getMatricula() {
        return matricula;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void addCita(Cita cita) {
        Validaciones.noNulo(cita, "La cita no puede ser nula");
        this.citas.add(cita);
    }
    
    public List<Cita> getCitas() {
        return Collections.unmodifiableList(citas);
    }
   
}
