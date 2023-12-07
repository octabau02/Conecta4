package com.juego.modelo;

import java.awt.*;
import java.io.Serializable;

/**
 * Clase destinada para el manejo de filas, columnas, color, color ganador
 * entre el servidor y jugador
 */

public class PosicionAsignada implements Serializable {

    private int fila;
    private int columna;
    private Color color;
    private Color colorGanador;
    private boolean empate;

    /**
     * metodo para crear un objeto con el fin de mandarlo al jugador para que este actualice la interfaz
     * grafica
     * @param fila
     * @param columna
     * @param color
     */
    public PosicionAsignada(int fila, int columna, Color color){
        this.fila = fila;
        this.columna = columna;
        this.color = color;
        this.colorGanador = null;
    }

    /**
     * metodo para el colo ganador y enviarlos a los jugadores
     * @param color
     */

    public PosicionAsignada(Color color){
        this.colorGanador = color;
    }

    /**
     * metodo para indicar que hubo un empate
     * @param empate
     */
    public PosicionAsignada(Boolean empate){
        this.empate = empate;
    }

    /**
     * metodos para retornar atributos de esta clase
     * @return
     */
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Color getColor(){
        return color;
    }

    public Color getColorGanador(){ return colorGanador;}

    public boolean getEmpate(){
        return empate;
    }
}
