package Usuario;

public class Cajero extends Usuario {
	
	public static final String CAJERO = "Cajero";
	
    public Cajero(String password, String login, String nombre) {
        super(password, login, nombre);
    }
    @Override
    public String getTipoUsuario() {
        return CAJERO;
    }
    
    public boolean verificarPago(Cliente cliente) {
    	
    	return true;
    }
}
