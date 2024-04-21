package Usuario;

import java.util.List;

import Model.Compra;
import Pieza.Pieza;

public class Cliente extends Usuario {
	
	public static final String CLIENTE = "Cliente";
	
	private List<Compra> comprasPasadas;
	
	private int valorMaximo;
	
    public Cliente(String password, String login, String nombre) {
        super(password, login, nombre);
    }
    
    @Override
    public String getTipoUsuario() {
        return Cliente;
    }
    
    public void realizarOfertaCompra(int oferta, Pieza piezaOfertada) {
    	
    	Compra compra = new Compra();
    	compra.registrarCompra(oferta, piezaOfertada, this);
    	
    }

	public int getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(int valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	
	public List<Compra> getComprasPasadas() {
		return comprasPasadas;
	}
	
	public void añadirPiezasPasadas(Compra compra) {
		comprasPasadas.add(compra);
	}
}
