package model;

public class Jugador {

	private String nombre;
	private int puntuacion;
	private String estado;
	private String pantalla;
	
	public Jugador() {
		
	}
	
	
	public Jugador(String nombre, int puntuacion, String estado, String pantalla) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.estado = estado;
		this.pantalla = pantalla;
	}


	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getPuntuacion() {
		return puntuacion;
	}



	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	

	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

	public String  getPantalla() {
		return pantalla;
	}


	public void setPantalla(String  pantalla) {
		this.pantalla = pantalla;
	}


	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", puntuacion=" + puntuacion + ", estado=" + estado + ", pantalla="
				+ pantalla + "]";
	}


	


	
	
	
}
