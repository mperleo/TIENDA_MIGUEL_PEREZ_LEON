package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.Impuesto;

public interface ImpuestoRepository extends JpaRepository<Impuesto, Integer>{
	Impuesto findById(String id);
}