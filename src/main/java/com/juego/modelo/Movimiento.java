package com.juego.modelo;

import java.io.Serializable;

public class Movimiento implements Serializable {
    Ficha ficha;
    int columna;
    public Movimiento(Ficha colorFicha, int columna){
        this.ficha = colorFicha;
        this.columna = columna;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public int getColumna() {
        return columna;
    }
}
