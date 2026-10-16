package org.ies.puerto;

import java.util.Scanner;

/**
 * @author edugutdev
 * @version 1.0.0
 */
public class Parking {
    static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        float tarifaHora = 1.50f;
        int totalVehiculos = 0;
        int totalHorasRegistradas = 0;
        int totalCalculosTarifa = 0;
        float importeTotalCalculado = 0f;


        String nombreUsuario;
        System.out.println("Introduzca tu nombre de usuario: ");
        nombreUsuario = teclado.nextLine();

        int opcion;

        do {
            mostrarMenu();
            System.out.println("Opcion: ");
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {


            }

        }



        // 1. Leer el nombre del usuario.
        // 2. Repetir el menú con do-while.
        // 3. Resolver cada opción mediante switch.
        // 4. Llamar a tus métodos y actualizar acumuladores.
        // 5. Cerrar Scanner al salir.

        teclado.close();
    }


}

