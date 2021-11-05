package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.models.entities.Usuario;


public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	Usuario findById(String id);
}
