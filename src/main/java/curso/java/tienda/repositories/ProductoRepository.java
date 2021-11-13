package curso.java.tienda.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	Producto findById(String id);	
	
	@Query(value="select * from productos ORDER BY fecha_alta DESC", nativeQuery=true)
	List<Producto> buscarProductosOrdenFecha();

	@Query(value="select * from productos where id_categoria=?1 ORDER BY fecha_alta DESC", nativeQuery=true)
	List<Producto> buscarProductosPorCat(String id_cat);
	
	@Query(value="select * from productos where id_categoria=?1 ORDER BY fecha_alta DESC LIMIT 4 ", nativeQuery=true)
	List<Producto> buscar4ProductosPorCat(int i);
	
	// consulta usando la sintaxis de hibernate
	@Query(value="select p from Producto p where p.id_categoria=?1")
	List<Producto> findByIdCategoria(Integer id_categoria, Sort sort);
}
