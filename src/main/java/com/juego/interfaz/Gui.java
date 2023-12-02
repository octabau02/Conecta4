package com.juego.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {
    private JButton[][] tableroBotones;
    private static final int ALTURA = 800, ANCHO = 600;
    private static final int FILAS = 6, COLUMNAS = 7;

    public Gui() {

        setTitle("Conecta 4");
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


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if(e.getSource() == tableroBotones[f][c]){
                    //System.out.println("columna: "+c);
                    //mostramos la columna

                    break;
                }
            }
        }
    }


}