package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Integer>{
	Valoracion findById(String id);
}