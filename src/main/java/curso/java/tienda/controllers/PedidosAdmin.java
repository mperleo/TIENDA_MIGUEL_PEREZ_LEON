package curso.java.tienda.controllers;

import java.util.HashMap;
import java.util.List;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.tienda.models.entities.Configuracion;
import curso.java.tienda.models.entities.DetallePedido;
import curso.java.tienda.models.entities.Pedido;
import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.DetallePedidoService;
import curso.java.tienda.service.PedidoService;
import curso.java.tienda.service.ProductoService;


@Controller
@RequestMapping("/admin/pedidos")
public class PedidosAdmin {
	@Autowired
	private PedidoService ps;
	@Autowired
	private DetallePedidoService dps;
	@Autowired
	private ProductoService prs;
	@Autowired
	private ConfiguracionService cs;
	
	private static Logger logger = LogManager.getLogger(PedidosAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
		List<Pedido> pedidos = ps.getListaPedidos();
		model.addAttribute("pedidos", pedidos);
		
		return "admin/pedidos/pedidos";
	}	
	
	@PostMapping("")
	public String verEstado(Model model, @RequestParam String estado) {
		List<Pedido> pedidos = null;
		
		if(estado.equals("todos")) {
			pedidos = ps.getListaPedidos();
		}
		else {
			pedidos = ps.getListaPedidosPorEstado(estado);
		}
		
		model.addAttribute("mensajeOk", "Mostrando los pedidos con estado' "+estado+"'");
		model.addAttribute("pedidos", pedidos);
		
		return "admin/pedidos/pedidos";
	}
	
	@GetMapping("ver/{id_pedido}")
	public String verPedido(Model model, @PathVariable("id_pedido") String id_pedido) {
		Pedido pedido = ps.getPedidoXId(Integer.parseInt(id_pedido));
		List<DetallePedido> lineas = dps.getDetallePedidoXIdPedido(Integer.parseInt(id_pedido));
		
		model.addAttribute("pedido", pedido);
		model.addAttribute("lineas", lineas);
		return "admin/pedidos/pedidoVer";
	}	
	
	@GetMapping("cancelar/{id_pedido}")
	public String cancelarPedido(Model model, @PathVariable("id_pedido") String id_pedido) {
		Pedido pedido = ps.getPedidoXId(Integer.parseInt(id_pedido));
		
		if(pedido.getEstado().equals("pendiente cancelación")) {
			pedido.setEstado("cancelado");
			ps.editPedido(pedido);
			
			model.addAttribute("mensajeOk", "El estado del pedido id:' "+id_pedido+"' se ha cambiado a cancelado ");
			logger.info("El estado del pedido id:' "+id_pedido+"' se ha cambiado a cancelado ");
			
			List<Pedido> pedidos = ps.getListaPedidos();
			model.addAttribute("pedidos", pedidos);
			return "admin/pedidos/pedidos";
		}
		else {
			model.addAttribute("mensaje", "El estado del pedido id:' "+id_pedido+"' no se puede cambiar a cancelado ");
			logger.error("El pedido id:' "+id_pedido+"' no se puede cancelar");
			
			List<Pedido> pedidos = ps.getListaPedidos();
			model.addAttribute("pedidos", pedidos);
			return "admin/pedidos/pedidos";
		}
	}	
	
	@GetMapping("procesar/{id_pedido}")
	public String procesarPedido(Model model, @PathVariable("id_pedido") String id_pedido) {
		Pedido pedido = ps.getPedidoXId(Integer.parseInt(id_pedido));
		List<DetallePedido> lineas = dps.getDetallePedidoXIdPedido(Integer.parseInt(id_pedido));
		HashMap<String, Configuracion> datos_factura = cs.getDatosFactura();
		
		if(pedido.getEstado().equals("pendiente")) {
			// quito las unidades del stock disponible en la tienda si se puede si no hay unidades disponibles no se permite la modificacion
			for(DetallePedido i: lineas) {
				Producto prodEditar = prs.getProductoXId(i.getProducto());
				Integer unidsPostVenta = prodEditar.getStock()-i.getUnidades();
				
				// solo se puede procesar el pedido si hay unidades suficientes
				if(unidsPostVenta<=prodEditar.getStock()) {
					prodEditar.setStock(unidsPostVenta);
					prs.editProducto(prodEditar);
				}
				else {
					model.addAttribute("mensaje", "El estado del pedido id:' "+id_pedido+"' no se puede cambiar a enviado, no hay unidades suficientes del producto id: "+prodEditar.getStock());
					logger.error("El pedido id:' "+id_pedido+"' no se puede cambiar a enviado, no hay unidades suficientes del producto id: "+prodEditar.getStock());
					
					List<Pedido> pedidos = ps.getListaPedidos();
					model.addAttribute("pedidos", pedidos);
					return "admin/pedidos/pedidos";
				}
			}
			
			// -------- MODIFICO LOS DATOS DEL PEDIDO Y GUARDO
			pedido.setEstado("enviado");
			
			Integer nFactNuevo = Integer.parseInt(datos_factura.get("factura_num").getValor())  +1;
			// actualizo el número de la factura al hacer el cmaibio de estado y lo meto
			datos_factura.get("factura_num").setValor(nFactNuevo.toString());
			// guardo el numero de la factura al pedido
			pedido.setNum_factura(nFactNuevo.toString());
			// guardo el pedido modificado en la bbdd, con el nuevo estado y el número de la factura
			ps.editPedido(pedido);
			
			model.addAttribute("mensajeOk", "El estado del pedido id:' "+id_pedido+"' se ha cambiado a enviado ");
			logger.info("El estado del pedido id:' "+id_pedido+"' se ha cambiado a cancelado ");
			
			List<Pedido> pedidos = ps.getListaPedidos();
			model.addAttribute("pedidos", pedidos);
			return "admin/pedidos/pedidos";
		}
		else {
			model.addAttribute("mensaje", "El estado del pedido id:' "+id_pedido+"' no se puede cambiar a enviado ");
			logger.error("El pedido id:' "+id_pedido+"' no se puede enviado");
			
			List<Pedido> pedidos = ps.getListaPedidos();
			model.addAttribute("pedidos", pedidos);
			return "admin/pedidos/pedidos";
		}
	}	
		
}
