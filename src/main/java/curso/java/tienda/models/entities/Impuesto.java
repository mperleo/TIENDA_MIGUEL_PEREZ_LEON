package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="impuestos")
public class Impuesto  {
	
	@Id @GeneratedValue
	private Integer id;
	private Float impuesto;

	public Impuesto() {
	}

	public Impuesto(Float impuesto) {
		this.impuesto = impuesto;
	}

	/**
	 * @param id
	 * @param d
	 */
	public Impuesto(Integer id, Float d) {
		this.id = id;
		this.impuesto = d;
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
