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
                case 1:
                    int cantidad = leerEnteroPositivo(teclado, "¿Cuantos vehículos quieres registrar?", "Debe ser un entero positivo");
                    for (int i = 1; i <= cantidad; i++) {
                        System.out.println("Vehículo "+i);
                        System.out.println("Matrícula: ");
                        String matricula = teclado.nextLine();
                        int horas = leerEnteroPositivo(teclado, "Horas estacionadas", "Las horas deben ser mayores que 0");
                        totalVehiculos++;
                        totalHorasRegistradas += horas;
                    }
                    System.out.println("Vehiculos registrados en esta operacion: "+cantidad);
                    break;

                case 2:
                    int horaTarifa = leerEnteroPositivo(teclado, "Horas para calcular la tarifa", "Las horas deben ser mayores que 0");
                    float importe = calcularTarifa(horaTarifa, tarifaHora);
                    totalCalculosTarifa++;
                    importeTotalCalculado += importe;
                    System.out.println("Importe: "+importe+" euros");
                    break;

                case 3:
                    mostrarResumen(nombreUsuario, totalVehiculos, totalHorasRegistradas, totalCalculosTarifa, importeTotalCalculado);
                    break;

                case 4:
                    System.out.println("Gracias por utilizar PARKING DAM-PRO");
                    teclado.close();
                    break;
            }


        } while (opcion != 4);

        teclado.close();
    }
    public static void mostrarMenu() {
        System.out.println("\nMostrar menu:");
        System.out.println("1. Registrar vehiculos");
        System.out.println("2. Calcular tarifa de estacionamiento");
        System.out.println("3. Mostrar resumen del dia");
        System.out.println("4. Salir");
    }

    public static int leerEnteroPositivo(Scanner teclado, String mensaje, String mensajeError) {
        int valor;
        do {
            System.out.println(mensaje+": ");
            valor = teclado.nextInt();
            teclado.nextLine();
            if (valor <= 0) {
                System.out.println(mensajeError);
            }
        } while (valor <= 0);
        return valor;
    }
    public static float calcularTarifa(int horas, float tarifaHora) {
        return horas * tarifaHora;
    }
    public static void mostrarResumen(String nombreUsuario, int totalVehiculos, int totalHorasRegistradas, int totalCalculosTarifa, float importeTotalCalculado) {
        System.out.println("Usuario: "+nombreUsuario);
        System.out.println("Vehiculos registrados: "+totalVehiculos);
        System.out.println("Horas registradas: "+totalHorasRegistradas);
        System.out.println("Calculos de tarifas: "+totalCalculosTarifa);
        System.out.println("Importe total calculado: "+importeTotalCalculado);
    }

}

