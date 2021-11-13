package curso.java.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.Configuracion;

public interface ConfiguracionRepository extends JpaRepository<Configuracion, Integer>{
	Configuracion findById(String id);
	
	@Query(value="select * from configuracion where clave like 'factura_%' ", nativeQuery=true)
	List<Configuracion> buscarDatosFactura();
	
	@Query(value="select * from configuracion where clave like ? ", nativeQuery=true)
	Configuracion buscarPorClave(String clave);
}
