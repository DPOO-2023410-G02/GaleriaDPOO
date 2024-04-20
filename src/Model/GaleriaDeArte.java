package Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Usuario.Usuario;


public class GaleriaDeArte {

	
	// Mapa de usuarios
	private Map<String, Usuario> usuarios;
	
	// inventario
	private Inventario inventario;
	
	//subasta actual en la galeria
	private Subasta subasta;
	
	// Se Inicializa una galeria de arte con los contenedores de información
	public GaleriaDeArte()
	
	{
		usuarios = new HashMap<String, Usuario>();
		inventario = new Inventario();
		subasta = new Subasta();
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
	
	public void agregarUsuario() 
	{
		return "hola"
	}

	
	
}
