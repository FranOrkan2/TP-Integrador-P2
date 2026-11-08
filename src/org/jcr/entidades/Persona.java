
package org.jcr.entidades;

import java.time.LocalDate;
import java.time.Period;
import org.jcr.enums.TipoSangre;


public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected LocalDate fechaNacimiento;
    protected TipoSangre tipoSangre;

    public Persona(String nombre, String apellido, String dni, LocalDate fechaNacimiento, TipoSangre tipoSangre) {
        this.nombre = Validaciones.textoObligatorio(nombre, "El nombre no puede estar vacio");
        this.apellido = Validaciones.textoObligatorio(apellido, "El apellido no puede estar vacio");
        this.dni = validarDni(dni);
        Validaciones.noNulo(fechaNacimiento, "La fecha de nacimiento no puede ser nula");
        this.fechaNacimiento = fechaNacimiento;
        Validaciones.noNulo(tipoSangre, "El tipo de sangre no puede ser nulo");
        this.tipoSangre=tipoSangre;
    }
    
    private String validarDni(String dni){
        Validaciones.textoObligatorio(dni, "El DNI no puede estar vacio");
        if(!Validaciones.sonDigitos(dni)){
            throw new IllegalArgumentException("El DNI solo puede contener nuemeros");
        }
        if(dni.length()!= 7 && dni.length()!=8){
            throw new IllegalArgumentException("El DNI solo puede tener entre 7 y 8 caracteres");
        }
        
        return dni;
    }
    
    public String getNombreCompleto(){
        return nombre + " " + apellido;
    }
    
    public int getEdad(){
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }
    
   
}
