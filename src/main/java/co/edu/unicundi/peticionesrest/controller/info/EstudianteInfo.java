package co.edu.unicundi.peticionesrest.controller.info;

import java.io.Serializable;
import java.util.*;
import javax.validation.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author James Daniel Alzate Rios
 * @author Paula Alejandra Guzman Cruz
 */
public class EstudianteInfo implements Serializable {
    
    //@NotNull (message = "¡Se requiere la cedula!")
    @Size (min = 7, max = 10, message = "¡Debe tener un tamaño entre 7 y 10 caracteres!")
    @Pattern(regexp = "^\\d+$", message = "¡Solo se admiten numeros!")
    private String cedula;
    
    //@NotBlank (message = "¡Se requiere el nombre!")
    @Size (min = 3, max = 20, message = "¡Debe tener un tamaño entre 3 y 20 caracteres!")
    @Pattern(regexp = "^[a-zA-Z_]+( [a-zA-Z_]+)*$", message = "¡Solo se admiten letras!")
    private String nombre;
    
    //@NotBlank (message = "¡Se requiere la apellido!")
    @Size (min = 3, max = 20, message = "¡Debe tener un tamaño entre 3 y 20 caracteres!")
    @Pattern(regexp = "^[a-zA-Z_]+( [a-zA-Z_]+)*$", message = "¡Solo se admiten letras!")
    private String apellido;
    
    @Range (min = 18, max = 99, message = "¡Debe estar en el rango entre 18 y 99 años!")
    private Integer edad;
    
    //@NotBlank (message = "¡Se requiere el correo!")
    @Pattern (regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", message = "¡Ingrese un formato valido!")
    private String correo;
    
    @Range (min = 1, max = 10, message = "¡Debe estar en el rango entre 1 y 10!")
    private Integer semestre;
    
    //No nulo (Validación personalizada)
    @Size(min = 1, max = 5, message = "¡Debe ingresar entre 1 y 5 materias!")
    private List<String> listaMaterias;
    
    @NotNull (message = "¡Se requieren numeros!")
    @Size (min = 2, max = 7, message = "¡Debe ingresar entre 2 y 7 numeros!")
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

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
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

    //Envia la intancia para validar si tiene alguna violación 
    public Set<ConstraintViolation<EstudianteInfo>> validar(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator.validate(this);
    }
}
