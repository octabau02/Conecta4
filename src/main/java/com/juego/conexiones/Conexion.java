    package com.juego.conexiones;

    import com.juego.modelo.Movimiento;
    import com.juego.modelo.PosicionAsignada;

    import java.awt.*;
    import java.io.IOException;
    import java.io.ObjectInputStream;
    import java.io.ObjectOutputStream;
    import java.net.Socket;

    /**
     * clase para tener comunicacion hacia el servidor
     */
    public class Conexion {
        protected Socket socket;
        private ObjectInputStream entrada;
        private ObjectOutputStream salida;
        private String host;
        private int puerto;


        /**
         * Recibe un host y puerto a conectarse y crea las entradas y salidas para la comunicacion
         * @param host
         * @param puerto
         */
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

        /**
         * lee un objeto del jugador y lo retorna al jugador
         * @return
         */
        public PosicionAsignada esperarMovimientos(){
            PosicionAsignada pos;
            try {
                pos = (PosicionAsignada)entrada.readObject();
                return pos;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * envia movimientos al servidor
         * @param colorFicha
         * @param columna
         */
        public void enviarMovimiento(Color colorFicha, int columna){

            try {
                Movimiento movimiento = new Movimiento(colorFicha, columna);
                salida.writeObject(movimiento);
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
