package com.juego.binaries;

import com.juego.conexiones.Servidor;

public class binServidor {
    /**
     * Metodo para iniciar el servidor
     * @param args
     */
    public static void main(String[] args) {
        Servidor s = new Servidor(9099);
        s.iniciarServidor();
        s.esperarMovimientos();
    }
}
