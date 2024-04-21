package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Pieza.Pieza;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Cliente;
import Usuario.Operador;

public class Subasta {
	
	private List<Pieza> piezasParaSubastar;
	private boolean inicializacion;
	private Operador operador;
	private HashMap<String, PiezaSubastada> piezasSubasta;
	private List<Cliente> clientesSubasta;
	private Administrador administrador;
	private List<String> registrosPujas;
	private Cajero cajero;
	
	public Subasta(List<Pieza> piezas) 
	{
		piezasParaSubastar = piezas;
		inicializacion = false;
		operador = GaleriaDeArte.getOperador();
		piezasSubasta = crearPiezasSubastadas();
		clientesSubasta = new ArrayList<Cliente>();
		administrador = GaleriaDeArte.getAdministrador();
		cajero = GaleriaDeArte.getCajero();
	}
	
	public void inicializarSubasta()
	{
		inicializacion = true;
	}
	
	
	public HashMap<String, PiezaSubastada> crearPiezasSubastadas()
	{
		HashMap<String, PiezaSubastada> piezasSubasta = new HashMap<String , PiezaSubastada>();
		for(Pieza pieza:  piezasParaSubastar) {
			piezasSubasta.put(pieza.getTitulo(), new PiezaSubastada(pieza.getPrecioCompra(), (int)(pieza.getPrecioCompra()* 1.1), pieza ) );			
			
		}
		return piezasSubasta;
		
	}
	
	public void verificarClienteSubasta(Cliente cliente) {
		 boolean llave1 = administrador.verificarUsuarioSubasta(cliente, obtenerValorMinimoPrecioActual());
		 if(llave1 && inicializacion) {
			 clientesSubasta.add(cliente);
		 }
	}
	
	
	public void registrarOfertaSubasta(String titulo, int puja, Cliente cliente) {
		
		PiezaSubastada pieza = piezasSubasta.get(titulo);
		int pujaMayor = pieza.getMayorPuja();
		int valorInicial = pieza.getValorInicialSubasta();
		
		if(puja > pujaMayor && puja > valorInicial && clientesSubasta.contains(cliente)) 
		{
		pieza.setMayorPuja(puja);
		pieza.setGanador(cliente);
		String registro = operador.registrarPujaCliente(titulo, pujaMayor, cliente);
		registrosPujas.add(registro);
		
		}
		
		
	}
	
	public void finalizarSubasta() {
		List<PiezaSubastada> listaPiezasEntrega = (List<PiezaSubastada>) piezasSubasta.values();
		
		for(PiezaSubastada pieza: listaPiezasEntrega ) {
			if (pieza.getMayorPuja() > pieza.getValorMinimoVenta() ) {
				Cliente ganador = pieza.getGanador();
				Pieza piezaEntrega  = pieza.getPiezaAsociada();
				int valorPagado = pieza.getMayorPuja();
				
				administrador.eliminarPiezaInventario(piezaEntrega.getLugar(), piezaEntrega);
				
				cajero.registarPagoSubasta(ganador, valorPagado);
				
				administrador.eliminarPiezaPropietario(piezaEntrega);
				
				ganador.añadirPiezas(piezaEntrega);
			}
		}
	}
	
	
	public int obtenerValorMinimoPrecioActual() {
        int valorMinimo = Integer.MAX_VALUE; 

        for (PiezaSubastada pieza : piezasSubasta.values()) {
            int precioActual = pieza.getValorMinimoVenta();
            if (precioActual < valorMinimo) {
                valorMinimo = precioActual;
            }
        }

        return valorMinimo;
    }
	
}
