package Test;

import Model.GaleriaDeArte;
import Usuario.Cliente;
import java.io.FileWriter;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;

import Model.GaleriaDeArte;
import Model.Inventario;
import Persistencia.PersistenciaUsuarios;
import Pieza.Pieza;
import Usuario.Cliente;
import Usuario.Operador;
import Usuario.Usuario;
import Model.Subasta;
public class TestSubasta {

	public static void main(String[] args) {
		
	   	//Creacion Galeria 
        GaleriaDeArte galeria = new GaleriaDeArte();
        
        //Creacion Empleados
        galeria.AgregarAdministrador("Qwer1234", "Camilo2816", "Camilo Sanchez");
        galeria.AgregarCajero("Qwer1234", "Camilo2816", "Camilo Sanchez");
        galeria.AgregarOperador("Qwer1234", "Camilo2816", "Camilo Sanchez");
        
        
        //Cliente
        Cliente usuarioTest = new Cliente("Zaq*Mko123" , "Andres21" , "Andres Chaparro" );
        Cliente usuarioTest2 = new Cliente("Zaq*Mko123" , "Juan12" , "Juan Rojas" );
        Cliente usuarioTest3 = new Cliente("Zaq*Mko123" , "Juan56" , "Juana Rojas" );
	
        galeria.AgregarUsuario(usuarioTest);
        usuarioTest.agregarSaldo(20000000);
        
        galeria.AgregarUsuario(usuarioTest2);
        usuarioTest2.agregarSaldo(20000000);
        
        galeria.AgregarUsuario(usuarioTest3);
        usuarioTest3.agregarSaldo(20000000);
        
        usuarioTest.registrarPieza("1924", "Pedro", "Roma", "TheBorn", 10000000);
        usuarioTest.RealizarConsignacion(usuarioTest.getPasadas().get(0).getCodigoPieza());
        usuarioTest.agregarSaldo(20000000);
        
        usuarioTest.registrarPieza("1999", "Pedro", "Roma", "TheBorn", 10000000);
        usuarioTest.RealizarConsignacion(usuarioTest.getPasadas().get(1).getCodigoPieza() );
        
        
        usuarioTest.registrarPieza("1998", "Pedro", "Roma", "TheBorn", 10000000);
        usuarioTest.RealizarConsignacion(usuarioTest.getPasadas().get(2).getCodigoPieza() );
        
        usuarioTest.registrarPieza("1959", "Pedro", "Roma", "TheBorn", 10000000);
        usuarioTest.RealizarConsignacion(usuarioTest.getPasadas().get(3).getCodigoPieza() );
        
        usuarioTest.registrarPieza("1129", "Pedro", "Roma", "TheBorn", 10000000);
        usuarioTest.RealizarConsignacion(usuarioTest.getPasadas().get(4).getCodigoPieza() );
        
        usuarioTest.registrarPieza("1569", "Pedro", "Roma", "TheBorn", 10000000);
        usuarioTest.RealizarConsignacion(usuarioTest.getPasadas().get(5).getCodigoPieza() );
        
        System.out.println(galeria.getInventario().getCapacidad());
        
        for(Pieza pieza: galeria.getInventario().getPiezasTotales()) {
        	System.out.println(pieza.getAnoCreacion());
        }
        
        Operador operador = galeria.getOperador();
        for(Pieza pieza: galeria.getInventario().getPiezasTotales()) 
        {
        	operador.agregarPiezaSubasta(pieza.getCodigoPieza());
        }
        System.out.println(operador.getPiezasSubasta());
        
        
        operador.CrearSubasta();
        Subasta subasta = galeria.getSubasta();
        System.out.println(galeria.getSubasta());
        
        usuarioTest.ingresarASubasta();
        usuarioTest2.ingresarASubasta();
        usuarioTest3.ingresarASubasta();
        
        System.out.println(subasta.getClientesSubasta());
	}
}
