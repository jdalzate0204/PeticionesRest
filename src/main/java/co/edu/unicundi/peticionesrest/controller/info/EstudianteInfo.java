package co.edu.unicundi.peticionesrest.controller.info;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author James Daniel Alzate Rios
 * @author Paula Alejandra Guzman Cruz
 */
public class EstudianteInfo implements Serializable{
    //Interface Serializable (Comprime en el disco duro para liberar memoria RAM)
    
    private String cedula;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String correo;
    private int semestre;
    private List<String> listaMaterias;
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
