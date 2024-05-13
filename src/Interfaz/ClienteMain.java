package Interfaz;

import java.util.List;
import java.util.Scanner;
import Model.GaleriaDeArte;
import Model.PiezaSubastada;
import Model.Subasta;
import Pieza.Pieza;
import Usuario.Cliente;

public class ClienteMain {

    public static void main(GaleriaDeArte galeria) {
        Scanner scanner = new Scanner(System.in);
        boolean usuarioAutenticado = false;

        // Menú de inicio de sesión y registro
        while (!usuarioAutenticado) {
            System.out.println("Menú Principal:");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer después de nextInt()

            switch (opcion) {
                case 1:
                    // Lógica para iniciar sesión
                    System.out.println("Iniciar sesión...");
                    System.out.print("Nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();

                    System.out.print("Contraseña: ");
                    String password = scanner.nextLine();
                    if (galeria.iniciarSesionCliente(nombreUsuario, password)) {
                        System.out.println("Inicio de sesión exitoso como cliente.");
                        usuarioAutenticado = true;
                    } else {
                        System.out.println("Nombre de usuario o contraseña incorrectos.");
                    }
                    break;
                case 2:
                    System.out.println("Registrarse...");
                    System.out.print("Ingrese el nombre de Usuario: ");
                    String nombreUsuarioRegistro = scanner.nextLine();
                            
                    System.out.print("Ingrese la contraseña: ");
                    String passwordRegistro = scanner.nextLine();
                            
                    System.out.print("Ingrese su nombre: ");
                    String nombreRegistro = scanner.nextLine();
                            
                    Cliente cliente = new Cliente(passwordRegistro, nombreUsuarioRegistro, nombreRegistro);
                    galeria.AgregarUsuario(cliente);
                    usuarioAutenticado = true;
                    break;
                case 3:
                    return; 
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }


       
        // Menú de acciones para el usuario autenticado
        while (usuarioAutenticado) {
            System.out.println("\nMenú de Acciones:");
            System.out.println("1. Ver Catálogo de Piezas");
            System.out.println("2. Realizar Compra");
            System.out.println("3. Ver Piezas propias");
            System.out.println("4. Ver saldo actual");
            System.out.println("5. Agregar saldo");
            System.out.println("6. Registrar pieza nueva");
            System.out.println("7. Consignar pieza en la galeria.");
            System.out.println("8. Ingresar a la subasta actual");
            System.out.println("9. Realizar Oferta subasta.");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    
                	
                	
                	List<Pieza> piezasGaleria = galeria.getInventario().getPiezasTotales();
                	System.out.println("Piezas en la Galería:");

                	for (Pieza pieza : piezasGaleria) {
                	    System.out.println("Título: " + pieza.getTitulo());
                	    System.out.println("Autor: " + pieza.getAutor());
                	    System.out.println("Precio: " + pieza.getPrecioCompra());
                	    System.out.println("----------------------");
                	}
 
                	
                    break;
                case 2:
                    System.out.print("Ingrese su nombre se usuario: ");
                    String nombreUsuario1 = scanner.nextLine();
                    Cliente clienteCompra = (Cliente) galeria.getUsuario(nombreUsuario1);
                    
                    System.out.print("Ingrese el codigo de la pieza que desea comprar: ");
                    String codigoPiezaCompra = scanner.nextLine();
                    
                    Pieza piezaCompra = galeria.getInventario().getPiezaTotal(codigoPiezaCompra) ;
                    clienteCompra.realizarOfertaCompra(piezaCompra);
                                        
                    break;
                case 3:
                	System.out.print("Ingrese su nombre se usuario: ");
                    String nombreUsuario2 = scanner.nextLine();
                    
                    List<Pieza> piezasCliente = galeria.getAdministrador().getPiezasCliente(nombreUsuario2);
                	for (Pieza pieza : piezasCliente) {
                	    System.out.println("Título: " + pieza.getTitulo());
                	    System.out.println("Autor: " + pieza.getAutor());
                	    System.out.println("Precio: " + pieza.getPrecioCompra());
                	    System.out.println("----------------------");
                	}
                    
                    break;
                case 4:
                    System.out.print("Ingrese su nombre se usuario: ");
                    String nombreUsuario3 = scanner.nextLine();
                    Cliente clienteSaldo = (Cliente) galeria.getUsuario(nombreUsuario3);
                    System.out.println("Su saldo es: " + clienteSaldo.getSaldo());
                	
                    break;
                case 5:
                    System.out.print("Ingrese su nombre se usuario: ");
                    String nombreUsuario4 = scanner.nextLine();
                    Cliente clienteAgregarSaldo = (Cliente) galeria.getUsuario(nombreUsuario4);
                    
                    System.out.print("Ingrese la cantidad que desea agregar: ");
                    int valorAgregar = scanner.nextInt();
                    
                    clienteAgregarSaldo.agregarSaldo(valorAgregar);
                    
                    System.out.println("Su nuevo saldo es: " + clienteAgregarSaldo.getSaldo());
                    
                	
                    break;
                case 6:
                	System.out.println(galeria.getUsuarios().size());
                    System.out.print("Ingrese su nombre de usuario: ");
                    scanner.nextLine(); // Consumir el salto de línea pendiente
                    String nombreUsuario5 = scanner.nextLine();
                    Cliente clienteRegistrar = (Cliente) galeria.getUsuario(nombreUsuario5);

                    if (clienteRegistrar != null) {
                        System.out.print("Ingrese el año de creación de la pieza: ");
                        String anoCreacion = scanner.nextLine();

                        System.out.print("Ingrese el autor de la pieza: ");
                        String autor = scanner.nextLine();

                        System.out.print("Ingrese el lugar de creación de la pieza: ");
                        String lugarCreacion = scanner.nextLine();

                        System.out.print("Ingrese el título de la pieza: ");
                        String titulo = scanner.nextLine();

                        System.out.print("Ingrese el precio de la pieza: ");
                        int precio = scanner.nextInt();
                        scanner.nextLine(); // Limpiar el buffer después de nextInt()

                        clienteRegistrar.registrarPieza(anoCreacion, autor, lugarCreacion, titulo, precio);
                        System.out.println("La consignación se ha realizado correctamente.");
                    } else {
                        System.out.println("No se encontró el usuario. Verifique el nombre de usuario e intente nuevamente.");
                    }
                    break;

                	
                case 7:
                	System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario6 = scanner.nextLine();
                    Cliente clienteConsignar = (Cliente) galeria.getUsuario(nombreUsuario6);
                    
                	System.out.print("Ingrese el codigo de la pieza que desea consignar: ");
                    String codigoPiezaConsignacion = scanner.nextLine();
                    clienteConsignar.RealizarConsignacion(codigoPiezaConsignacion);
                    
                    break;
                case 8:
                   	System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario7 = scanner.nextLine();
                    Cliente clienteSubasta = (Cliente) galeria.getUsuario(nombreUsuario7);
                    clienteSubasta.ingresarASubasta();
                    
                    
                    
                    break;
                case 9:
                    
                   	System.out.print("Ingrese su nombre de usuario: ");
                    String nombreUsuario8 = scanner.nextLine();
                    Cliente clientePuja = (Cliente) galeria.getUsuario(nombreUsuario8);
                    Subasta subasta = galeria.getSubasta();
                    if(subasta != null) {
                    	List<PiezaSubastada> piezasSubasta = (List<PiezaSubastada>) subasta.getPiezasSubasta().values();
                    	
                    	System.out.println("Piezas disponibles para puja:");
                        for (int i = 0; i < piezasSubasta.size(); i++) {
                            PiezaSubastada pieza = piezasSubasta.get(i);
                            System.out.println((i + 1) + ". " + pieza.getPiezaAsociada().getTitulo() + " - Precio actual: " + pieza.getMayorPuja());
                        }
                        
                        System.out.print("Ingrese el titulo de la pieza: ");
                        String tituloPieza = scanner.nextLine();
                        
                        System.out.print("Ingrese el valor de su puja: ");
                        int valorPuja = scanner.nextInt();
                        
                        clientePuja.realizarOfertaSubasta(tituloPieza, valorPuja);
                    }
                    
                    break;
                case 10:
                    usuarioAutenticado = false; // Salir del bucle y volver al menú principal
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }
}
