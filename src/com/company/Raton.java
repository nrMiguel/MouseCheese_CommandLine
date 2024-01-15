package com.company;

import java.util.Scanner;

public class Raton {
    private int puntuacion;

    public String movimiento() {
        String respuesta = "";
        Scanner sc = new Scanner(System.in);
        boolean correcto = false;

        while (!correcto){
            System.out.println("El movimiento puede ser:\nArriba -> u\nAbajo -> d\nDerecha -> r\nIzquierda -> l");
            respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("u") || respuesta.equalsIgnoreCase("d") ||
                    respuesta.equalsIgnoreCase("r") || respuesta.equalsIgnoreCase("l")){
                correcto=true;
            } else {
                System.out.println("La opción debe ser u, d, r o l, vuelva a introducirla: ");
            }
        }

        return respuesta;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion, boolean tipo) {
        if (tipo) {
            this.puntuacion += puntuacion;
        } else {
            this.puntuacion -= puntuacion;
        }
    }
}
