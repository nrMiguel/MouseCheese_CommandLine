package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Tablero implements Questionable {
    private int tablero[][] = new int[4][4];

    public Tablero() {
        establecerPuntuaciones();
    }

    /**
     * Ni 1, ni 2, ni 3 sumarán puntos, indica ser ratón, quesito y gatito respectivamente.
     * El número 4 indica que es una casilla de pregunta (++).
     * el número 5 indica que es una casilla de adivinar número aleatorio (--).
     * Este método establece las puntuaciones del tablero, asigna puntuaciones aleatoriamente (10, 20, 30 o 50) siempre que
     * no sea la posición inicial del ratón (0, 0), del gato (aleatorio o 3, 2) o el quesito (3, 3).
     */
    private void establecerPuntuaciones() {
        Random rand = new Random();
        int puntuaciones[] = {10, 20, 30};
        boolean gato = false; //sirve para escoger la posición aleatoria del ratón.
        boolean positivo=false; //sirve para escoger la posición de la pregunta (++).
        boolean negativo=false; //sirve para escoger la posición de adivinar número (--).

        tablero[0][0] = 1; //1 indica ratón.
        tablero[3][3] = 3; //3 indica quesito.

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ((i == 0 && j == 0) || (i == 3 && j == 3)) { //para que no ponga puntuación en casilla de ratón o quesito.

                } else {
                    int aux = rand.nextInt(10) + 1;
                    if (((!gato && aux == 3) || (i == 3 && j == 2 && !gato)) /*&& i>1 && j>0*/) { //busca posición del gatito.
                        tablero[i][j] = 2; // 2 indica gatito.
                        gato = true; //indica que se ha encontrado la posición del gatito.
                    } else if ((!positivo && aux == 4) || (!positivo && i==3 && j==1)) {
                        positivo=true; //indica que la casilla es una pregunta.
                        tablero[i][j] = 4;
                    } else if ((!negativo && aux == 5) || (!negativo && i==3 && j==0)) {
                        negativo=true; //indica que la casilla es una adivinar número.
                        tablero[i][j] = 5;
                    }  else {
                        tablero[i][j] = (rand.nextInt(3)+1)*10; //Generará una puntuación de 10, 20 o 30.
                    }
                }
            }
        }
    }

    public int getCelda(int posY, int posX){
        return tablero[posY][posX];
    }

    public void setCasilla(int posY, int posX, int val) {
        tablero[posY][posX]=val;
    }

    /**
     * Este imprime el tablero completo para el usuario
     */
    public void displayTablero(){
        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++){
                if (tablero[i][j]==1){
                    System.out.print("MM" + "\t");
                } else if(tablero[i][j]==3){
                    System.out.print("CH" + "\t");
                } else if(tablero[i][j]==6) {
                    System.out.print("·." + "\t");
                } else {
                    System.out.print("00" + "\t");
                }
            }
            System.out.println();
        }
    }

    @Override
    public String getQuestion() {

        return "Cual es el valor por defecto de una variable int?";
    }

    @Override
    public boolean submitAnswer(String answer) {
        if (answer.equals("0")) {
            System.out.println("Has acertado +50p");
            return true;
        } else{
            System.out.println("Te has equivocado -50p");
            return false;
        }
    }

    public boolean adivinarNum(){
        Random rand = new Random();
        int num=rand.nextInt(3)+1;

        Scanner sc = new Scanner(System.in);

        System.out.println("Adivina un número del 1-3");
        System.out.println(num);
        int respuesta=sc.nextInt();

        if (num==respuesta){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Este imprime el tablero completo al descubierto.
     */
    public void getTableroDesarrollo(){
        System.out.println("Este es el tablero con las puntuaciones y casillas al descubierto: ");

        for (int i=0; i<4; i++){
            for (int j=0; j<4; j++) {
                if (tablero[i][j] == 1) {
                    System.out.print("MM" + "\t");
                } else if(tablero[i][j]==2){
                    System.out.print("CC" + "\t");
                } else if (tablero[i][j] == 3) {
                    System.out.print("CH" + "\t");
                } else if (tablero[i][j] == 4) {
                    System.out.print("++" + "\t");
                } else if (tablero[i][j] == 5){
                    System.out.print("--" + "\t");
                }else {
                    System.out.print(tablero[i][j] + "\t");
                }
                            }
            System.out.println();
        }
    }
}
