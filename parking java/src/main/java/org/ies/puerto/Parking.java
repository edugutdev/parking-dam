package org.ies.puerto;

import java.util.Scanner;

/**
 * @author edugutdev
 * @version 1.0.0
 */
public class Parking {
    static void main() {
        Scanner teclado = new Scanner(System.in);
        int producto = 0;
        float suma = 0;
        System.out.println("=====================");
        System.out.println("    TIENDA DAM-PRO   ");
        System.out.println("=====================");
        System.out.println("Introduce tu nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("¿Cuántos productos va a comprar? ");
        producto = teclado.nextInt();

        for (int i = 1; i <=producto ; i++) {
            System.out.println("Producto "+i);
            teclado.nextLine();
            System.out.println("Nombre: ");
            String nombreProducto = teclado.nextLine();
            System.out.println("Precio: ");
            float precioProducto = teclado.nextFloat();
            suma += precioProducto;
            System.out.println(nombreProducto+" - "+precioProducto+" euros");

        }

        System.out.println("=====================");
        System.out.println("   TOTAL DEL TICKET  ");
        System.out.println("=====================");
        System.out.println("Cliente: "+nombre);
        System.out.println("Productos comprados: "+producto);
        System.out.println("TOTAL: "+suma+ " euros");
        System.out.println("Gracias por su compra, "+nombre);


        // 1. Pedir el nombre del cliente.
        // 2. Preguntar la cantidad de productos.
        // 3. Inicializar el acumulador total.
        // 4. Usar un bucle for para pedir los productos.
        // 5. Mostrar el número de productos y el total.

        teclado.close();
    }
}
