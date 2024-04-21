package Model;

import Pieza.Pieza;
import Usuario.Administrador;
import Usuario.Cliente;
import Model.GaleriaDeArte;

public class Compra {
		
	
	public void registrarCompra(int Oferta, Pieza piezaOfertada, Cliente cliente) {
	
		piezaOfertada.hacerNoDisponible();
		
        Administrador administrador = GaleriaDeArte.getAdministrador();
        
        boolean llave = administrador.verificarUsuario(cliente); 
		
        if(llave) {
        	
        	Inventario inventario = GaleriaDeArte.getInventario();
        	inventario.eliminarPieza(piezaOfertada.getLugar(), piezaOfertada);
        	cliente.añadirPiezasPasadas(this);
        	
        }
	}
}
