package curso.java.tienda.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.tienda.models.entities.Descuento;
import curso.java.tienda.models.entities.DetallePedido;
import curso.java.tienda.models.entities.MetodoPago;
import curso.java.tienda.models.entities.Pedido;
import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.models.entities.Usuario;

import curso.java.tienda.service.ProductoService;
import curso.java.tienda.utils.FechasUtil;
import curso.java.tienda.service.PedidoService;
import curso.java.tienda.service.DescuentoService;
import curso.java.tienda.service.DetallePedidoService;
import curso.java.tienda.service.MetodoPagoService;

@Controller
@RequestMapping("/cesta")
public class Cesta {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private PedidoService peds;
	@Autowired
	private DetallePedidoService dps;
	@Autowired
	private MetodoPagoService ms;
	@Autowired
	private DescuentoService ds;
	
	@Autowired
	private FechasUtil eu;
	
	private static Logger logger = LogManager.getLogger(Cesta.class);
	
	@GetMapping("")
	public String portada(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, DetallePedido> cesta = (HashMap<Integer, DetallePedido>) session.getAttribute("cesta");
		
		ArrayList<DetallePedido> cestaLista = new ArrayList<DetallePedido>();
		Double total = (double) 0;
		Integer nProds = 0;
		
		if(cesta != null) {
			Set<Integer> i = cesta.keySet();
			
			for( Integer key: i) {
				cestaLista.add(cesta.get(key));
				total+=cesta.get(key).getTotal();
				nProds+=cesta.get(key).getUnidades();
				
			}
			session.setAttribute("nProds", nProds);
		}
		else {
			session.setAttribute("nProds", 0);
		}
		
		Double subtotal = total;
		
		// si hay descuento en la sesion guardo hago el descuento al mostrar el precio final
		Descuento descuento = (Descuento)session.getAttribute("descuento");
		if(descuento != null) {
			total = total * (1- (descuento.getDescuento()/100));
			total = DoubleRounder.round(total, 3);
		}
		
		model.addAttribute("cesta", cestaLista);
		model.addAttribute("total", total);
		model.addAttribute("subtotal", subtotal);
		return "cesta/cart";
	}
	
	@GetMapping("add/{id_prod}")
	public String addProd(Model model, HttpSession session, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		@SuppressWarnings("unchecked")
		HashMap<Integer, DetallePedido> cesta = (HashMap<Integer, DetallePedido>) session.getAttribute("cesta");
		
		if(cesta == null || !cesta.containsKey(id_producto)) {
			Producto nuevo = ps.getProductoXId(id_producto);
			
			if(nuevo != null) {
				DetallePedido nuevaLinea = new DetallePedido();
				nuevaLinea.setProducto(id_producto);
				nuevaLinea.setPrecioUnidad(nuevo.getPrecioImpuesto());
				nuevaLinea.setImpuesto(nuevo.getImpuesto());
				nuevaLinea.setUnidades(1);
				nuevaLinea.setTotal(nuevo.getPrecioImpuesto()*nuevaLinea.getUnidades());
				nuevaLinea.setProductoNombre(nuevo.getNombre());
				
				// si la cesta no tiene elementos
				// si la cesta contiene elementos pero no contiene el objeto no se mira
				if(cesta == null) {
					cesta = new HashMap<Integer, DetallePedido>();
				}
				cesta.put(id_producto, nuevaLinea);
				
			}	
		}
		else if(cesta.containsKey(id_producto)) {
			DetallePedido lineaPedido = cesta.get(id_producto);
			
			lineaPedido.setUnidades(lineaPedido.getUnidades()+1);
			lineaPedido.setTotal(lineaPedido.getPrecioUnidad()*lineaPedido.getUnidades());
			
			cesta.replace(id_producto, lineaPedido);
			
		}
		else {
			logger.error("Intento de añadir al carro un producto que no existe por referencia, n.ref: "+id_prod);
		}
		
		session.setAttribute("cesta", cesta);
		
		return "redirect:/cesta";
	}
	
	@GetMapping("down/{id_prod}")
	public String downProd(Model model, HttpSession session, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		@SuppressWarnings("unchecked")
		HashMap<Integer, DetallePedido> cesta = (HashMap<Integer, DetallePedido>) session.getAttribute("cesta");
		
		if(cesta != null && cesta.containsKey(id_producto)) {
			DetallePedido lineaPedido = cesta.get(id_producto);
			
			lineaPedido.setUnidades(lineaPedido.getUnidades()-1);
			lineaPedido.setTotal(lineaPedido.getPrecioUnidad()*lineaPedido.getUnidades());
			
			if(lineaPedido.getUnidades()!=0) {
				cesta.replace(id_producto, lineaPedido);
				
			}
			else {
				cesta.remove(id_producto);
			}
			
		}
		else {
			logger.error("Intento de retirar del carro un producto que no esta en la cesta, n.ref: "+id_prod);
		}
		
		session.setAttribute("cesta", cesta);
		
		return "redirect:/cesta";
	}
	
	@GetMapping("eliminar/{id_prod}")
	public String elimLineaProd(Model model, HttpSession session, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		
		@SuppressWarnings("unchecked")
		HashMap<Integer, DetallePedido> cesta = (HashMap<Integer, DetallePedido>) session.getAttribute("cesta");
		
		if(cesta != null && cesta.containsKey(id_producto)) {
			cesta.remove(id_producto);			
		}
		else {
			logger.error("Intento de retirar del carro un producto que no esta en la cesta, n.ref: "+id_prod);
		}
		
		session.setAttribute("cesta", cesta);
		
		
		return "redirect:/cesta";
	}
	
	@GetMapping("vaciarCesta")
	public String vaciarLista(Model model, HttpSession session) {
		session.setAttribute("cesta", null);
		return "cesta/cart";
	}
	
	@PostMapping("aplicarDescuento")
	public String aplicarDescuento(@RequestParam String codigo, HttpSession session) {
		Descuento descuento = ds.getDescuentoPorCodigo(eu.fechaFormato("yyyy-MM-dd"), codigo);
		session.setAttribute("descuento", descuento);
		return "redirect:/cesta";
	}
	
	@GetMapping("pedido")
	public String pedido(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		HashMap<Integer, DetallePedido> cesta = (HashMap<Integer, DetallePedido>) session.getAttribute("cesta");
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		ArrayList<DetallePedido> cestaLista = new ArrayList<DetallePedido>();
		
		if(usuario !=null) {
			if(cesta !=null) {
				// creo el objeto pedido y los objetos para calcular datos
				Pedido pedido = new Pedido();
				LocalDate fecha = LocalDate.now();
				Double total = 0.0;
				
				// calculo el total de las lineas de pedido
				Set<Integer> i = cesta.keySet();
				for( Integer key: i) {
					cestaLista.add(cesta.get(key));
					total+=cesta.get(key).getTotal();
				}
				// si hay descuento en la sesion guardo hago el descuento al mostrar el precio final
				Descuento descuento = (Descuento)session.getAttribute("descuento");
				if(descuento != null) {
					total = total * (1- (descuento.getDescuento()/100));
					total = DoubleRounder.round(total, 3);
					pedido.setDescuento(descuento.getDescuento());
					pedido.setCodigoDescuento(descuento.getCodigo());
				}
				// meto los datos del pedido
				pedido.setIdUsuario(usuario.getId());
				pedido.setEstado("pendiente");
				pedido.setTotal(total);
				pedido.setFecha(fecha.toString());
				
				//guardo el pedido en la sesion
				session.setAttribute("pedido", pedido);
				
				List<MetodoPago> metodosPago = ms.getListaMetodosPago();
				model.addAttribute("metodosPago", metodosPago);
				// guardo los parametros que se van a mostrar en la vista
				model.addAttribute("pedido", pedido);
				model.addAttribute("cesta", cestaLista);
				
				logger.info("Pedido con los datos iniciados y guardado en la sesion");
				return "cesta/checkout";
			}
			else{
				logger.error("Intento de relizar pedido sin productos en la cesta");
				model.addAttribute("mensaje", "No hay productos en la cesta");
				return "error";
			}
		}
		else {
			logger.error("Intento de realizar pedido por un usuario no registrado");
			model.addAttribute("mensaje", "Tienes que iniciar sesion para hacer un pedido");
			return "login/login";
		}	
	
	}
	
	@PostMapping("pedido/guardar")
	public String pedidoGuardar(Model model, HttpSession session, @RequestParam String metodo_pago, RedirectAttributes redirectAttributes) {
		Pedido pedido = (Pedido) session.getAttribute("pedido");
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		@SuppressWarnings("unchecked")
		HashMap<Integer, DetallePedido> cesta = (HashMap<Integer, DetallePedido>) session.getAttribute("cesta");
		
		if(usuario !=null) {
			if(pedido !=null) {
				// completo los datos que faltan
				pedido.setMetodo_pago(metodo_pago);
				pedido.setId(peds.getPedidoIdMax());
				
				// guardo en la base de datos el pedido
				peds.addPedido(pedido);
				
				// completo los datos que faltan a las lineas y las guardo en la base de datos
				Set<Integer> i = cesta.keySet();
				for( Integer key: i) {
					// completo el dato
					cesta.get(key).setId_pedido(pedido.getId());
					// subo el elemento
					dps.addDetallePedido(cesta.get(key));
				}
				
				// anulo los elementos de sesion
				session.setAttribute("pedido", null);
				session.setAttribute("cesta", null);
				session.setAttribute("nProds", 0);
				
				logger.info("Pedido guardado en la bbdd con exito");
				redirectAttributes.addAttribute("mensajeOk", "Pedido creado con exito ");
				return "redirect:/home";
			}
			else {
				logger.error("Intento de guardar un pedido sin tener el pedido en sesion");
				model.addAttribute("mensaje", "No hay pedido en la sesion");
				return "error";
			}
		}
		else {
			logger.error("Intento de guardar un pedido sin estar iniciada la sesión");
			redirectAttributes.addAttribute("mensaje", "Tienes que iniciar sesion para hacer un pedido");
			return "redirect:/login";
		}
		
	}
	
}
