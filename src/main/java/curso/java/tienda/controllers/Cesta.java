package curso.java.tienda.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cesta")
public class Cesta {
	
	@GetMapping("")
	public String portada(Model model, HttpSession session) {
		return "cart";
	}
	
	@GetMapping("add/{id_prod}")
	public String addProd(Model model, HttpSession session) {
		return "cart";
	}
}
