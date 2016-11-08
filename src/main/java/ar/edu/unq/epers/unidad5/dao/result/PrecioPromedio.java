package ar.edu.unq.epers.unidad5.dao.result;

import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

public class PrecioPromedio {
	
	@MongoId
	@MongoObjectId
	private String codigo;

	private int value;
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public int getValue() {
		return this.value;
	}
}
