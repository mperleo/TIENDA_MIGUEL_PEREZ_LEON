package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.MetodoPago;

public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer>{
	MetodoPago findById(String id);
}