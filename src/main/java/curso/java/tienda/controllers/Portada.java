package curso.java.tienda.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.DAO.Productos;
import curso.java.tienda.models.entities.Producto;

@Controller
@RequestMapping("")
public class Portada {
	
	public ArrayList<Producto> productos = new ArrayList<Producto>();
	
	@GetMapping("")
	public String portada() {
		return "index";
	}
	
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
	@GetMapping("/tienda")
	public String shop(Model model) {
		productos = Productos.seleccionar();
		model.addAttribute("productos", productos);
		return "shop";
	}
	
	@GetMapping("/producto")
	public String producto() {
		return "detail";
	}
	
	
}
