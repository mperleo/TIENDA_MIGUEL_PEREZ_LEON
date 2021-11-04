package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final


public class MetodoPago {

	private Integer id;
	private String metodoPago;

	public MetodoPago() {
	}

	public MetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMetodoPago() {
		return this.metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

}
