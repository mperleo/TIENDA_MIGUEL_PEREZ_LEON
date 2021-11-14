package curso.java.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	Pedido findById(String id);
	
	@Query(value="select MAX(id) from pedidos", nativeQuery=true)
	Integer buscarMaxId();
	
	@Query(value="select * from pedidos where id_usuario=?", nativeQuery=true)
	List<Pedido> buscarPedidosUsuario(String id_usuario);
	
	@Query(value="select * from pedidos where id_usuario=? AND estado=?", nativeQuery=true)
	List<Pedido> buscarPedidosUsuarioEstado(String id_usuario, String estado);
	
	@Query(value="select * from pedidos where estado=?", nativeQuery=true)
	List<Pedido> buscarPedidosEstado(String estado);
	
	@Query(value="select SUM(total) from pedidos WHERE estado='enviado' AND fecha LIKE ?%", nativeQuery=true)
	Double totalPedididosMes(String fecha);
	
	@Query(value="select SUM(total) from pedidos WHERE estado='enviado' AND fecha LIKE ?% GROUP BY MONTH(fecha)", nativeQuery=true)
	List<String> totalPedididosMeses(String fecha);
	
	@Query(value="select MONTH(fecha) from pedidos WHERE estado='enviado' AND fecha LIKE ?% GROUP BY MONTH(fecha)", nativeQuery=true)
	List<String> mesesPedididos(String fecha);
	
	/*@Query(value="select SUM(pedidos.total), categorias.nombre from pedidos "
			+ "INNER JOIN detalles_pedidos ON detalles_pedidos.id_pedido = pedidos.id "
			+ "INNER JOIN productos ON productos.id = detalles_pedidos.producto "
			+ "INNER JOIN categorias ON categorias.id = productos.id_categoria "
			+ "WHERE pedidos.estado='enviado' AND pedidos.fecha LIKE ?% GROUP BY(productos.id_categoria)", nativeQuery=true)
	List<String> totalPedidosPorCategoria(String fecha);*/
	
	@Query(value="select SUM(pedidos.total)from pedidos INNER JOIN detalles_pedidos ON detalles_pedidos.id_pedido = pedidos.id INNER JOIN productos ON productos.id = detalles_pedidos.producto INNER JOIN categorias ON categorias.id = productos.id_categoria WHERE pedidos.estado='enviado' AND pedidos.fecha LIKE ?% GROUP BY(productos.id_categoria)", nativeQuery=true)
	List<String> totalPedidosPorCategoria(String fecha);
	
	@Query(value="select categorias.id, categorias.nombre from pedidos INNER JOIN detalles_pedidos ON detalles_pedidos.id_pedido = pedidos.id INNER JOIN productos ON productos.id = detalles_pedidos.producto INNER JOIN categorias ON categorias.id = productos.id_categoria WHERE pedidos.estado='enviado' AND pedidos.fecha LIKE ?% GROUP BY(productos.id_categoria)", nativeQuery=true)
	List<String> totalPedidosPorCategoriaNombreCategoria(String fecha);
	
}
