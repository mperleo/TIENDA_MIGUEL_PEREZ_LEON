package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	Producto findById(String id);
}