package com.company;
import java.util.Scanner;

public class Main {

    private static boolean end = false;

    public static void main(String[] args) {
        // write your code here
        Tablero tablero = new Tablero();
        Raton raton = new Raton();
        System.out.println("Desea iniciar el modo test?");
        System.out.println();
        tablero.getTableroDesarrollo(); //para saber las puntuaciones iniciales.
        System.out.println("------------------------");

        System.out.println("Bienvenido!\nPuedes desplazarte (tú eres MM) dentro del tablero, debes conseguir el queso" +
                "(CH) pero ten cuidado porque hay un gato (CC) que te puede comer");

        while (!end) {
            System.out.println("--------------");
            tablero.displayTablero();
            System.out.println("--------------");
            System.out.println("Tu puntuación: " + raton.getPuntuacion());
            moverRaton(tablero, raton);
        }

    }

    private static void moverRaton(Tablero tablero, Raton raton) {
        switch (raton.movimiento()) {
            case "u":
                comprobarCasilla(tablero, raton, -1, "u");
                break;
            case "d":
                comprobarCasilla(tablero, raton, +1, "d");
                break;
            case "r":
                comprobarCasilla(tablero, raton, +1, "r");
                break;
            case "l":
                comprobarCasilla(tablero, raton, -1, "l");
                break;
        }
    }

    private static void comprobarCasilla(Tablero tablero, Raton raton, int mv, String tipoM) {
        Scanner sc = new Scanner(System.in);
        String respuesta = "";

        int posY=0;
        int posX=0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tablero.getCelda(i, j) == 1) { // 1 indica que en la posción hay un ratón
                    posY=i;
                    posX=j;
                }
            }
        }

        int i=posY;
        int j=posX;

        if ((tipoM.equals("u") || tipoM.equals("d")) && ((i + mv) <= 3) && ((i + mv) >= 0)) {
            if (tablero.getCelda(i + mv, j) >= 10 && tablero.getCelda(i + mv, j) <= 30) {
                raton.setPuntuacion(tablero.getCelda(i + mv, j), true);
                tablero.setCasilla(i, j, 6);
                tablero.setCasilla(i + mv, j, 1);
            } else if (tablero.getCelda(i + mv, j) == 2) {
                System.out.println("Te ha cazado el gato, has PERDIDO");
                raton.setPuntuacion(raton.getPuntuacion(), false);
                end = true;
            } else if (tablero.getCelda(i + mv, j) == 3) {
                System.out.println("Has encontrado el quesito, has GANADO!");
                end = true;
            } else if (tablero.getCelda(i + mv, j) == 4) {
                System.out.println("has caído en la celda pregunta, si aciertas sumarás 50p, si fallas" +
                        "los restarás");
                System.out.println(tablero.getQuestion());
                respuesta = sc.nextLine();

                raton.setPuntuacion(50, tablero.submitAnswer(respuesta));
                tablero.setCasilla(i, j, 6);
                tablero.setCasilla(i + mv, j, 1);
            } else if (tablero.getCelda(i + mv, j) == 5) {
                System.out.println("Has caído en la celda adivina un número del 1-3, si aciertas sumarás 50p," +
                        "si fallas los restarás.");

                raton.setPuntuacion(50, tablero.adivinarNum());
                tablero.setCasilla(i, j, 6);
                tablero.setCasilla(i + mv, j, 1);
            } else if (tablero.getCelda(i + mv, j) == 6) {
                System.out.println("Esta casilla ya la habías pisado, has PERDIDO");
                raton.setPuntuacion(raton.getPuntuacion(), false);
                end = true;
            }
        } else if ((tipoM.equals("r") || tipoM.equals("l")) && ((j + mv) <= 3) && ((j + mv) >= 0)) {
            if (tablero.getCelda(i, j + mv) >= 10 && tablero.getCelda(i, j + mv) <= 30) {
                raton.setPuntuacion(tablero.getCelda(i, j + mv), true);
                tablero.setCasilla(i, j, 6);
                tablero.setCasilla(i, j + mv, 1);
            } else if (tablero.getCelda(i, j + mv) == 2) {
                System.out.println("Te ha cazado el gato, has PERDIDO");
                raton.setPuntuacion(raton.getPuntuacion(), false);
                end = true;
            } else if (tablero.getCelda(i, j + mv) == 3) {
                System.out.println("Has encontrado el quesito, has GANADO!");
                end = true;
            } else if (tablero.getCelda(i, j + mv) == 4) {
                System.out.println("has caído en la celda pregunta, si aciertas sumarás 50p, si fallas" +
                        "los restarás");
                System.out.println(tablero.getQuestion());
                respuesta = sc.nextLine();

                raton.setPuntuacion(50, tablero.submitAnswer(respuesta));
                tablero.setCasilla(i, j, 6);
                tablero.setCasilla(i, j + mv, 1);
            } else if (tablero.getCelda(i, j + mv) == 5) {
                System.out.println("Has caído en la celda adivina un número del 1-3, si aciertas sumarás 50p," +
                        "si fallas los restarás.");

                raton.setPuntuacion(50, tablero.adivinarNum());
                tablero.setCasilla(i, j, 6);
                tablero.setCasilla(i, j + mv, 1);
            } else if (tablero.getCelda(i, j + mv) == 6) {
                System.out.println("Esta casilla ya la habías pisado, has PERDIDO");
                raton.setPuntuacion(raton.getPuntuacion(), false);
                end = true;
            }
        } else {
            System.out.println("Te has salido del tablero, movimiento no válido");
        }
    }
}
