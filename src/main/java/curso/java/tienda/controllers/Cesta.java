package curso.java.tienda.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.entities.DetallePedido;
import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.service.ProductoService;

@Controller
@RequestMapping("/cesta")
public class Cesta {
	
	@Autowired
	private ProductoService ps;
	
	private static Logger logger = LogManager.getLogger(Cesta.class);
	
	@GetMapping("")
	public String portada(Model model, HttpSession session) {
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
		
		model.addAttribute("cesta", cestaLista);
		model.addAttribute("total", total);
		
		return "cart";
	}
	
	@GetMapping("add/{id_prod}")
	public String addProd(Model model, HttpSession session, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		
		HashMap<Integer, DetallePedido> cesta = (HashMap<Integer, DetallePedido>) session.getAttribute("cesta");
		
		if(cesta == null || !cesta.containsKey(id_producto)) {
			Producto nuevo = ps.getProductoXId(id_producto);
			
			if(nuevo != null) {
				DetallePedido nuevaLinea = new DetallePedido();
				nuevaLinea.setProducto(id_producto);
				nuevaLinea.setPrecioUnidad(nuevo.getPrecio());
				nuevaLinea.setImpuesto(nuevo.getImpuesto());
				nuevaLinea.setUnidades(1);
				nuevaLinea.setTotal(nuevo.getPrecio()*nuevaLinea.getUnidades());
				
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
		
		ArrayList<DetallePedido> cestaLista = new ArrayList<DetallePedido>();
		Double total = (double) 0;
		
		return "redirect:/cesta";
	}
	
	@GetMapping("down/{id_prod}")
	public String downProd(Model model, HttpSession session, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		
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
		return "cart";
	}
	
}
