package curso.java.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	Producto findById(String id);

	@Query(value="select * from productos where id_categoria=?1 ", nativeQuery=true)
	List<Producto> buscarProductosPorCat(String id_cat);
	
	@Query(value="select * from productos where id_categoria=?1 ORDER BY fecha_alta ASC LIMIT 4 ", nativeQuery=true)
	List<Producto> buscar4ProductosPorCat(int i);
}
