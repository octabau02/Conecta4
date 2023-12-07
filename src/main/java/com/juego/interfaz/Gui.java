package com.juego.interfaz;

import com.juego.modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {
    private JButton[][] tableroBotones;
    private static final int ALTURA = 800, ANCHO = 600;
    private static final int FILAS = 6, COLUMNAS = 7;
    private Jugador jugador;

    /**
     * metodo que solicita mediante interfaz grafica
     * el nombre y color del jugador para inicializar al jugador.
     * aqui viene asignada la ip y puerto al que se va a conectar el jugador
     */
    public void iniciarJugador() {
        var nombre = obtenerNombre();
        var color = obtenerColor();
        jugador = new Jugador(nombre, color);
        jugador.conectarAlServidor("localhost", 9099);
    }

    /**
     * pide un nombre y lo retorna
     * @return
     */
    private String obtenerNombre() {
        return JOptionPane.showInputDialog("ingresa tu nombre");
    }

    /**
     * pide un color y lo retorna en forma del objeto Color
     * @return
     */
    private Color obtenerColor(){
        String[] opciones = {"Rojo", "Azul", "Verde"};

        var colorSel = (String)JOptionPane.showInputDialog(null,
                "Elige un color", "Eleccion", JOptionPane.QUESTION_MESSAGE, null,
                opciones,opciones[1]);

        switch (colorSel){
            case "Rojo":
                return Color.red;
            case "Azul":
                return Color.blue;
            case "Verde":
                return Color.green;
            default:
                return Color.GRAY;
        }
    }

    /**
     * metodo para iniciar las caracteristicas de la interfaz
     * del tablero como ancho, alto y botones
     */
    public void inicializar(){
        setTitle("Conecta 4 "+ jugador.getName());
        setSize(ALTURA, ANCHO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(FILAS, COLUMNAS));

        tableroBotones = new JButton[FILAS][COLUMNAS];
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {

                tableroBotones[f][c] = new JButton();
                tableroBotones[f][c].setBackground(Color.WHITE);
                tableroBotones[f][c].addActionListener(this);
                add(tableroBotones[f][c]);
            }
        }

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * metodo para detectar el boton que se pulso y enviarlo al servidor
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if(e.getSource() == tableroBotones[f][c]){
                    jugador.enviarMovimiento(c);
                    break;
                }
            }
        }
    }

    /**
     * metodo para esperar mensajes del servidor,
     * actualizar la interfaz grafica y comparar
     * si hay ganador, perdedor o empate
     */
    public void empezarEscucha(){

        Thread hiloEscucha = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    var posicionAsignada = jugador.actualizarMovimientos();

                    if(posicionAsignada.getEmpate()){
                        JOptionPane.showMessageDialog(null, "Hubo un empate");
                        dispose();
                    }

                    if (posicionAsignada.getColorGanador() == null) {
                        tableroBotones[posicionAsignada.getFila()][posicionAsignada.getColumna()]
                                .setBackground(posicionAsignada.getColor());

                    }else{

                        if(jugador.getColorFicha().equals(posicionAsignada.getColorGanador())){
                            JOptionPane.showMessageDialog(null, "Ganaste "+ jugador.getName());
                        }else{
                            JOptionPane.showMessageDialog(null, "Perdiste "+ jugador.getName());
                        }
                        dispose();
                    }
                }

            }
        });
        hiloEscucha.start();

    }



}