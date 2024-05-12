package Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.GaleriaDeArte;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Cliente;
import Usuario.Operador;

public class JUnitSubasta {

    private static final Cliente CLIENTE_DUENO = new Cliente("12345678", "Juan2312", "Juan Perez");
    private static final Cliente CLIENTE1 = new Cliente("12345678", "Julio4354", "Julio Ojeda");
    private static final Cliente CLIENTE2 = new Cliente("12345678", "Pedro4354", "Pedro Ojeda");
    private static final Cliente CLIENTE3 = new Cliente("12345678", "camilo4354", "Camilo Ojeda");
    private Administrador admin;
    private GaleriaDeArte galeria;
    private Cajero cajero;
    private Operador operador;

    @BeforeEach
    void setUp() {
        crearGaleria();
    }

    private void crearGaleria() {
        galeria = new GaleriaDeArte();
        galeria.AgregarAdministrador("12345678", "Fernando23", "Fernando Perez");
        galeria.AgregarCajero("12345678", "Fernando23", "Fernando Perez");
        galeria.AgregarOperador("12345678", "Fernando23", "Fernando Perez");
        galeria.AgregarUsuario(CLIENTE1);
        galeria.AgregarUsuario(CLIENTE2);
        galeria.AgregarUsuario(CLIENTE3);
        galeria.AgregarUsuario(CLIENTE_DUENO);
        admin = galeria.getAdministrador();
        cajero = galeria.getCajero();
        operador = galeria.getOperador();
    }
    

    @Test
    public void testCreaSubasta() {
        CLIENTE_DUENO.registrarPieza("1999", "Pedro", "Roma", "TheBorn", 10000000);
        CLIENTE_DUENO.registrarPieza("2000", "Pedro", "Roma", "TheBorn2", 10000000);
        CLIENTE_DUENO.RealizarConsignacion(CLIENTE_DUENO.getPasadas().get(0).getCodigoPieza());
        CLIENTE_DUENO.RealizarConsignacion(CLIENTE_DUENO.getPasadas().get(1).getCodigoPieza());
        operador.agregarPiezaSubasta(CLIENTE_DUENO.getPasadas().get(0).getCodigoPieza());
        operador.agregarPiezaSubasta(CLIENTE_DUENO.getPasadas().get(1).getCodigoPieza());

        assertEquals(2, operador.getPiezasSubasta().size(), "No se agregaron correctamente todas las piezas");
        operador.CrearSubasta();
        assertEquals(2, galeria.getSubasta().getPiezasSubasta().values().size(), "Las piezas para la subasta no se crearon correctamente");
    }

    @Test
    public void testVerificarSubasta() {
    	CLIENTE1.agregarSaldo(50000000);
        CLIENTE2.agregarSaldo(50000000);
        CLIENTE3.agregarSaldo(50000000);
    	assertEquals(true, admin.verificarUsuarioSubasta(CLIENTE1, 5000) ,"No se verifico correctamente cuando el saldo es mayor al precio minimo de entrada.");
    	assertEquals(false, admin.verificarUsuarioSubasta(CLIENTE1, 500000000) ,"No se verifico correctamente cuando el saldo es menor al precio minimo de entrada.");
    	assertEquals(true, admin.verificarUsuarioSubasta(CLIENTE1, 50000000) ,"No se verifico correctamente cuando el saldo es igual al precio minimo de entrada.");

    }

    @Test
    public void testIngresarSubasta() {
    	CLIENTE_DUENO.registrarPieza("1999", "Pedro", "Roma", "TheBorn", 10000000);
        CLIENTE_DUENO.registrarPieza("2000", "Pedro", "Roma", "TheBorn2", 10000000);
        CLIENTE_DUENO.RealizarConsignacion(CLIENTE_DUENO.getPasadas().get(0).getCodigoPieza());
        CLIENTE_DUENO.RealizarConsignacion(CLIENTE_DUENO.getPasadas().get(1).getCodigoPieza());
        operador.agregarPiezaSubasta(CLIENTE_DUENO.getPasadas().get(0).getCodigoPieza());
        operador.agregarPiezaSubasta(CLIENTE_DUENO.getPasadas().get(1).getCodigoPieza());
       
        assertEquals(2, operador.getPiezasSubasta().size(), "No se agregaron correctamente todas las piezas");
        operador.CrearSubasta();
        
        CLIENTE1.agregarSaldo(50000000);
        CLIENTE2.agregarSaldo(50000000);
        CLIENTE3.agregarSaldo(50000000);
        CLIENTE1.ingresarASubasta();
        CLIENTE2.ingresarASubasta();
        CLIENTE3.ingresarASubasta();

        assertEquals(3, galeria.getSubasta().getClientesSubasta().size(), "No se agregaron correctamente todos los clientes a la subasta");
    }
}

