package Usuario;

public class Operador extends Usuario {
	public static final String OPERADOR = "Operador";
	
    public Operador(String password, String login, String nombre) {
        super(password, login, nombre);
    }
    @Override
    public String getTipoUsuario() {
        return OPERADOR;
    }
}
