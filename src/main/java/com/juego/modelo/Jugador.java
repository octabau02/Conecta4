package com.juego.modelo;

import com.juego.conexiones.Conexion;
import com.juego.interfaz.Gui;

import java.awt.*;

public class Jugador {
    private Conexion conexion;

    private String nombre;
    private Color colorFicha;

    /**
     * constructor que recibe un nombre y el color de la ficha
     * @param nombre
     * @param colorFicha
     */
    public Jugador(String nombre, Color colorFicha){
        this.nombre = nombre;
        this.colorFicha = colorFicha;
    }

    public String getName(){
        return this.nombre;
    }

    public Color getColorFicha() {
        return colorFicha;
    }

    /**
     * metodo para conectarse al servidor con host y puerto
     * @param host
     * @param puerto
     */
    public void conectarAlServidor(String host, int puerto){
        conexion = new Conexion();
        conexion.conectarAServidor(host, puerto);
    }

    /**
     * metodo para enviar un movimiento en la columna seleccionada
     * @param c
     */

    public void enviarMovimiento(int c){
        conexion.enviarMovimiento(this.colorFicha, c);
    }

    /**
     * metodo que retorna una posicion asignada a la interfaz
     * @return
     */
    public PosicionAsignada actualizarMovimientos(){
        return conexion.esperarMovimientos();
    }

}
