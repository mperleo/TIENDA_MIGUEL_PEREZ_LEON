package curso.java.tienda.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class Login {
	
	@GetMapping("")
	public String login() {
		return "login";
	}
	
	@GetMapping("registrate")
	public String crearCuenta(){
		return "signin";
	}
}