package curso.java.tienda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductosUsuario {
	
	@Autowired
	private ProductoService ps;
	
	@GetMapping("ver/{id_prod}")
	public String producto(Model model, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		Producto prod = ps.getProductoXId(id_producto);
		model.addAttribute("producto", prod);
		return "detail";
	}
}
