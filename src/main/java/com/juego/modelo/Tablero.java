package com.juego.modelo;

public class Tablero {
    private final int FILAS = 6;
    private final int COLUMNAS = 7;

    private Ficha[][] celdas;

    public Tablero(){
        this.celdas = new Ficha[FILAS][COLUMNAS];

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

    public int colocarFicha(Ficha colorFicha, int columna){

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

    public boolean hayGanador(){
        if (hayGanadorEnHorizontal() || hayGanadorEnVertical() || hayGanadorEnDiagonal()){
            return true;
        }
        return false;
    }

    private boolean hayGanadorEnHorizontal() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna <= COLUMNAS - 4; columna++) {
                Ficha ficha = celdas[fila][columna];
                if (ficha != null && ficha.equals(celdas[fila][columna + 1]) &&
                        ficha.equals(celdas[fila][columna + 2]) && ficha.equals(celdas[fila][columna + 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hayGanadorEnVertical() {
        for (int fila = 0; fila <= FILAS - 4; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                Ficha ficha = celdas[fila][columna];
                if (ficha != null && ficha.equals(celdas[fila + 1][columna]) &&
                        ficha.equals(celdas[fila + 2][columna]) && ficha.equals(celdas[fila + 3][columna])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hayGanadorEnDiagonal() {
        // Verificar diagonales hacia la derecha y hacia la izquierda
        for (int fila = 0; fila <= FILAS - 4; fila++) {
            for (int columna = 0; columna <= COLUMNAS - 4; columna++) {
                // Diagonal hacia la derecha
                if (celdas[fila][columna] != null && celdas[fila][columna].equals(celdas[fila + 1][columna + 1]) &&
                        celdas[fila][columna].equals(celdas[fila + 2][columna + 2]) && celdas[fila][columna].equals(celdas[fila + 3][columna + 3])) {
                    return true;
                }

                // Diagonal hacia la izquierda
                if (celdas[fila][columna + 3] != null && celdas[fila][columna + 3].equals(celdas[fila + 1][columna + 2]) &&
                        celdas[fila][columna + 3].equals(celdas[fila + 2][columna + 1]) && celdas[fila][columna + 3].equals(celdas[fila + 3][columna])) {
                    return true;
                }
            }
        }
        return false;
    }

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
