package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Pieza.Pieza;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Cliente;
import Usuario.Operador;
import Usuario.Usuario;


public class GaleriaDeArte {

	
	// Mapa de usuarios
	private Map<String, Usuario> usuarios;
	// inventario
	private static Inventario inventario;
	
	//subasta actual en la galeria
	private static Subasta subasta;
	
	private static Administrador administrador;

	private static Operador operador;
	
	private static Cajero cajero;
	
	private List<List<String>> registrosPorSubasta;
	
	// Se Inicializa una galeria de arte con los contenedores de información
	public GaleriaDeArte()
	
	{
		usuarios = new HashMap<String, Usuario>();
		inventario = new Inventario();
		subasta = null;
		administrador = new Administrador("202111146", "a.chaparrod", "Andres");
		operador = new Operador("" , "" , "");
		cajero = new Cajero("" , "" , "");
		registrosPorSubasta = new ArrayList<>();
	}
	
	
	//Se obtiene una lista con todos los usuarios
	public Collection<Usuario> getUsuarios()
	{
		return usuarios.values();	
	}
	
	//Se obtiene un usuario en especifico.
	public Usuario getUsuario(String login) 
	{
		return usuarios.get(login);
	}
		
	//existe un usuario
	public Boolean existeUsuario(String login) {
	    
	    return usuarios.containsKey(login);
	}
	
	public void agregarUsuarioTipo(String password, String login, String nombre, String tipo) 
	{
		if (tipo.equals("Administrador"))
		usuarios.put(login, new Administrador (password, login, nombre));
		else if (tipo.equals("Cliente"))
		usuarios.put(login, new Cliente (password, login, nombre));
		else if (tipo.equals("Operador"))
		usuarios.put(login, new Operador (password, login, nombre));
		else if (tipo.equals("Cajero"))
		usuarios.put(login, new Cajero (password, login, nombre));
	}


	public static Administrador getAdministrador() {
		return administrador;
	}
	public static Cajero getCajero() {
		return cajero;
	}
	public static Operador getOperador() {
		return operador;
	}
	public static Inventario getInventario() {
		return inventario;
	}

	public static Subasta getSubasta() {
		return subasta;
	}


    public static void setSubasta(Subasta subasta) {
        GaleriaDeArte.subasta = subasta;
    }
	
}
