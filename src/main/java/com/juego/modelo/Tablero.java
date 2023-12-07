package com.juego.modelo;

import java.awt.*;

public class Tablero {
    private final int FILAS = 6;
    private final int COLUMNAS = 7;

    private Color[][] celdas;

    /**
     * constructor para poner las celdas del tablero en nulas
     */
    public Tablero(){
        this.celdas = new Color[FILAS][COLUMNAS];

        for(int f = 0; f< FILAS; f++){
            for(int c = 0; c< COLUMNAS; c++){
                this.celdas [f][c] = null;
            }
        }
    }

    public void mostrarTablero(){

        for(int f = 0; f< FILAS; f++){
            for(int c = 0; c< COLUMNAS; c++){
                System.out.print(celdas [f][c] + "|");
            }
            System.out.println();
        }
    }

    /**
     *metodo que recibe un color y columna para colocar una ficha,
     * donde se compara que sea una posicion valida y haya lugar disponible
     * @param colorFicha
     * @param columna
     * @return
     */
    public int colocarFicha(Color colorFicha, int columna){

        if(columna < 0 || columna >= COLUMNAS){
            System.out.println("Columna no valida");
            return 0;
        }
        for(int f = FILAS - 1; f >= 0; f--){
            if(celdas [f][columna] == null){
                celdas [f][columna] = colorFicha;
                return f;
            }
        }
        // la columna esta llena
        System.out.println("La columna esta llena");
        return 0;
    }

    /**
     * metodo que retorna el color de un ganador en caso de haberlo
     * @return
     */
    public Color hayGanador(){
        Color colorGanador;

        if((colorGanador = hayGanadorEnVertical()) != null){
            return colorGanador;
        }
        if((colorGanador = hayGanadorEnHorizontal()) != null){
            return colorGanador;
        }
        if((colorGanador = hayGanadorEnDiagonal()) != null){
            return colorGanador;
        }

        return null;
    }

    //metodo paar revisar si hay ganador
    private Color hayGanadorEnHorizontal() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna <= COLUMNAS - 4; columna++) {
                Color ficha = celdas[fila][columna];
                if (ficha != null && ficha.equals(celdas[fila][columna + 1]) &&
                        ficha.equals(celdas[fila][columna + 2]) && ficha.equals(celdas[fila][columna + 3])) {
                    return ficha;
                }
            }
        }
        return null;
    }

    //metodo paar revisar si hay ganador
    private Color hayGanadorEnVertical() {
        for (int fila = 0; fila <= FILAS - 4; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                Color ficha = celdas[fila][columna];
                if (ficha != null && ficha.equals(celdas[fila + 1][columna]) &&
                        ficha.equals(celdas[fila + 2][columna]) && ficha.equals(celdas[fila + 3][columna])) {

                    return ficha;
                }
            }
        }
        return null;
    }

    //metodo paar revisar si hay ganador
    private Color hayGanadorEnDiagonal() {
        // Verificar diagonales hacia la derecha y hacia la izquierda
        for (int fila = 0; fila <= FILAS - 4; fila++) {
            for (int columna = 0; columna <= COLUMNAS - 4; columna++) {
                // Diagonal hacia la derecha
                if (celdas[fila][columna] != null && celdas[fila][columna].equals(celdas[fila + 1][columna + 1]) &&
                        celdas[fila][columna].equals(celdas[fila + 2][columna + 2]) && celdas[fila][columna].equals(celdas[fila + 3][columna + 3])) {
                    return celdas[fila][columna];
                }

                // Diagonal hacia la izquierda
                if (celdas[fila][columna + 3] != null && celdas[fila][columna + 3].equals(celdas[fila + 1][columna + 2]) &&
                        celdas[fila][columna + 3].equals(celdas[fila + 2][columna + 1]) && celdas[fila][columna + 3].equals(celdas[fila + 3][columna])) {
                    return celdas[fila][columna];
                }
            }
        }
        return null;
    }

    /**
     * metodo que comprueba si hay empate y retorna un boolean
     */
    public boolean hayEmpate() {
        for (int columna = 0; columna < COLUMNAS; columna++) {
            if (celdas[0][columna] == null) {
                // Si hay al menos una celda vacía en la parte superior, no hay empate
                return false;
            }
        }
        // Si todas las celdas en la parte superior están ocupadas, hay empate
        return true;
    }

}
