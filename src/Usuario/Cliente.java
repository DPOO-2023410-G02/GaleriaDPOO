package Usuario;

import java.util.ArrayList;
import java.util.List;

import Model.Compra;
import Model.GaleriaDeArte;
import Model.Subasta;
import Pieza.Pieza;

public class Cliente extends Usuario {
	
	public static final String CLIENTE = "Cliente";
	
	private List<Compra> compras;
	
	private List<Pieza> piezasPasadas;
	
	private List<Pieza> piezas;
	
	private int valorMaximo;
	
	private int saldo;
	
	public Cliente(String password, String login, String nombre) {
	    super(password, login, nombre);
	    compras = new ArrayList<Compra>();
	    piezasPasadas = new ArrayList<Pieza>();
	    piezas = new ArrayList<Pieza>();
	    valorMaximo = 0;
	    saldo = 0;
	}

    
    @Override
    public String getTipoUsuario() {
        return CLIENTE;
    }
    
    public void realizarOfertaCompra(Pieza piezaOfertada) {
    	
    	boolean disponible = piezaOfertada.isDisponible();
    	
    	if(disponible) {
	    	Compra compra = new Compra(piezaOfertada);
	    	compra.registrarCompra(piezaOfertada, this);
    	}
    }

	public int getValorMaximo() {
		return valorMaximo;
	}

	public int getSaldo() {
		return saldo;
	}


	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}


	public void setValorMaximo(int valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}
	
	public void añadirCompras(Compra compra) {
		compras.add(compra);
	}
	public List<Pieza> getPiezasPasadas() {
		return piezasPasadas;
	}
	
	public void añadirPiezasPasadas(Pieza pieza) {
		piezasPasadas.add(pieza);
	}
	
	public List<Pieza> getPasadas() {
		return piezas;
	}
	
	public void añadirPiezas(Pieza pieza) {
		pieza.setPropietario(this);
		piezas.add(pieza);
		
	}
	
	public void eliminarPieza(Pieza pieza) {
		piezas.remove(pieza);
		añadirPiezasPasadas(pieza);
	}
	
	
	
	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}


	public void setPiezasPasadas(List<Pieza> piezasPasadas) {
		this.piezasPasadas = piezasPasadas;
	}


	public void setPiezas(List<Pieza> piezas) {
		this.piezas = piezas;
	}

	//SUBASTA
	
	public void realizarOfertaSubasta( String titulo, int puja) {
		Subasta subasta = GaleriaDeArte.getSubasta();
		
		subasta.registrarOfertaSubasta(titulo, puja, this);
		
	}
	
	public void ingresarASubasta() {
		Subasta subasta = GaleriaDeArte.getSubasta();
		subasta.verificarClienteSubasta(this);
	}
	
	
	//
}


