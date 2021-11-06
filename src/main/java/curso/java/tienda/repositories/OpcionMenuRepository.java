package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.OpcionMenu;

public interface OpcionMenuRepository extends JpaRepository<OpcionMenu, Integer>{
	OpcionMenu findById(String id);
}