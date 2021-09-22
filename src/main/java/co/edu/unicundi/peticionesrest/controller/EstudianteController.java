package co.edu.unicundi.peticionesrest.controller;

import co.edu.unicundi.peticionesrest.controller.info.*;
import com.wordnik.swagger.annotations.*;
import com.wordnik.swagger.jaxrs.JavaHelp;
import java.io.*;
import java.util.*;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.ws.rs.*;
import javax.ws.rs.core.*; 

/**
 *
 * @author James Daniel Alzate Rios
 * @author Paula Alejandra Guzman Cruz
 */
@Stateless
@Path("/estudiantes")
@Api(value = "/estudiantes", description = "Operaciones con estudiantes")
public class EstudianteController extends JavaHelp { 
    EstudianteInfo estudiante = new EstudianteInfo();
    List<EstudianteInfo> listaEstudiante;
    File archivo = new File("C:/Users/acer/Documents/NetBeansProjects/PeticionesRest/ArchivoEstudiantes.txt");
    File archivoTemp = new File("C:/Users/acer/Documents/NetBeansProjects/PeticionesRest/Temporal.txt");
    EstudianteMetodo em = new EstudianteMetodo(); 
    
    @POST
    @Path("/agregar")
    @ApiOperation(
            value = "Registra los estudiantes",
            notes = "Recibe los paremtros de:\n- cedula\n- nombre\n- apellido\n- edad\n- correo\n- semestre\n- listaMaterias (ArrayList)\n"
                    + "- numeros (vector)\nTodos los atributos se encuentran validados")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregar(@ApiParam(value = "Datos para el registro", required = true) 
                               EstudianteInfo est) {
        listaEstudiante =  new ArrayList<>();
        
        if (archivo.exists() && archivo.length() > 0) {
            try {
                HashMap<String, String> errores = new HashMap();
                
                for (ConstraintViolation error: est.validar())
                    errores.put(error.getPropertyPath().toString(), error.getMessage());
                
                if (errores.size() > 0)
                    return Response.status(Response.Status.BAD_REQUEST).entity(errores).build();
                else {
                    FileOutputStream file = new FileOutputStream(archivo, true);
                    MiObjectOutputStream oos = new MiObjectOutputStream(file);
                
                    em.datos(est);
                    listaEstudiante.add(est);

                    oos.writeObject(listaEstudiante);
                    oos.close();
                    file.close();

                    return Response.status(Response.Status.CREATED).entity(est.getNombre() + " Registrado Exitosamente").build();
                }      
            } catch (IOException e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            try {
                HashMap<String, String> errores = new HashMap();
                
                for (ConstraintViolation error: est.validar())
                    errores.put(error.getPropertyPath().toString(), error.getMessage());
                
                if (errores.size() > 0)
                    return Response.status(Response.Status.BAD_REQUEST).entity(errores).build();
                else {
                    FileOutputStream file = new FileOutputStream(archivo);
                    ObjectOutputStream oos = new ObjectOutputStream(file);

                    em.datos(est);
                    listaEstudiante.add(est);

                    oos.writeObject(listaEstudiante);
                    oos.close();
                    file.close();

                    return Response.status(Response.Status.CREATED).entity(est.getNombre() + " Registrado Exitosamente").build();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
    
    @GET
    @Path("/mostrar")
    @ApiOperation(
            value = "Lista todos los estudiantes registrados",
            notes = "Devuelve el registro de todos los estudiantes con sus atributos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostrar(){
        listaEstudiante = new ArrayList<>();
        
        if (archivo.exists()) {
            try {
                FileInputStream file = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(file);
            
                try {
                    while (true)
                        listaEstudiante.addAll((List) ois.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    
                    ois.close();
                    file.close();
                    return Response.status(Response.Status.OK).entity(listaEstudiante).build();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } else
            return Response.status(Response.Status.NOT_FOUND).entity("Archivo no encontrado").build();
    }
    
    @GET
    @Path("/mostrarPorCedula/{cedula : \\d+}")
    @ApiOperation(
            value = "Lista un estudiante en especifico",
            notes = "Recibe por la URL el parametro de cedula y devuelve el registro correspondiente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostrarPorCedula(@ApiParam(value = "Cedula del estudiante a buscar", required = true)
                                         @PathParam("cedula") 
                                         String cedula) {
        estudiante = null; 
        listaEstudiante = new ArrayList<>();
        
        if (archivo.exists()) {
            try {
                FileInputStream file = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(file);
            
                try {
                    while (true) {
                        listaEstudiante = (List) ois.readObject();
                        for(EstudianteInfo e : listaEstudiante){
                            if(cedula.equals(e.getCedula())){
                                estudiante = e;
                            }
                        } 
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    
                    ois.close();
                    file.close();
                    
                    if (estudiante != null)
                        return Response.status(Response.Status.OK).entity(estudiante).build();
                    else
                        return Response.status(Response.Status.NOT_FOUND).entity("Registro no existente").build();
                }           
            } catch (IOException e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } else
            return Response.status(Response.Status.NOT_FOUND).entity("Archivo no encontrado").build();
    }
    
    @PUT 
    @Path("/modificarPorCedula/{cedula : \\d+}")
    @ApiOperation(
            value = "Modifica algun dato de un estudiante en especifico",
            notes = "Recibe por la URL el parametro de cedula del registro a modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(@ApiParam(value = "Cedula del estudiante a buscar", required = true)
                                @PathParam("cedula") String cedula, 
                                EstudianteInfo est){
        listaEstudiante = new ArrayList<>();
        List<EstudianteInfo> lista = new ArrayList<>();
        
        if (archivo.exists()) {
            try {
                HashMap<String, String> errores = new HashMap();
                
                for (ConstraintViolation error: est.validar())
                    errores.put(error.getPropertyPath().toString(), error.getMessage());
                
                if (errores.size() > 0)
                    return Response.status(Response.Status.BAD_REQUEST).entity(errores).build();
                else {
                    FileInputStream fileIS = new FileInputStream(archivo);
                    ObjectInputStream ois = new ObjectInputStream(fileIS);

                    FileOutputStream fileOS = new FileOutputStream(archivoTemp);
                    ObjectOutputStream oos = new ObjectOutputStream(fileOS);

                    try {
                        while (true) {
                            listaEstudiante = (List) ois.readObject();
                            for (EstudianteInfo e : listaEstudiante) {
                                if (cedula.equals(e.getCedula())) {
                                    em.datos(est);
                                    lista.add(est);
                                    oos.writeObject(lista); 
                                }
                                if (!cedula.equals(e.getCedula()))
                                    oos.writeObject(listaEstudiante);
                            }
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();

                        oos.close();
                        ois.close();

                        archivo.delete();
                        archivoTemp.renameTo(archivo);

                        return Response.status(Response.Status.OK).entity("Registro de " + est.getNombre() + " Modificado Exitosamente").build();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } else
            return Response.status(Response.Status.NOT_FOUND).entity("Archivo no encontrado").build();
    }
    
    @DELETE
    @Path("/eliminarPorCedula/{cedula : \\d+}")
    @ApiOperation(
            value = "Elimina el regitro de un estudiante en especifico",
            notes = "Recibe por la URL el parametro de cedula del registro a eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(@ApiParam(value = "Cedula del estudiante a buscar", required = true)
                                @PathParam("cedula") String cedula){
        listaEstudiante = new ArrayList<>();
        
        if (archivo.exists()) {
            try {
                FileInputStream fileIS = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(fileIS);
            
                FileOutputStream fileOS = new FileOutputStream(archivoTemp);
                ObjectOutputStream oos = new ObjectOutputStream(fileOS);
            
                try {
                    while (true) {
                        listaEstudiante = (List) ois.readObject();
                        for (EstudianteInfo e : listaEstudiante) {
                            if (!cedula.equals(e.getCedula()))
                                oos.writeObject(listaEstudiante);
                        }
                    } 
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    
                    oos.close();
                    ois.close();
            
                    archivo.delete();
                    archivoTemp.renameTo(archivo);
            
                    return Response.status(Response.Status.NO_CONTENT).build(); 
                }
            } catch (IOException e) {
                e.printStackTrace();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            } 
        } else
            return Response.status(Response.Status.NOT_FOUND).entity("Archivo no encontrado").build();
    }
}