package Usuario;
import java.util.List;

import Model.GaleriaDeArte;
import Model.Subasta;
import Pieza.Pieza;


public class Operador extends Usuario {
	
	public static final String OPERADOR = "Operador";
	
    public Operador(String password, String login, String nombre) {
        super(password, login, nombre);
            
    }
    @Override
    public String getTipoUsuario() {
        return OPERADOR;
    }
    
    public void CrearSubasta(List<Pieza> piezasSubasta) 
    {
    	Subasta subasta = new Subasta(piezasSubasta);
    	GaleriaDeArte.setSubasta(subasta);
    }
    
    public void iniciarSubasta() 
    {
    	GaleriaDeArte.getSubasta().inicializarSubasta();
    }
    
    public String registrarPujaCliente(String titulo, int puja, Cliente cliente) {
        String resultado = titulo + "_" + puja + "_" + cliente.getNombre();
        return resultado;
    }

    public void finalizarSubastaOperador() {
    	Subasta subasta = GaleriaDeArte.getSubasta();
    	subasta.finalizarSubasta();
    	
    }
    
}
