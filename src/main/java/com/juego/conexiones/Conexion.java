package com.juego.conexiones;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Conexion {

    protected Socket socket;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private String host;
    private int puerto;

    public Conexion(){

    }


    public void conectarAServidor(String host, int puerto){
        this.host = host;
        this.puerto = puerto;
        try {
            socket = new Socket(host, puerto);
            entrada = new ObjectInputStream(socket.getInputStream());
            salida = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void esperarMensaje(){
        try {
            var mensaje = entrada.readObject();
            System.out.println(mensaje);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarObjeto(Object objeto){

        try {
            salida.writeObject(objeto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
