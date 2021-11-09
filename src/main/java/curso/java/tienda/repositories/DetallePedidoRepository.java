package curso.java.tienda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.tienda.models.entities.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{
	DetallePedido findById(String id);
	
	@Query(value="select * from detalles_pedidos where id_pedido=?", nativeQuery=true)
	List<DetallePedido> buscarLineasPedido(String id_pedido);
}

