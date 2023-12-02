package com.juego.modelo;

import com.juego.conexiones.Conexion;
import com.juego.interfaz.Gui;

public class Jugador extends Gui{
    private Conexion conexion;

    private String nombre;
    private Ficha colorFicha;

    public Jugador(String nombre, Ficha colorFicha){
        this.nombre = nombre;
        this.colorFicha = colorFicha;
    }

    public Ficha getColorFicha() {
        return colorFicha;
    }

    public void conectarAlServidor(String host, int puerto){
        conexion = new Conexion();
        conexion.conectarAServidor(host, puerto);
    }


}
