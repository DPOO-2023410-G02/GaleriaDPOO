package Model;

import Pieza.Pieza;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Cliente;
import Model.GaleriaDeArte;

public class Compra {
	
	private Administrador administrador;
	
	private Cajero cajero;
	
	private int precio;
	
	private Pieza pieza;
	
	
	public Compra(Pieza piezaOfertada) {
		
		administrador = GaleriaDeArte.getAdministrador();
		cajero = GaleriaDeArte.getCajero();
		precio = piezaOfertada.getPrecioCompra();
		pieza = piezaOfertada;
	}
	
	
	public void registrarCompra(Pieza piezaOfertada, Cliente comprador) {		
		
		administrador.hacerNoDisponible(piezaOfertada);
        
        boolean llave1 = administrador.verificarUsuario(comprador, piezaOfertada); 

        if(llave1) {
        	
        	administrador.eliminarPiezaInventario(piezaOfertada.getLugar(), piezaOfertada);
        	
        	cajero.registarPago(comprador, piezaOfertada); 
        	
        	comprador.añadirCompras(this);
         	
        	administrador.eliminarPiezaPropietario(piezaOfertada);
        	
        	comprador.añadirPiezas(piezaOfertada);
        	
        }
        else {
        	piezaOfertada.hacerDisponible();
        }
	}


	public int getPrecio() {
		return precio;
	}


	public Pieza getPieza() {
		return pieza;
	}
	
}
