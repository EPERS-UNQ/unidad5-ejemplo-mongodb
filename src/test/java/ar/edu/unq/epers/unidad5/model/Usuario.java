package ar.edu.unq.epers.unidad5.model;

public class Usuario {

	private String username;
	
	//Se necesita un constructor vacio para que jackson pueda
	//convertir de JSON a este objeto.
	protected Usuario() {
	}

	public Usuario(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}
	
}
