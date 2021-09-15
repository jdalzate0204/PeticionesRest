package co.edu.unicundi.peticionesrest.controller.info;

/**
 *
 * @author James Daniel Alzate Rios
 * @author Paula Alejandra Guzman Cruz
 */
public class EstudianteMetodo {
    public void datos(EstudianteInfo estudiante){
        estudiante.getApellido();
        estudiante.getCedula();
        estudiante.getCorreo();
        estudiante.getEdad();
        estudiante.getNombre();
        estudiante.getSemestre();
        estudiante.getListaMaterias();
        estudiante.getNumeros();
    }
}
