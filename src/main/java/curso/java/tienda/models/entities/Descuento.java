package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final

import java.util.Date;

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
	private Date fechaInicio;
	private Date fechaFin;
	
	/**
	 * @param id
	 * @param codigo
	 * @param descuento
	 * @param fechaInicio
	 * @param fechaFin
	 */
	public Descuento(Integer id, String codigo, Float descuento, Date fechaInicio, Date fechaFin) {
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
}
