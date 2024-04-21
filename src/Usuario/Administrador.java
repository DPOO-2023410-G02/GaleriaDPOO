package Usuario;

public class Administrador extends Usuario {
	
	public static final String ADMINISTRADOR = "Administrador";
	
    public Administrador(String password, String login, String nombre) {
        super(password, login, nombre);
    }
    
    @Override
    public String getTipoUsuario() {
        return ADMINISTRADOR;
    }
    
    public boolean verificarUsuario(Cliente cliente) {
    	return true;
    }
}
