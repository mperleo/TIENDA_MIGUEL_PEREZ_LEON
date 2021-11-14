package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.Descuento;

public interface DescuentoRepository extends JpaRepository<Descuento, Integer>{
	Descuento findById(String id);
	
	@Query(value="select * from descuentos WHERE fecha_inicio < ?1 AND fecha_fin > ?1 ORDER BY fecha_inicio DESC LIMIT 1", nativeQuery=true)
	Descuento getDescActivo(String fechaHoy);
	
	@Query(value="select * from descuentos WHERE fecha_inicio < ?1 AND fecha_fin > ?1 AND codigo=?2", nativeQuery=true)
	Descuento getDescPorCodigo(String fechaHoy, String codigo);
}