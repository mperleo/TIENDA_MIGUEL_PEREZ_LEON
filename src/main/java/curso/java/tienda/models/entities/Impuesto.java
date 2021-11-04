package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final


public class Impuesto  {

	private Integer id;
	private Float impuesto;

	public Impuesto() {
	}

	public Impuesto(Float impuesto) {
		this.impuesto = impuesto;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getImpuesto() {
		return this.impuesto;
	}

	public void setImpuesto(Float impuesto) {
		this.impuesto = impuesto;
	}

}
