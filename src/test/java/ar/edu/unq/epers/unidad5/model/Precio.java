package ar.edu.unq.epers.unidad5.model;

import java.time.LocalDate;

public class Precio {
	
	private Zona zona;
	private Usuario user;
	private int precio;
	
	private LocalDate fecha;

	//Se necesita un constructor vacio para que jackson pueda
	//convertir de JSON a este objeto.
	protected Precio() {
	}
	
	public Precio(Zona zona, Usuario user, int precio) {
		this.zona = zona;
		this.user = user;
		this.precio = precio;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public Usuario getUser() {
		return this.user;
	}
	
	public Zona getZona() {
		return this.zona;
	}
	
	public double getPrecio() {
		return this.precio;
	}

}
