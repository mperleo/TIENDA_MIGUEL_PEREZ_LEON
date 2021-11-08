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
	
	@GetMapping("modificar/")
	public String modificarUsuario(HttpSession session) {
		return "modificar";
	}
	
	@PostMapping("modificar/guardar")
	public String modificarUsuarioGuardar(@ModelAttribute Usuario usuario, HttpSession session, Model model) {
		Usuario  user = (Usuario) session.getAttribute("usuario");
		// meto los datos que del usuario que no estan en el formulario
		usuario.setId(user.getId());
		usuario.setClave(user.getClave());
		
		// compruebo si el correo indicado es unico en la bbdd o si no lo ha cambiado al guardar
		Usuario compEmail = us.getUsuarioXEmail(usuario.getEmail());
		if(compEmail == null || compEmail.getId() == usuario.getId()) {
			us.editUsuario(usuario);
			model.addAttribute("mensajeOk", "Datos de usuario modficados correctamente");
			return "ver";
		}
		else {
			model.addAttribute("mensaje", "Has indicado un correo no valido");
			return "modificar";
		}
	}
	
	@GetMapping("modificarPass")
	public String modificarContraseña(HttpSession session) {
		return "modificarPass";
	}
	
	@PostMapping("modificarPass/guardar")
	public String modificarContraseñaGuardar(HttpSession session, Model model, @RequestParam String contraComp, @RequestParam String contraNueva, @RequestParam String contraAct) {
		Usuario user = (Usuario) session.getAttribute("usuario");
		// meto los datos que del usuario que no estan en el formulario
		if(user.getClave().equals(contraAct)) {
			if(contraComp.equals(contraNueva)) {
				user.setClave(contraNueva);
				us.editUsuario(user);
				
				model.addAttribute("mensajeOk", "Contraseña modficada correctamente");
				return "ver";
			}
			else {
				model.addAttribute("mensaje", "Las contraseñas indicadas no son iguales");
				return "modificarPass";
			}
		}
		else {
			model.addAttribute("mensaje", "Tienes que indicar tu contraseña actual");
			return "modificarPass";
		}

	}
}
