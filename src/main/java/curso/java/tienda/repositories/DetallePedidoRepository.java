package curso.java.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.tienda.models.entities.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{
	DetallePedido findById(String id);
}

