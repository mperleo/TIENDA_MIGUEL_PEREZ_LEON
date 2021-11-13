package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.DetallePedido;
import curso.java.tienda.repositories.DetallePedidoRepository;

@Service
public class DetallePedidoService {
	
	@Autowired
	private DetallePedidoRepository dpr;
	
	public List<DetallePedido> getListaDetallePedidos() {
		return dpr.findAll();
		//return listaUsuarios;
	}
	
	public void addDetallePedido(DetallePedido dp) {
		dpr.save(dp);
	}
	
	public void delDetallePedido(int id) {
		DetallePedido dp = dpr.getById(id);
		dpr.delete(dp);
	}
	
	public void editDetallePedido(DetallePedido dp) {
		dpr.save(dp);
	}
	
	public DetallePedido getDetallePedidoXId(int id) {
		DetallePedido dp = dpr.getById(id);
		return dp;
	}
	
	public List<DetallePedido> getDetallePedidoXIdPedido(Integer id) {
		return  dpr.buscarLineasPedido(id.toString());
	}
}