package com.juego.modelo;

import java.awt.*;
import java.io.Serializable;

/**
 * clase para enviar movimientos con ficha y columna
 */
public class Movimiento implements Serializable {
    Color ficha;
    int columna;

    /**
     * constructor para crear el objeto con color y columna
     * @param colorFicha
     * @param columna
     */
    public Movimiento(Color colorFicha, int columna){
        this.ficha = colorFicha;
        this.columna = columna;
    }

    /**
     * metodos para retornar atributos de la clase
     * @return
     */
    public Color getFicha() {
        return ficha;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public String toString() {
        return ficha + ", " + columna;
    }
}
