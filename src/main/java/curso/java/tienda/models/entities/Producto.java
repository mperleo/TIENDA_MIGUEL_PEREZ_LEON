package curso.java.tienda.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto {

	@Id @GeneratedValue
	private Integer id;
	private Integer id_categoria;
	private String nombre;
	private String descripcion;
	private Double precio;
	private Double precioImpuesto;
	private Integer stock;
	private String fecha_alta;
	private String fecha_baja;
	private Float impuesto;
	private String imagen;
	@ManyToOne
	private Proveedor proveedor;


	public Producto() {
	}


	public Producto(Integer id,int id_categoria, String nombre, String descripcion, Double precio, Integer stock, String fecha_alta,
			String fecha_baja, Float impuesto, String imagen) {
		
		this.id = id;
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fecha_alta = fecha_alta;
		this.fecha_baja = fecha_baja;
		this.impuesto = impuesto;
		this.imagen = imagen;
	}


	public Producto(int id_categoria, String nombre, String descripcion, Double precio, Integer stock, String fecha_alta,
			String fecha_baja, Float impuesto, String imagen) {
		
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fecha_alta = fecha_alta;
		this.fecha_baja = fecha_baja;
		this.impuesto = impuesto;
		this.imagen = imagen;
	}
	
	


	/**
	 * @param id
	 * @param id_categoria
	 * @param nombre
	 * @param descripcion
	 * @param precio
	 * @param precioImpuesto
	 * @param stock
	 * @param fecha_alta
	 * @param fecha_baja
	 * @param impuesto
	 * @param imagen
	 * @param proveedor
	 */
	public Producto(Integer id, Integer id_categoria, String nombre, String descripcion, Double precio,
			Double precioImpuesto, Integer stock, String fecha_alta, String fecha_baja, Float impuesto, String imagen,
			Proveedor proveedor) {
		this.id = id;
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.precioImpuesto = precioImpuesto;
		this.stock = stock;
		this.fecha_alta = fecha_alta;
		this.fecha_baja = fecha_baja;
		this.impuesto = impuesto;
		this.imagen = imagen;
		this.proveedor = proveedor;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getId_categoria() {
		return id_categoria;
	}


	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Double getPrecio() {
		return precio;
	}
	
	public Double getPrecioImpuesto() {
		return precioImpuesto;
	}


	public void setPrecioImpuesto(Double precioImpuesto) {
		this.precioImpuesto = precioImpuesto;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public String getFecha_alta() {
		return fecha_alta;
	}


	public void setFecha_alta(String fecha_alta) {
		this.fecha_alta = fecha_alta;
	}


	public String getFecha_baja() {
		return fecha_baja;
	}


	public void setFecha_baja(String fecha_baja) {
		this.fecha_baja = fecha_baja;
	}


	public Float getImpuesto() {
		return impuesto;
	}


	public void setImpuesto(Float impuesto) {
		this.impuesto = impuesto;
	}


	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	/**
	 * @return the proveedor
	 */
	public Proveedor getProveedor() {
		return proveedor;
	}


	/**
	 * @param proveedor the proveedor to set
	 */
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	
}
