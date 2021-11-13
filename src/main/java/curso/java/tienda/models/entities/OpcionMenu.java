package curso.java.tienda.models.entities;
// Generated 4 nov 2021 19:04:44 by Hibernate Tools 4.3.5.Final

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="opciones_menu")
public class OpcionMenu {

	@Id @GeneratedValue
	private Integer id;
	private int id_rol;
	private String nombre_opcion;
	private String url_opcion;

	public OpcionMenu() {
	}

	/**
	 * @param id
	 * @param id_rol
	 * @param nombre_opcion
	 * @param url_opcion
	 */
	public OpcionMenu(Integer id, int id_rol, String nombre_opcion, String url_opcion) {
		this.id = id;
		this.id_rol = id_rol;
		this.nombre_opcion = nombre_opcion;
		this.url_opcion = url_opcion;
	}
	
	/**
	 * @param id
	 * @param id_rol
	 * @param nombre_opcion
	 * @param url_opcion
	 */
	public OpcionMenu(int id_rol, String nombre_opcion, String url_opcion) {
		this.id_rol = id_rol;
		this.nombre_opcion = nombre_opcion;
		this.url_opcion = url_opcion;
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
	 * @return the id_rol
	 */
	public int getId_rol() {
		return id_rol;
	}

	/**
	 * @param id_rol the id_rol to set
	 */
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	/**
	 * @return the nombre_opcion
	 */
	public String getNombre_opcion() {
		return nombre_opcion;
	}

	/**
	 * @param nombre_opcion the nombre_opcion to set
	 */
	public void setNombre_opcion(String nombre_opcion) {
		this.nombre_opcion = nombre_opcion;
	}

	/**
	 * @return the url_opcion
	 */
	public String getUrl_opcion() {
		return url_opcion;
	}

	/**
	 * @param url_opcion the url_opcion to set
	 */
	public void setUrl_opcion(String url_opcion) {
		this.url_opcion = url_opcion;
	}
}
