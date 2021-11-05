package curso.java.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.service.ProductoService;

@Controller
@RequestMapping("")
public class Portada {
	
	@Autowired
	private ProductoService ps;
	
	@GetMapping("")
	public String portada(Model model) {
		List<Producto> productos = ps.getListaProductos();
		model.addAttribute("productos", productos);
		return "index";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Producto> productos = ps.getListaProductos();
		model.addAttribute("productos", productos);
		return "index";
	}
	
	@GetMapping("/tienda")
	public String shop(Model model) {
		List<Producto> productos = ps.getListaProductos();
		model.addAttribute("productos", productos);
		return "shop";
	}
}
