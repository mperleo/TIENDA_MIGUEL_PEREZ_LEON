package curso.java.tienda.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.tienda.models.entities.DetallePedido;
import curso.java.tienda.models.entities.Pedido;
import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.service.DetallePedidoService;
import curso.java.tienda.service.PedidoService;
import curso.java.tienda.utils.PedidosUtil;

@Controller
@RequestMapping("/pedidos")
public class PedidosUsuario {

	@Autowired
	private PedidoService peds;
	
	@Autowired
	private DetallePedidoService dps;
	
	@Autowired
	private PedidosUtil pu;
	
	private static Logger logger = LogManager.getLogger(PedidosUsuario.class);
	
	@GetMapping("")
	public String verPedidos(HttpSession session, Model model) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario !=null) {
			List<Pedido> listaPedidos = peds.getPedidosUsuario(usuario.getId());
			model.addAttribute("pedidos",listaPedidos);
			return "pedidos/pedidosUsuario";
		}
		else {
			model.addAttribute("mensaje", "para ver los pedidos tienes que iniciar sesión");
			logger.error("Intento de acceder a los listados de pedidos sin sesion iniciada");
			return "login/login";
		}
	}
	
	@PostMapping("")
	public String verEstado(HttpSession session, Model model, @RequestParam String estado) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario !=null) {
			List<Pedido> listaPedidos = null;
			
			if(estado.equals("todos")) {
				listaPedidos = peds.getPedidosUsuario(usuario.getId());
			}
			else {
				listaPedidos = peds.getPedidosUsuarioEstado(usuario.getId(),estado);
			}
			model.addAttribute("mensajeOk", "Mostrando los pedidos con estado '"+estado+"'");
			model.addAttribute("pedidos", listaPedidos);
			return "pedidos/pedidosUsuario";
		}
		else {
			model.addAttribute("mensaje", "para ver los pedidos tienes que iniciar sesión");
			logger.error("Intento de acceder a los listados de pedidos sin sesion iniciada");
			return "login/login";
		}
	}
	
	@GetMapping("ver/{id_pedido}")
	public String verPedido(HttpSession session, Model model, @PathVariable("id_pedido") String id_pedido) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario !=null) {
			Pedido pedido = peds.getPedidoXId(Integer.parseInt(id_pedido));
			if(usuario.getId() == pedido.getIdUsuario()) {
				List<DetallePedido> lineas = dps.getDetallePedidoXIdPedido(Integer.parseInt(id_pedido));
				
				model.addAttribute("pedido", pedido);
				model.addAttribute("lineas", lineas);
				return "pedidos/pedidoUsuario";
			}
			else {
				model.addAttribute("mensaje", "No tienes permiso para acceder a este pedido");
				logger.error("Intento de acceder a un pedido de otro usuario, id_usuario"+usuario.getId()+" id_pedido:"+id_pedido);
				return "error";
			}
				
		}
		else {
			model.addAttribute("mensaje", "Para ver el detalle del pedido tienes que iniciar sesión");
			logger.error("Intento de acceder al detalle de un pedido sin sesion iniciada, n_ped: "+id_pedido);
			return "login/login";
		}
	}
	
	@GetMapping("cancelar/{id_pedido}")
	public String cancelarPedido(HttpSession session, Model model, @PathVariable("id_pedido") String id_pedido) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario !=null) {
			Pedido pedido = peds.getPedidoXId(Integer.parseInt(id_pedido));
			if(usuario.getId() == pedido.getIdUsuario()) {
				// marco el estado del pedido como pendiente de cancelar
				pedido.setEstado("pendiente cancelación");
				peds.editPedido(pedido);
				
				// vuelvo a la vista de listar los pedidos
				List<Pedido> listaPedidos = peds.getPedidosUsuario(usuario.getId());
				model.addAttribute("pedidos",listaPedidos);
				model.addAttribute("mensajeOk", "Pedido marcado como pendiente para cancelar, un empleado cancelara el pedido en unos momentos");
				return "pedidos/pedidosUsuario";
			}
			else {
				model.addAttribute("mensaje", "No tienes permiso para acceder a este pedido");
				logger.error("Intento de cancelar un pedido de otro usuario, id_usuario"+usuario.getId()+" id_pedido:"+id_pedido);
				return "error";
			}
				
		}
		else {
			model.addAttribute("mensaje", "Para ver el cancelar un pedido tienes que iniciar sesión");
			logger.error("Intento de acceder al detalle de un pedido sin sesion iniciada, n_ped: "+id_pedido);
			return "login/login";
		}
	}
	
	@GetMapping("borrarLinea/{id_linea}")
	public String borrarLinea(HttpSession session, Model model, @PathVariable("id_linea") String id_linea) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario !=null) {
			DetallePedido lineaBorrar = dps.getDetallePedidoXId(Integer.parseInt(id_linea));
			Pedido pedido = peds.getPedidoXId(lineaBorrar.getId_pedido());
			if(usuario.getId() == pedido.getIdUsuario()) {
				// vuelvo a calcular el total del pedido y lo guardo en la base de datos
				// si el pedido tiene aplicado un descuento hay que calcular el precio del ariculo con el descuento antes de hacer la resta
				if(pedido.getDescuento()!= null) {
					Double pedidoNuevoTotal = pedido.getTotal() - (lineaBorrar.getTotal()* (1- (pedido.getDescuento()/100)));
					pedidoNuevoTotal = DoubleRounder.round(pedidoNuevoTotal, 3);
					pedido.setTotal(pedidoNuevoTotal); 
					peds.addPedido(pedido);
				}
				// si no hay un descuento aplicado lo resto directamente
				else {
					Double pedidoNuevoTotal = pedido.getTotal() - lineaBorrar.getTotal();
					pedido.setTotal(pedidoNuevoTotal); 
					peds.addPedido(pedido);
				}
				
				
				// borro la linea del pedido
				dps.delDetallePedido(Integer.parseInt(id_linea));
				
				// saco los datos y muestro
				List<DetallePedido> lineas = dps.getDetallePedidoXIdPedido(pedido.getId());
				
				model.addAttribute("pedido", pedido);
				model.addAttribute("lineas", lineas);
				model.addAttribute("mensajeOk", "Linea de pedido borrada correctamente");
				return "pedidos/pedidoUsuario";
			}
			else {
				model.addAttribute("mensaje", "No tienes permiso para borrar esta linea de pedido");
				logger.error("Intento de borrar una linea de pedido en un pedido de otro usuario, id_usuario"+usuario.getId()+" id_pedido:"+pedido.getId()+" id_linea:"+id_linea);
				return "error";
			}
		}
		else {
			model.addAttribute("mensaje", "Para ver los pedidos tienes que iniciar sesión");
			return "login/login";
		}
	}
	
	@GetMapping("generarFactura/{id_pedido}")
	public void generarFactura(HttpSession session, HttpServletResponse response, Model model, @PathVariable("id_pedido") String id_pedido) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario !=null) {
			Pedido pedido = peds.getPedidoXId(Integer.parseInt(id_pedido));
			if(usuario.getId() == pedido.getIdUsuario()) {
				List<DetallePedido> lineas = dps.getDetallePedidoXIdPedido(Integer.parseInt(id_pedido));
				String nombreArchivo = pu.generarPdf(pedido, lineas);
				try {
					// la funcion getDesktop no funciona en los navegadores en los que he probado el proyecto y para hacer que se decargue el fichero se mada por el response
					// por esto la pagina se debe cargar en un enlace que vaya a una página con target blank
					FileInputStream fis = new FileInputStream("./ficherosPdf/"+nombreArchivo);
					String mimeType = "application/pdf";
					response.setContentType(mimeType);
					response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", nombreArchivo));
					response.setContentLength(fis.available());
					FileCopyUtils.copy(fis, response.getOutputStream());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error("Error al leer el fichero pdf para descargarlo , error: "+ e.getMessage());
					e.printStackTrace();
				}


			}
			else {
				logger.error("Intento de acceder a un pedido de otro usuario, id_usuario"+usuario.getId()+" id_pedido:"+id_pedido);
				//return "error";
			}
				
		}
		else {
			logger.error("Intento de acceder al detalle de un pedido sin sesion iniciada, n_ped: "+id_pedido);
			//return "login/login";
		}
	}
	
	
}
