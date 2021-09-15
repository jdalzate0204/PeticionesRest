package co.edu.unicundi.peticionesrest.controller;

import java.io.*;

/**
 *
 * @author James Daniel Alzate Rios
 * @author Paula Alejandra Guzman Cruz
 */
public class MiObjectOutputStream extends ObjectOutputStream  {
    //Sobrescribimos el método que crea la cabecera
    protected void writeStreamHeader() throws IOException{
        // No hacer nada.
    }
 
    //Constructores
    public MiObjectOutputStream () throws IOException{
        super();
    }
    public MiObjectOutputStream(OutputStream out) throws IOException{
                super(out);
    }
}
