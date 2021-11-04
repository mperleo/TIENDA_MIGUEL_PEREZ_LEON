package curso.java.tienda.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rol{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String rol;
	
	public Rol(){}
	
	/**
	 * @param id
	 * @param rol
	 */
	public Rol(int id, String rol) {
		this.id = id;
		this.rol = rol;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	
}
