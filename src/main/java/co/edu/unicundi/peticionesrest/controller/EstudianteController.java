package co.edu.unicundi.peticionesrest.controller;

import co.edu.unicundi.peticionesrest.controller.info.*;
import java.io.*;
import java.util.*;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*; 

/**
 *
 * @author James Daniel Alzate Rios
 * @author Paula Alejandra Guzman Cruz
 */
@Stateless
@Path("/estudiantes")
public class EstudianteController {
    EstudianteInfo estudiante = new EstudianteInfo();
    List<EstudianteInfo> listaEstudiante;
    File archivo = new File("C:/Users/acer/Documents/NetBeansProjects/PeticionesRest/ArchivoEstudiantes.txt");
    File archivoTemp = new File("C:/Users/acer/Documents/NetBeansProjects/PeticionesRest/Temporal.txt");
    EstudianteMetodo em = new EstudianteMetodo();
    
    @POST
    @Path("/agregar") 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response agregar(EstudianteInfo est) throws FileNotFoundException {
        listaEstudiante =  new ArrayList<>();
        
        if (archivo.exists() && archivo.length() > 0){
            try {
                FileOutputStream file = new FileOutputStream(archivo, true);
                MiObjectOutputStream oos = new MiObjectOutputStream(file);

                em.datos(est);
                listaEstudiante.add(est);

                oos.writeObject(listaEstudiante);
                oos.close();
                file.close();

                return Response.status(Response.Status.CREATED).entity("Registrado Exitosamente").build();             
            } catch (IOException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
            }
        } else{
            try {
                FileOutputStream file = new FileOutputStream(archivo, true);
                ObjectOutputStream oos = new ObjectOutputStream(file);

                em.datos(est);
                listaEstudiante.add(est);

                oos.writeObject(listaEstudiante);
                oos.close();
                file.close();

                return Response.status(Response.Status.CREATED).entity("Registrado Exitosamente").build();
            } catch (IOException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
            }
        }
    }
    
    @GET
    @Path("/mostrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostrar(){
        listaEstudiante = new ArrayList<>();
        
        try {
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(file);
            
            try {
                while(true){
                    listaEstudiante = (List) ois.readObject();
                    for (EstudianteInfo e : listaEstudiante){
                        estudiante = e;
                    }
                    return Response.status(Response.Status.OK).entity(estudiante).build();
                }
            } catch (IOException | ClassNotFoundException e) {
            }
            
            ois.close();
            file.close();
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    
    @GET
    @Path("/mostrarPorCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostrarPorCedula(@PathParam("cedula") String cedula){
        estudiante = null; 
        listaEstudiante = new ArrayList<>();
        
        try {
            FileInputStream file = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(file);
            
            try {
                while(true){
                    listaEstudiante = (List) ois.readObject();
                    for(EstudianteInfo e : listaEstudiante){
                        if(cedula.equals(e.getCedula())){
                            estudiante = e;
                        }
                    }
                    
                }
            }catch (IOException | ClassNotFoundException e) {
            }
            ois.close();
            file.close();
            
            if(estudiante != null)
                return Response.status(Response.Status.OK).entity(estudiante).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("Registro no existente").build();
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
    
    @PUT 
    @Path("/modificarPorCedula/{cedula}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificar(@PathParam("cedula") String cedula, EstudianteInfo est){
        listaEstudiante = new ArrayList<>();
        List<EstudianteInfo> lista = new ArrayList<>();
        
        try {
            FileInputStream fileIS = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fileIS);
            
            FileOutputStream fileOS = new FileOutputStream(archivoTemp);
            ObjectOutputStream oos = new ObjectOutputStream(fileOS);
            
            try {
                while(true){
                    listaEstudiante = (List) ois.readObject();
                    for(EstudianteInfo e : listaEstudiante){
                        if(cedula.equals(e.getCedula())){
                            em.datos(est);
                            lista.add(est);
                            oos.writeObject(lista); 
                        }
                        if(!cedula.equals(e.getCedula()))
                            oos.writeObject(listaEstudiante);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
            }
            oos.close();
            ois.close();
            
            archivo.delete();
            archivoTemp.renameTo(archivo);
            
            return Response.status(Response.Status.OK).entity("Registro Modificado Exitosamente").build();
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } 
    }
    
    @DELETE
    @Path("/eliminarPorCedula/{cedula}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("cedula") String cedula){
        listaEstudiante = new ArrayList<>();
        
        try {
            FileInputStream fileIS = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fileIS);
            
            FileOutputStream fileOS = new FileOutputStream(archivoTemp);
            ObjectOutputStream oos = new ObjectOutputStream(fileOS);
            
            try {
                while(true){
                listaEstudiante = (List) ois.readObject();
                for(EstudianteInfo e : listaEstudiante){
                    if(!cedula.equals(e.getCedula())){
                       oos.writeObject(listaEstudiante);
                    }
                }
            } 
            } catch (IOException | ClassNotFoundException e) {
            }
            
            oos.close();
            ois.close();
            
            archivo.delete();
            archivoTemp.renameTo(archivo);
            
            return Response.status(Response.Status.NO_CONTENT).build(); 
        } catch (IOException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        } 
    }
}
