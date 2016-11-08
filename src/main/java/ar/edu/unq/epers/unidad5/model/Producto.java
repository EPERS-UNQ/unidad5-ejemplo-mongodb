package ar.edu.unq.epers.unidad5.model;

import java.util.ArrayList;
import java.util.List;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class Producto {
	
	@MongoId
	@MongoObjectId
	private String id;

	private String codigo;
	private String nombre;
	private String marca;
	
	private List<Precio> precios = new ArrayList<>();
	
	//Se necesita un constructor vacio para que jackson pueda
	//convertir de JSON a este objeto.
	protected Producto() {
	}
	
	public Producto(String codigo, String nombre, String marca) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.marca = marca;
	}

	public void addPrecio(Precio precio) {
		this.precios.add(precio);
	}
	
	public String getId() {
		return this.id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getMarca() {
		return this.marca;
	}

	public List<Precio> getPrecios() {
		return this.precios;
	}

}
