package curso.java.tienda.service;

import java.util.List;

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
	
	public Usuario comprobarLogin(Usuario usuario) {	
		List<Usuario> lista = ur.buscarUsuarioLogin(usuario.getEmail(), usuario.getClave());
		if(!lista.isEmpty()) {
			return lista.get(0);
		}
		return null;
	}
	
	public Usuario getUsuarioXId(int id) {
		Usuario u = ur.getById(id);
		return u;
	}
	
	public Usuario getUsuarioXEmail(String email) {
		Usuario u = ur.findByEmail(email);
		return u;
	}
}
