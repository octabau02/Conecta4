package com.juego.interfaz;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    /**
     * metodo para inicializar las caractersticas del menu como:
     * titulo, tamaño, botones, etc.
     */
    public Menu() {
        setTitle("Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JButton btnVsMaquina = new JButton("Jugar contra la Máquina");
        JButton btnVsPersona = new JButton("Jugar con Otra Persona");

        /**
         * Acciones a hacer clic al boton "Jugar contra la Máquina"
         */
        btnVsMaquina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JOptionPane.showMessageDialog(null,"En progreso");
            }
        });

        /**
         * Acciones a hacer clic al boton "Jugar con Otra Persona"
         */
        btnVsPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Gui gui = new Gui();
                gui.iniciarJugador();
                gui.inicializar();
                gui.empezarEscucha();
            }
        });

        JPanel panelBotones = new JPanel(new GridLayout(2, 1));
        panelBotones.add(btnVsMaquina);
        panelBotones.add(btnVsPersona);
        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

}
