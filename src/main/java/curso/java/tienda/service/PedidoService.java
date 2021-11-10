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
		Integer idNueva= pr.buscarMaxId();
		if(idNueva== null) {
			return 1;
		}
		else {
			return idNueva +1;
		}
	}
	
	public List<Pedido> getPedidosUsuario(Integer id_usuario) {
		
		return pr.buscarPedidosUsuario(id_usuario.toString());
	}
}