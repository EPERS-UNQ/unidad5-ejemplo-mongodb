package ar.edu.unq.epers.unidad5.model;

public class Zona {

		private String calle;
		private String altura;
		private String localidad;
		private String pais;
		
		//Se necesita un constructor vacio para que jackson pueda
		//convertir de JSON a este objeto.
		protected Zona() {
		}
		
		public Zona( String calle, String altura, String localidad, String pais) {
			this.localidad = localidad;
			this.calle = calle;
			this.altura = altura;
			this.pais = pais;
		}
		
		public String getLocalidad() {
			return this.localidad;
		}
		public String getCalle() {
			return this.calle;
		}
		public String getAltura() {
			return this.altura;
		}
		public String getPais() {
			return this.pais;
		}
	
}
