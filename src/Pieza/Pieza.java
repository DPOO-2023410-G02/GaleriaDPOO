package Pieza;
import java.time.LocalDate;

import Usuario.Cliente;

public class Pieza {
	
	private String codigoPieza;
	
	private String anoCreacion;
	
	private String autor;
	
	private String lugarCreacion;
	
	private String titulo;
	
	private String fechaConsignacion;
	
	private int precioCompra;
	
	private boolean disponible;
	
	private String lugar;
	
	private Cliente propietario;
	
    public Pieza(String codigoPieza, String anoCreacion, String autor, String lugarCreacion,
            String titulo, String fechaConsignacion, int precioCompra, Cliente propietario, String lugar) {
    	
   this.codigoPieza = codigoPieza;
   this.anoCreacion = anoCreacion;
   this.autor = autor;
   this.lugarCreacion = lugarCreacion;
   this.titulo = titulo;
   this.fechaConsignacion = fechaConsignacion;
   this.precioCompra = precioCompra;
   this.disponible = true;
   this.propietario = propietario;
   this.lugar = lugar;
}
	
	public String getCodigoPieza() {
		return codigoPieza;
	}

	public String getAnoCreacion() {
		return anoCreacion;
	}

	public String getAutor() {
		return autor;
	}

	public String getLugarCreacion() {
		return lugarCreacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getFechaConsignacion() {
		return fechaConsignacion;
	}

	public int getPrecioCompra() {
		return precioCompra;
	}

	public boolean isDisponible() {
		return disponible;
	}
	
	public Cliente getPropietario() {
		return propietario;
	}
	public void hacerDisponible() {
		this.disponible = true;
	}
	public void hacerNoDisponible() {
		this.disponible = false;
	}
	public String getLugar() {
		return lugar;
	}
	public boolean verificarConsignacionPieza(LocalDate fecha) {
		
        LocalDate fechaConsignacionLocalDate = LocalDate.parse(fechaConsignacion); // Suponiendo que fechaConsignacion es una cadena en formato "yyyy-MM-dd"
        //Devuelve True si la fecha de parametro va andes que la fecha de consignacion
        //Si es True significa que la pieza no ah superado la fecha que entra como parametro
        return fechaConsignacionLocalDate.isAfter(fecha);
	}

	public void setPropietario(Cliente propietario) {
		this.propietario = propietario;
	}
	
}
