package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final

import java.util.Date;

public class Pedido {

	private Integer id;
	private Usuario usuario;
	private Date fecha;
	private String metodo__pago;
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
	public Pedido(Integer id, Usuario usuario, Date fecha, String metodo__pago, String estado, String num_factura,
			Double total) {
		this.id = id;
		this.usuario = usuario;
		this.fecha = fecha;
		this.metodo__pago = metodo__pago;
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
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMetodo__pago() {
		return metodo__pago;
	}
	public void setMetodo__pago(String metodo__pago) {
		this.metodo__pago = metodo__pago;
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
