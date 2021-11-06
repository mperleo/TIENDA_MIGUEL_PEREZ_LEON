package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer>{
	Rol findById(String id);
}
