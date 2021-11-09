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
}
