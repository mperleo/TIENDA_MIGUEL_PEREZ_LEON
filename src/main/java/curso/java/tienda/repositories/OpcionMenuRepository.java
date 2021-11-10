package curso.java.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.OpcionMenu;

public interface OpcionMenuRepository extends JpaRepository<OpcionMenu, Integer>{
	OpcionMenu findById(String id);
	
	@Query(value="select * from pedidos where id_rol=?", nativeQuery=true)
	List<OpcionMenu> buscarOpcionesRol(Integer id_rol);
	
}