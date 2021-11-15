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
	}
	
	public List<Pedido> getListaPedidosPorEstado(String estado) {
		return pr.buscarPedidosEstado(estado);
	}
	
	public Integer getIdUltimoProd() {
		return pr.buscarIdUltimoPedido();
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
	
	public List<Pedido> getPedidosUsuario(Integer id_usuario) {
		return pr.buscarPedidosUsuario(id_usuario.toString());
	}
	
	public List<Pedido> getPedidosUsuarioEstado(Integer id_usuario, String estado) {
		return pr.buscarPedidosUsuarioEstado(id_usuario.toString(), estado);
	}
	
	public Double getTotalPedidosEsteMes(String fecha) {
		return pr.totalPedididosMes(fecha);
	}
	
	public List<String> getTotalPedidosMeses(String fecha){
		return pr.totalPedididosMeses(fecha);
	}
	
	public List<String> getMesesPedidos	(String fecha){	    
		return pr.mesesPedididos(fecha);
	}
	
	public List<String> getTotalPedidosPorCategoria	(String fecha){	    
		return pr.totalPedidosPorCategoria(fecha);
	}
	
	public List<String> getTotalPedidosPorCategoriaNombreCategoria(String fecha){	    
		return pr.totalPedidosPorCategoriaNombreCategoria(fecha);
	}
}