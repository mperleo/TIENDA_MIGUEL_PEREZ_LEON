package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	Categoria findById(String id);
}
