package ar.edu.unq.epers.unidad5.dao;

import java.util.List;

import org.jongo.Aggregate.ResultsIterator;

import ar.edu.unq.epers.unidad5.dao.result.PrecioPromedio;
import ar.edu.unq.epers.unidad5.model.Producto;
import ar.edu.unq.epers.unidad5.model.Zona;

public class ProductoDAO extends GenericMongoDAO<Producto> {

	public ProductoDAO() {
		super(Producto.class);
	}

	public List<Producto> getByMarca(String marca) {
		return this.find("{ marca: # }", marca);
	}

	public List<Producto> getByPrecio(int precio) {
		return this.find("{ precios.precio: # }", precio);
	}

	public List<Producto> getByRangoPrecio(int min, int max) {
		// Esta query escrita de esta forma solamente asegura que existe algun objeto
		// en precios cuyo precio es mayor que X y que existe algun objeto en precios
		// cuyo preico es menor que Y, no necesariamente X e Y son el mismo objeto.
		// return this.find("{ precios.precio: { $gt: #, $lt: #} }", min, max);
		
		// Esta otra query es similar, pero asegura que X e Y sean el mismo objeto precio
		// que satisfaga ambas condiciones
		return this.find("{ precios: { "
				+ "	$elemMatch: { "
				+ "		precio: { $gt: #, $lt: # } "
				+ "	} "
				+ "} }", min, max);
	}
	
	public List<Producto> getPorPrecioEnZona(int max, Zona zona) {
		return this.find("{ precios: { "
				+ "	$elemMatch: { "
				+ "		precio: { $lt: # }, "
				+ "		zona: # "
				+ "	} "
				+ "} }", max, zona);
	}
	
	public List<PrecioPromedio> getPrecioPromedio(List<String> codigos) {
		// match:   primero selecciona los items
		// unwind:  luego convierte una lista de n productos con m precios cada uno en una lista de n*m productos con 1 precio cada uno
		// project:  solo se queda con el codigo y el precios.precio de cada producto
		// group:    agrupa por codigo (recordemos que despues del unwind codigo no es unico sino hay m productos con el mismo codigo
		//                y diferentes precios).  Dentro de cada grupo calcula el promedio del campo precios.precio
		// sort:       ordena el resultado por _id (codigo) de forma ascendiente
		
		ResultsIterator<PrecioPromedio> result = this.mongoCollection
				.aggregate("{ $match: { codigo: { $in: # } } }", codigos)
				.and(" { $unwind: \"$precios\" } ")
				.and(" { $project: { codigo: 1, precios: { precio: 1 } } }")
				.and(" { $group: { "
						+ "	_id: \"$codigo\", "
						+ "	value: { $avg: \"$precios.precio\" } "
						+ "} }")
				.and(" { $sort: { _id: 1 } }")
				.as(PrecioPromedio.class);
		
		return this.copyToList(result);
	}
	

}
