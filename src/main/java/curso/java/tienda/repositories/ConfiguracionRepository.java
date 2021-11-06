package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.Configuracion;

public interface ConfiguracionRepository extends JpaRepository<Configuracion, Integer>{
	Configuracion findById(String id);
}
