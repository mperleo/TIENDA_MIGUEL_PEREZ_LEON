package curso.java.tienda.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.DAO.Usuarios;
import curso.java.tienda.models.entities.Usuario;

@Controller
@RequestMapping("/login")
public class Login {
	
	private static Logger logger = LogManager.getLogger(Login.class);
	
	@GetMapping("")
	public String login(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario",usuario);
		return "login";
	}
	
	@PostMapping("entrar")
	public String validar(@ModelAttribute Usuario usuario, HttpSession session, Model model) {
		Usuario busqueda = Usuarios.seleccionarUno(usuario);
		if(busqueda != null) {
			session.setAttribute("usuario", busqueda);
			logger.info("Usuario: "+usuario.getEmail()+" ha iniciado sesión");
			return "redirect:/home";
		}
		else {
			model.addAttribute("mensaje", "<strong>Error:</strong> datos erroneos");
			logger.error("Fallo de inicio de sesion "+usuario.getClave()+" "+usuario.getEmail());
			return "redirect:/login";
		}
	}
	
	@GetMapping("cerrarSesion")
	public String cerrarSesion(HttpSession session){
		return "home";
	}
	
	@GetMapping("registrate")
	public String crearCuenta(){
		return "signin";
	}
}
