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

	@Query(value="select * from productos where categoria_id=?1 ORDER BY fecha_alta DESC", nativeQuery=true)
	List<Producto> buscarProductosPorCat(String id_cat);
	
	@Query(value="select * from productos where proveedor_id=?1 ", nativeQuery=true)
	List<Producto> buscarProductosPorProv(String id_prov);
	
	@Query(value="select * from productos where categoria_id=?1 ORDER BY fecha_alta DESC LIMIT 4 ", nativeQuery=true)
	List<Producto> buscar4ProductosPorCat(int i);
	
	// consulta usando la sintaxis de hibernate
	@Query(value="select p from Producto p where p.categoria.id=?1")
	List<Producto> findByIdCategoria(Integer id_categoria, Sort sort);
	
	@Query(value="select COUNT(*) from productos", nativeQuery=true)
	Integer numProds();
	
	@Query(value="select * from productos where stock<6 ORDER BY stock ASC", nativeQuery=true)
	List<Producto> buscarProductosPocoStock();
	
	@Query(value="select id from productos ORDER BY id ASC LIMIT 1", nativeQuery=true)
	Integer buscarIdUltimoProdcuto();
}
