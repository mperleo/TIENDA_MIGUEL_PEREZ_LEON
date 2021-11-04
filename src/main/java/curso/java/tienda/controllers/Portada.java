package curso.java.tienda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class Portada {
	
	@GetMapping("")
	public String portada() {
		return "index";
	}
	
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
	@GetMapping("/tienda")
	public String shop() {
		return "shop";
	}
	
	@GetMapping("/producto")
	public String producto() {
		return "detail";
	}
	
	
}
