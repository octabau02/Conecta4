package com.juego.conexiones;

import com.juego.interfaz.Gui;
import com.juego.modelo.Jugador;
import com.juego.modelo.Movimiento;
import com.juego.modelo.Tablero;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    protected ServerSocket serverSocket;
    private Gui interfaz;
    private int puerto;
    protected Socket socketJugador1;
    private ObjectOutputStream salidaj1;
    private ObjectInputStream entradaj1;

    protected Socket socketJugador2;
    private ObjectOutputStream salidaj2;
    private ObjectInputStream entradaj2;

    private Tablero tablero;

    public Servidor(int puerto){
        this.puerto = puerto;
        tablero = new Tablero();
    }

    public void iniciarServidor(){
        try {
            serverSocket = new ServerSocket(9099);
            System.out.println("Servidor Iniciado");

            socketJugador1 = serverSocket.accept();
            salidaj1 = new ObjectOutputStream(socketJugador1.getOutputStream());
            System.out.println("Jugador 1 conectado");


            socketJugador2 = serverSocket.accept();
            salidaj2 = new ObjectOutputStream(socketJugador2.getOutputStream());
            System.out.println("Jugador 2 conectado");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarInterfaz(){
        interfaz = new Gui();
    }
}
