package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="metodos_pago")
public class MetodoPago {
	
	@Id @GeneratedValue
	private Integer id;
	private String metodo_pago;
	/**
	 * @param id
	 * @param metodoPago
	 */
	public MetodoPago(Integer id, String metodoPago) {
		this.id = id;
		this.metodo_pago = metodoPago;
	}

	public MetodoPago() {
	}

	public MetodoPago(String metodoPago) {
		this.metodo_pago = metodoPago;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetodo_pago() {
		return this.metodo_pago;
	}

	public void setMetodo_pago(String metodoPago) {
		this.metodo_pago = metodoPago;
	}

}
