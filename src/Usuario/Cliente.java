package Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import Model.Artista;
import Model.Compra;
import Model.GaleriaDeArte;
import Model.Inventario;
import Model.Subasta;
import Pieza.Pieza;
import Pieza.GeneradorCodigosPieza;

public class Cliente extends Usuario {
	
	private GeneradorCodigosPieza generadorCodigos;
	
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
	    generadorCodigos = GeneradorCodigosPieza.getInstance();
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
	
	public void anadirCompras(Compra compra) {
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
	
	public void realizarOfertaSubasta ( String titulo, int puja)throws Exception{
		Subasta subasta = GaleriaDeArte.getSubasta();
		 if (subasta == null) {
	         throw new Exception("No se puede acceder a la subasta dado que la subasta no se ah creado.");
		 }
		 else if (Boolean.TRUE.equals(subasta.isInicializacion())) {
	         throw new Exception("No se puede acceder a la subasta dado que ya hay una transcurriendo.");
		 }
		 else if (!subasta.getPiezasSubasta().containsKey(titulo)) {
	         throw new Exception("No existe la pieza a la que se quiere subastar");
		 }
		 else {
			 subasta.registrarOfertaSubasta(titulo, puja, this);
		 }
	}
	
	public void ingresarASubasta() throws Exception{
		Subasta subasta = GaleriaDeArte.getSubasta();
		 if (subasta == null) {
	         throw new Exception("No se puede acceder a la subasta dado que la subasta no se ah creado.");
		 }
		 else if (Boolean.TRUE.equals(subasta.isInicializacion())) {
	         throw new Exception("No se puede acceder a la subasta dado que ya hay una transcurriendo.");
		 }
		 else {
			 subasta.verificarClienteSubasta(this);
		 }
	}
	
	public void registrarPieza(String anoCreacion, String autor, String lugarCraecion, String titulo, int precioCompra) {
		//CODIGO PIEZA
		String codigoPieza = generadorCodigos.generarCodigo();
		
		
		//FECHA CONSIGNACIÓN
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Añadir 2 meses a la fecha actual
        LocalDate fechaDosMesesDespues = fechaActual.plusMonths(2);

        // Formatear la fecha en el formato "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaDosMesesDespues.format(formatter);
		
        // LUGAR GALERIA
        String lugarGaleria; 
        if(precioCompra > 10000000) {
        	lugarGaleria = "Coleccion";
        	
        }else {
        	lugarGaleria = "Bodega";
        }
        
  
		Pieza pieza = new Pieza(codigoPieza, anoCreacion, autor, lugarCraecion, titulo, fechaFormateada, precioCompra, this, null);
		piezas.add(pieza);
		
		
		List<Artista> artistas = GaleriaDeArte.getInventario().getArtistas();
		Artista artistaDueno = null;		
		for ( Artista artista: artistas) {
			if (artista.getNombre().equals(autor)) {
				 artistaDueno = artista;
				 
			}				
		}
		
		if(artistaDueno == null) {	
			Artista nuevoArtista = new Artista(autor);
			nuevoArtista.agregarPieza(pieza);
			artistas.add(nuevoArtista);
		}else {			
			artistaDueno.agregarPieza(pieza);
		}
	
		
		
	}
	
	public void RealizarConsignacion(String codigoPieza) {
		Pieza pieza = buscarPieza(codigoPieza);
 
		Inventario inventario = GaleriaDeArte.getInventario();
        if(pieza.getPrecioCompra() > 10000000) {  
        	pieza.setLugar("Coleccion");
        	inventario.agregarPieza("Coleccion", pieza);
        }else {
        	pieza.setLugar("Bodega");
        	inventario.agregarPieza("Bodega", pieza);
        }
        
        
        
		
	}
	
	public Pieza buscarPieza(String codigoPieza) {
		Pieza piezaBuscada = null;
		for (Pieza pieza: piezas) {
			if(codigoPieza.equals(pieza.getCodigoPieza())) {
				piezaBuscada = pieza;
			}
		}
		return piezaBuscada;
	}
	
	
	
	public void agregarSaldo(int valor){
		saldo += valor;
	}
	
}


