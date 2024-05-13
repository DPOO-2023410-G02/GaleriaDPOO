package Interfaz;

import java.util.Scanner;

import Model.GaleriaDeArte;

public class Main {
    public static void main(String[] args) {
        GaleriaDeArte galeria = new GaleriaDeArte();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la galería de arte.");
        System.out.println("Por favor, seleccione su tipo de usuario:");
        System.out.println("1. Administrador");
        System.out.println("2. Operador");
        System.out.println("3. Cliente");
        System.out.println("4. Cerrar Aplicacion");

        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                AdminMain.main(galeria);
                
            case 2:
                OperadorMain.main(galeria);
                
            case 3:
                ClienteMain.main(galeria);
                
            case 4:
            	break;
            default:
                System.out.println("Opción no válida. Saliendo del programa.");
                break;
        }
    }
}
