package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedido {
	
	@Id @GeneratedValue
	private Integer id;
	
	private Integer id_usuario;
	private String fecha;
	private String metodo_pago;
	private String estado;
	private String num_factura;
	private Double total;
	
	/**
	 * @param id
	 * @param usuario
	 * @param fecha
	 * @param metodo__pago
	 * @param estado
	 * @param num_factura
	 * @param total
	 */
	public Pedido(Integer id, Integer usuario, String fecha, String metodo__pago, String estado, String num_factura,
			Double total) {
		this.id = id;
		this.id_usuario = usuario;
		this.fecha = fecha;
		this.metodo_pago = metodo__pago;
		this.estado = estado;
		this.num_factura = num_factura;
		this.total = total;
	}
	
	/**
	 * 
	 */
	public Pedido() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdUsuario() {
		return id_usuario;
	}
	public void setIdUsuario(Integer usuario) {
		this.id_usuario = usuario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getMetodo_pago() {
		return metodo_pago;
	}
	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNum_factura() {
		return num_factura;
	}
	public void setNum_factura(String num_factura) {
		this.num_factura = num_factura;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

}
