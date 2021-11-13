package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
	Proveedor findById(String id);
}