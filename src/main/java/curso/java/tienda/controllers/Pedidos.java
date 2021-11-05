package curso.java.tienda.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedido")
public class Pedidos {

	private static Logger logger = LogManager.getLogger(Pedidos.class);
	
	@GetMapping("")
	public String verPedidoActual(HttpSession session, Model model) {
		
		if(session.getAttribute("usuario") != null) {
			return "checkout";
		}
		else {
			session.setAttribute("mensaje", "Inicia sesion para hacer un pedido");
			return "redirect:/login";
		}
	}
	
}
