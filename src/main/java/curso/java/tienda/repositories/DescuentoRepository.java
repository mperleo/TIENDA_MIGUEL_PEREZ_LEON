package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.Descuento;

public interface DescuentoRepository extends JpaRepository<Descuento, Integer>{
	Descuento findById(String id);
}