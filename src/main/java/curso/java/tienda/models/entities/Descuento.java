package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="descuentos")
public class Descuento {
	
	@Id @GeneratedValue
	private Integer id;
	private String codigo;
	private Float descuento;
	private String fechaInicio;
	private String fechaFin;
	
	/**
	 * @param id
	 * @param codigo
	 * @param descuento
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public Descuento(Integer id, String codigo, Float descuento, String fechaInicio, String fechaFin) {
		this.id = id;
		this.codigo = codigo;
		this.descuento = descuento;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public Descuento() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
