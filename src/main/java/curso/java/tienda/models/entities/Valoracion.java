package curso.java.tienda.models.entities;

public class Valoracion{
	private Integer id;
	private int id_producto;
	private int id_usuario;
	private Integer valoracion;
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
	public Valoracion(Integer id, int id_producto, int id_usuario, Integer valoracion, String comentario) {
		super();
		this.id = id;
		this.id_producto = id_producto;
		this.id_usuario = id_usuario;
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
	public int getId_producto() {
		return id_producto;
	}

	/**
	 * @param id_producto the id_producto to set
	 */
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	/**
	 * @return the id_usuario
	 */
	public int getId_usuario() {
		return id_usuario;
	}

	/**
	 * @param id_usuario the id_usuario to set
	 */
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
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
