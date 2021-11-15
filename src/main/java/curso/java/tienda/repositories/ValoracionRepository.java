package curso.java.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Integer>{
	Valoracion findById(String id);
	
	@Query(value="SELECT * FROM valoraciones WHERE producto_id=?", nativeQuery=true)
	List<Valoracion> findByProd(Integer id_producto);
	
	@Query(value="SELECT * FROM valoraciones WHERE producto_id=?1 and usuario_id=?2", nativeQuery=true)
	Valoracion findByProdAndUser(Integer id_producto, Integer id_usuario);
	
	@Query(value="SELECT CASE WHEN EXISTS ( "
					+ "SELECT detalles_pedidos.producto from pedidos "
					+ "INNER JOIN detalles_pedidos ON detalles_pedidos.id_pedido = pedidos.id "
					+ "WHERE pedidos.estado='enviado' AND pedidos.id_usuario=?1 AND detalles_pedidos.producto=?2 "
				+ ")  THEN 'true' ELSE 'false' END", nativeQuery=true)
	String puedeValorarUsuario(Integer id_usuario, Integer id_producto);
}