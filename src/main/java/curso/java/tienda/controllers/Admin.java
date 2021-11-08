package curso.java.tienda.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.entities.Usuario;

@Controller
@RequestMapping("/admin")
public class Admin {
	
	private static Logger logger = LogManager.getLogger(Admin.class);
	
	@GetMapping("")
	public String portada(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null && usuario.getId_rol() == 1) {
			return "admin/index";
		}
		else {
			model.addAttribute("mensaje", "no tienes permiso para acceder a esta página");
			logger.error("Intento de acceso sin permisos en pagina admin");
			return "error";
		}
	}
	
	@GetMapping("categorias")
	public String categorias(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null && usuario.getId_rol() == 1) {
			return "admin/categorias";
		}
		
		else {
			model.addAttribute("mensaje", "no tienes permiso para acceder a esta página");
			logger.error("Intento de acceso sin permisos en pagina admin/categorias");
			return "error";
		}
	}
	
	@GetMapping("roles")
	public String roles(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null && usuario.getId_rol() == 1) {
			return "admin/roles";
		}
		
		else {
			model.addAttribute("mensaje", "no tienes permiso para acceder a esta página");
			logger.error("Intento de acceso sin permisos en pagina admin/roles");
			return "error";
		}
	}
	
	@GetMapping("opcionesMenu")
	public String opcionesMenu(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null && usuario.getId_rol() == 1) {
			return "admin/opcionesMenu";
		}
		
		else {
			model.addAttribute("mensaje", "no tienes permiso para acceder a esta página");
			logger.error("Intento de acceso sin permisos en pagina admin/opcionesMenu");
			return "error";
		}
	}
	
	@GetMapping("configuraciones")
	public String configuraciones(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null && usuario.getId_rol() == 1) {
			return "admin/configuraciones";
		}
		
		else {
			model.addAttribute("mensaje", "no tienes permiso para acceder a esta página");
			logger.error("Intento de acceso sin permisos en pagina admin/configuraciones");
			return "error";
		}
	}
}
