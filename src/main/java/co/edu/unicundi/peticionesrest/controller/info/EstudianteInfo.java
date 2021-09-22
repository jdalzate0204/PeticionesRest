package co.edu.unicundi.peticionesrest.controller.info;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.*;

/**
 *
 * @author James Daniel Alzate Rios
 * @author Paula Alejandra Guzman Cruz
 */
public class EstudianteInfo implements Serializable{
    
    /*@NotNull
    @Size (min = 7, max = 10)
    @Pattern(regexp = "/^([0-9])*$/")*/
    private String cedula;
    
    /*@NotNull (message = "Por favor, ingrese el nombre")
    @Size (min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    @Pattern(regexp = "/^[a-zA-Z]*$/", message = "Nombre solo admite letras")*/
    private String nombre;
    
    /*@NotNull (message = "Por favor, ingrese el apellido")
    @Size (min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
    @Pattern(regexp = "/^[a-zA-Z]*$/", message = "Apellido solo admite letras")*/
    private String apellido;
    
    /*@NotNull (message = "Porfavor, ingrese la edad")
    @Min (18)
    @Max (99)
    @Size (max = 2, message = "La edad no debe superar los 2 caracteres")
    @Pattern(regexp = "/^([0-9])*$/", message = "Edad solo admite numeros")*/
    private Integer edad;
    
    /*@NotNull (message = "Porfavor, ingrese el correo")
    @Pattern (regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Ingrese un formato de correo valido")*/
    private String correo;
    
    /*@NotNull (message = "Porfavor, ingrese el semestre en numero")
    @Min (1)
    @Max (10)
    @Size (max = 2, message = "El semestre no debe superar los 2 caracteres")
    @Pattern(regexp = "/^([0-9])*$/", message = "Semestre solo admite numeros")*/
    private int semestre;
    
    /*@NotNull (message = "Porfavor, ingrese las materias")
    @Max (5)*/
    private List<String> listaMaterias;
    
    /*@NotNull (message = "Porfavor, ingrese los numeros")
    @Min (2)
    @Max (7)
    @Pattern(regexp = "/^([0-9])*$/", message = "El vector numeros solo admite numeros")*/
    private int[] numeros;

    public EstudianteInfo() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    } 

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public List<String> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<String> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public int[] getNumeros() {
        return numeros;
    }

    public void setNumeros(int[] numeros) {
        this.numeros = numeros;
    }

    /*@Override
    public String toString(){ //Recorrer los datos para concatenar
        
        return cedula + " " + nombre + " " + apellido + " " + edad + " " + correo + " " + semestre;
    }*/
}
