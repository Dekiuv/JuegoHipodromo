package com.mycompany.caballo;
/**
 *
 * @author Dekiuv
 */
import java.util.Scanner;

public class Caballo {
    // Scanner
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] hipodromo;
        // Eliminado el parámetro inicial
        int participantes = cantidadParticipantes();
        int apuesta = elegirCaballo(participantes);
        hipodromo = new char[participantes][50];
        iniciarHipodromo(hipodromo);
        mostrarHipodromo(hipodromo);

        int ganador = -1;
        do {
            for (int turno = 0; turno < participantes && ganador == -1; turno++) {
                int posicion = avanzarCaballo(hipodromo, turno);
                mostrarHipodromo(hipodromo);
                System.out.println("--------------------------------------------------------------------------------------");
                if (posicion >= 50 - 1) {
                    ganador = turno;
                }
            }
        } while (ganador == -1);

        System.out.println("Ha ganado el caballo numero: " + ganador);
        if (apuesta == ganador) {
            System.out.println("Has ganado la apuesta");
        } else {
            System.out.println("Has perdido la apuesta");
        }
        // Cierre del Scanner
        sc.close();
    }

    public static void iniciarHipodromo(char[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                // Inicializa todo con puntos
                m[i][j] = '.';
            }
            // Marca la posición inicial del caballo
            m[i][0] = '#';
        }
    }

    public static void mostrarHipodromo(char[][] hipodromo) {
        for (int i = 0; i < hipodromo.length; i++) {
            System.out.print("[" + i + "] ");
            // Mostrar desde la columna 0
            for (int j = 0; j < hipodromo[i].length; j++) {
                System.out.print(hipodromo[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    // Eliminado el parámetro innecesario
    public static int cantidadParticipantes() {
        int participantes;
        do {
            System.out.println("Cuantos caballos quieres que haya? (MIN = 2, MAX = 8)");
            participantes = sc.nextInt();
        } while (participantes < 2 || participantes > 8);
        System.out.println("Correran " + participantes + " personas");
        return participantes;
    }

    public static int elegirCaballo(int participantes) {
        int apuesta;
        do {
            System.out.println("Quien ganara la carrera? Del 0 al " + (participantes - 1));
            apuesta = sc.nextInt();
        } while (apuesta < 0 || apuesta >= participantes);
        return apuesta;
    }

    public static int avanzarCaballo(char[][] hipodromo, int turno) {
        // Avance aleatorio entre 1 y 6
        int avance = (int) (Math.random() * 6 + 1);
        int posicionActual = 0;

        // Encuentra la posición actual del caballo
        for (int i = 0; i < 50; i++) {
            if (hipodromo[turno][i] == '#') {
                // Elimina la posición actual
                hipodromo[turno][i] = '.';
                posicionActual = i;
                break;
            }
        }

        // Calcula la nueva posición del caballo
        int nuevaPosicion = Math.min(posicionActual + avance, 50 - 1);
        // Coloca el caballo en la nueva posición
        hipodromo[turno][nuevaPosicion] = '#';

        return nuevaPosicion;
    }
}
