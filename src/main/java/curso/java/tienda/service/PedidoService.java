package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Pedido;
import curso.java.tienda.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pr;
	
	public List<Pedido> getListaPedidos() {
		return pr.findAll();
		//return listaUsuarios;
	}
	
	public void addPedido(Pedido p) {
		pr.save(p);
	}
	
	public void delPedido(int id) {
		Pedido p = pr.getById(id);
		pr.delete(p);
	}
	
	public void editPedido(Pedido p) {
		pr.save(p);
	}
	
	public Pedido getPedidoXId(int id) {
		Pedido p = pr.getById(id);
		return p;
	}
	
	public Integer getPedidoIdMax() {
		return pr.buscarMaxId() +1;
	}
}