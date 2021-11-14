package curso.java.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findById(String id);
	
	Usuario findByEmail(String email);
	
	@Query(value="select * from usuarios where email=?1 and clave=?2", nativeQuery=true)
	List<Usuario> buscarUsuarioLogin(String email, String clave);

	@Query(value="select * from usuarios where id_rol=?1 ", nativeQuery=true)
	List<Usuario> buscarUsuarioPorRol(String id_rol);
	
	@Query(value="select COUNT(*) from usuarios", nativeQuery=true)
	Integer numUsuarios();
}
