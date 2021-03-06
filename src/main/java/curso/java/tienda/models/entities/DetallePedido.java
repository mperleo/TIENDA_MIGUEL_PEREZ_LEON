package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detalles_pedidos")
public class DetallePedido {
	
	@Id @GeneratedValue
	private Integer id;
	private int id_pedido;
	private Integer producto;
	private Double precioUnidad;
	private Integer unidades;
	private Float impuesto;
	private Double total;
	private String productoNombre;
	
	/**
	 * @param id
	 * @param id_pedido
	 * @param producto
	 * @param precioUnidad
	 * @param unidades
	 * @param impuesto
	 * @param total
	 * @param producto_nombre
	 */
	public DetallePedido(Integer id, int id_pedido, Integer producto, Double precioUnidad, Integer unidades,
			Float impuesto, Double total, String productoNombre) {
		super();
		this.id = id;
		this.id_pedido = id_pedido;
		this.producto = producto;
		this.precioUnidad = precioUnidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		this.total = total;
		this.productoNombre = productoNombre;
	}
	
	public DetallePedido() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Integer getProducto() {
		return producto;
	}

	public void setProducto(Integer producto) {
		this.producto = producto;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(Float impuesto) {
		this.impuesto = impuesto;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(Double double1) {
		this.precioUnidad = double1;
	}

	public String getProductoNombre() {
		return productoNombre;
	}

	public void setProductoNombre(String producto_nombre) {
		this.productoNombre = producto_nombre;
	}
	
}
