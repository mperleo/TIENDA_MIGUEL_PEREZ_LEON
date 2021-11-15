package curso.java.tienda.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="valoraciones")
public class Valoracion{
	
	@Id @GeneratedValue
	private Integer id;
	@ManyToOne
	private Producto producto;
	@ManyToOne
	private Usuario usuario;
	
	@Min(value = 0, message=" La nota mínima es 0'") @Max(value=10, message=" La nota mínima es 0'")
	private Integer valoracion;
	@NotBlank(message=" El comentario es obligatorio")
	private String comentario;

	public Valoracion() {
	}

	/**
	 * @param id
	 * @param id_producto
	 * @param id_usuario
	 * @param valoracion
	 * @param comentario
	 */
	public Valoracion(Integer id, Producto producto, Usuario usuario, Integer valoracion, String comentario) {
		super();
		this.id = id;
		this.producto = producto;
		this.usuario = usuario;
		this.valoracion = valoracion;
		this.comentario = comentario;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the id_producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param id_producto the id_producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the id_usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param id_usuario the id_usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the valoracion
	 */
	public Integer getValoracion() {
		return valoracion;
	}

	/**
	 * @param valoracion the valoracion to set
	 */
	public void setValoracion(Integer valoracion) {
		this.valoracion = valoracion;
	}

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}

	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
