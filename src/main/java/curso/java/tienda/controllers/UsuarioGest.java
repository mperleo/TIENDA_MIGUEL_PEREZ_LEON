package curso.java.tienda.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.service.UsuarioService;

@Controller
@RequestMapping("/miUsuario")
public class UsuarioGest {
	@Autowired
	private UsuarioService us;
	
	private static Logger logger = LogManager.getLogger(UsuarioGest.class);
	
	@GetMapping("ver")
	public String ver( HttpSession session, Model model) {
		return "ver";
	}
	
	@PostMapping("modificar")
	public String ver(@ModelAttribute Usuario usuario, HttpSession session, Model model) {
		Usuario  user = (Usuario) session.getAttribute("usuario");
		usuario.setClave(user.getClave());
		return "ver";
	}
	
	@PostMapping("modificarContraseña")
	public String modificarContraseña(HttpSession session, Model model, @RequestParam String contraComp, @RequestParam String contraNueva, @RequestParam String contraAct) {
		Usuario user = (Usuario) session.getAttribute("usuario");
		user.setClave(contraNueva);
		return "ver";
	}
}
