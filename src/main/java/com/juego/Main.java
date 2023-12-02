package com.juego;

import com.juego.conexiones.Servidor;

public class Main {
    public static void main(String[] args) {
        Servidor servidor = new Servidor(9099);
        servidor.iniciarServidor();

    }
}