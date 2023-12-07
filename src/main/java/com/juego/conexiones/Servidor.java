package com.juego.conexiones;

import com.juego.interfaz.Gui;
import com.juego.modelo.Jugador;
import com.juego.modelo.Movimiento;
import com.juego.modelo.PosicionAsignada;
import com.juego.modelo.Tablero;

import java.awt.*;
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

    /**
     * Constructor que recibe un puerto en el que va a recibir las conexiones
     * @param puerto
     */
    public Servidor(int puerto){
        this.puerto = puerto;
        tablero = new Tablero();
    }

    /**
     * Metodo que inicializa el servidor y espera dos conexiones,
     * ademas de crear las entradas y salidas hacia los jugadores
     */
    public void iniciarServidor(){
        try {
            serverSocket = new ServerSocket(9099);
            System.out.println("Servidor Iniciado");

            socketJugador1 = serverSocket.accept();
            salidaj1 = new ObjectOutputStream(socketJugador1.getOutputStream());
            entradaj1 = new ObjectInputStream(socketJugador1.getInputStream());
            System.out.println("Jugador 1 conectado");


            socketJugador2 = serverSocket.accept();
            salidaj2 = new ObjectOutputStream(socketJugador2.getOutputStream());
            entradaj2 = new ObjectInputStream(socketJugador2.getInputStream());
            System.out.println("Jugador 2 conectado");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * metodo para esperar los movimientos de los jugadores por turnos
     */
    public void esperarMovimientos(){
        var fila = 0;
        while (true){
            try {
                var movimientoj1 = (Movimiento)entradaj1.readObject();
                if((fila = tablero.colocarFicha(movimientoj1.getFicha(), movimientoj1.getColumna())) >= 0){
                    enviarMovimientos(fila, movimientoj1.getColumna(), movimientoj1.getFicha());
                }
                enviarResultados();

                var movimientoj2 = (Movimiento)entradaj2.readObject();
                if((fila = tablero.colocarFicha(movimientoj2.getFicha(), movimientoj2.getColumna())) >= 0){
                    enviarMovimientos(fila, movimientoj2.getColumna(), movimientoj2.getFicha());
                }
                enviarResultados();


            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * Este metodo envia los movimientos realizados a los jugadores
     * @param fila
     * @param columna
     * @param color
     */
    private void enviarMovimientos(int fila, int columna, Color color) {
        try {
            PosicionAsignada pos = new PosicionAsignada(fila, columna, color);
            salidaj1.writeObject(pos);
            salidaj2.writeObject(pos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * este metodo revisa si existe un ganador o empate
     * @throws IOException
     */

    public void enviarResultados() throws IOException {
        Color color = null;
        if((color = tablero.hayGanador()) != null){
            PosicionAsignada resultado = new PosicionAsignada(color);

            salidaj1.writeObject(resultado);
            salidaj2.writeObject(resultado);

        }else if(tablero.hayEmpate()){
            PosicionAsignada empate = new PosicionAsignada(true);
            //System.out.println(empate.getEmpate());
            salidaj1.writeObject(empate);
            salidaj2.writeObject(empate);
        }
    }

}
