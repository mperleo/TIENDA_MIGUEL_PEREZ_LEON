package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	Categoria findById(String id);
	
	@Query(value="select id from categorias ORDER BY id ASC LIMIT 1", nativeQuery=true)
	Integer buscarIdUltimaCat();
}
