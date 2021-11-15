package curso.java.tienda.service;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository ur;
	
	public List<Usuario> getListaUsuarios() {
		return ur.findAll();
	}
	
	public List<Usuario> getListaUsuarioPorRol(String id_rol) {
		return ur.buscarUsuarioPorRol(id_rol);
	}
	
	public void addUsuario(Usuario usuario) {
		ur.save(usuario);
	}
	
	public void delUsuario(int id) {
		Usuario u = ur.getById(id);
		ur.delete(u);
	}
	
	public void editUsuario(Usuario u) {
		ur.save(u);
	}
	
	/**
	 * Función que devuelve el número de usuarios que hay registrados en la base de datos
	 * @return numero de usuarios registrados en la base de datos
	 */
	public Integer count() {
		return ur.numUsuarios();
	}
	
	/** 
     * Busca un usaurio en la base de datos con los datos obtenidos del formulario de login
     * 
     * @param usuario objeto con los datos del formulario de inicio de sesión
     * @return objeto usuario si se ha encontrado en la base de datos o nulo si no hay datos
     */
	public Usuario comprobarLogin(Usuario usuario) {	
		// busco el usuario con los datos del formulario para el email y la contrasela con el hash calculado
		List<Usuario> lista = ur.buscarUsuarioLogin(usuario.getEmail(), this.hashPassword(usuario.getClave()));
		if(!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}
	
	/** 
     * Busca un usuario por su id
     * @param id valor del id de un usuario de la base de datos
     * @return objeto usuario con el resultado de la consulta
     */
	public Usuario getUsuarioXId(int id) {
		Usuario u = ur.getById(id);
		return u;
	}
	
	/** 
     * Busca un usaurio por su correo
     * Se puede hacer al haber una condición de correo unico al crear el usuairo
     * @param email cadena con un email para buscarlo en la base de datos
     * @return objeto usuario si se ha encontrado en la base de datos o nulo si no hay datos
     */
	public Usuario getUsuarioXEmail(String email) {
		Usuario u = ur.findByEmail(email);
		return u;
	}
	
	/** 
     * Calcula el hash de una cadena.
     * Usando la librería Commons Codec
     * Uso para calcular el hash de una contraseña en el login y al crear un usuario
     * @param clave cadena con la contraseña para hacer el hash
     * @return hash de la contraseña indicada en una cadena
     */
	public String hashPassword(String clave) {
		Base64 hasher = new Base64();
		return new String(hasher.encode(clave.getBytes()));
	}
}
