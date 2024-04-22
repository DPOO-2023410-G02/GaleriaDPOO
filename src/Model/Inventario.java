package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import Pieza.Pieza;



public class Inventario {

	private List<Pieza> piezasColeccioon;
	private List<Pieza> piezasBodega;
	private List<Pieza> piezasPasadas;
	public static final String BODEGA = "Bodega";
	public static final String COLECCION = "Coleccion";
	
	public Inventario() 
	{
	piezasBodega = new ArrayList<Pieza>();
	piezasColeccioon = new ArrayList<Pieza>();
	piezasPasadas = new ArrayList<Pieza>();
	}
	
	public int getCapacidad() 
	{
	return piezasBodega.size() + piezasColeccioon.size();	
	}
	
	
	public ArrayList<Pieza> getPiezasTotales() 
	{
	ArrayList<Pieza> piezasTotales = new ArrayList<Pieza>();
	piezasTotales.addAll(piezasColeccioon);
	piezasTotales.addAll(piezasBodega);
	return piezasTotales;
	}

	public List<Pieza> getPiezasColeccioon() {
		return piezasColeccioon;
	}

	public List<Pieza> getPiezasBodega() {
		return piezasBodega;
	}

	public List<Pieza> getPiezasPasadas() {
		return piezasPasadas;
	}
	
	
	public void agregarPieza(String lugar, Pieza pieza) {
		if(lugar.equals(BODEGA)) {
			piezasBodega.add(pieza);
		}
		else if(lugar.equals(COLECCION)) {
			piezasColeccioon.add(pieza);
		}
		
	}

	public void eliminarPieza(String lugar, Pieza pieza) {
		if(lugar.equals(BODEGA)) {
			piezasBodega.remove(pieza);
		}
		else if(lugar.equals(COLECCION)) {
			piezasColeccioon.remove(pieza);
		}
		piezasPasadas.add(pieza);
	}
	
	public void verificarConsignacionPiezas() {
        
		LocalDate fechaActual = LocalDate.now();

        for (Pieza pieza : getPiezasBodega()) {
            if (pieza.verificarConsignacionPieza(fechaActual)) {
            	eliminarPieza(BODEGA,  pieza);
            }
        }
        for (Pieza pieza : getPiezasColeccioon()) {
            if (pieza.verificarConsignacionPieza(fechaActual)) {
            	eliminarPieza(COLECCION,  pieza);
            }
        }
	}

	public void setPiezasColeccioon(List<Pieza> piezasColeccioon) {
		this.piezasColeccioon = piezasColeccioon;
	}

	public void setPiezasBodega(List<Pieza> piezasBodega) {
		this.piezasBodega = piezasBodega;
	}

	public void setPiezasPasadas(List<Pieza> piezasPasadas) {
		this.piezasPasadas = piezasPasadas;
	}
	
	
	
	
}
