package Usuario;

public class Propietario extends Usuario {

	public static final String OPERADOR = "Operador";
	
    public Propietario(String password, String login, String nombre) {
        super(password, login, nombre);
    }
    @Override
    public String getTipoUsuario() {
        return OPERADOR;
    }
}
