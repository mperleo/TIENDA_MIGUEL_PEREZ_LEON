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
		return "usuario/ver";
	}
	
	@GetMapping("modificar/")
	public String modificarUsuario(HttpSession session) {
		return "usuario/modificar";
	}
	
	@PostMapping("modificar/guardar")
	public String modificarUsuarioGuardar(@ModelAttribute Usuario usuario, HttpSession session, Model model) {
		Usuario  user = (Usuario) session.getAttribute("usuario");
		// meto los datos que del usuario que no estan en el formulario
		usuario.setId(user.getId());
		usuario.setClave(user.getClave());
		usuario.setId_rol(user.getId_rol());
		
		// compruebo si el correo indicado es unico en la bbdd o si no lo ha cambiado al guardar
		Usuario compEmail = us.getUsuarioXEmail(usuario.getEmail());
		if(compEmail == null || compEmail.getId() == usuario.getId()) {
			us.editUsuario(usuario);
			model.addAttribute("mensajeOk", "Datos de usuario modficados correctamente");
			logger.info("Usuario "+user.getId()+" ha modificado su perfil");
			
			// obtengo los datos del usuario nuevos y los cambio para el objeto guardado en sesion
			Usuario usuarioSesionModif = us.getUsuarioXId(user.getId());
			session.setAttribute("usuario", usuarioSesionModif);
			
			return "usuario/ver";
		}
		else {
			model.addAttribute("mensaje", "Has indicado un correo no valido");
			return "usuario/modificar";
		}
	}
	
	@GetMapping("modificarPass")
	public String modificarContra(HttpSession session) {
		return "usuario/modificarPass";
	}
	
	/** 
     * Modelo que recoge los datos del formulario de cambio de contraseña
     * @param session objeto que contiene los datos de la sesión del usuario en el navegador
     * @param model objeto para mandar parámetros en las vistas
     * @param contraComp contraseña para hacer la validación de contraseña con dos campos
     * @param contraNueva nueva contraseña para el usuario
     * @param contraAct contraseña actual en la base de datos del usuario activo
     * @return redireccion a la página de veista de perfil o al formulario de contraseña si no se ha modificado con éxito
     */
	@PostMapping("modificarPass/guardar")
	public String modificarContrGuardar(HttpSession session, Model model, @RequestParam String contraComp, @RequestParam String contraNueva, @RequestParam String contraAct) {
		Usuario user = (Usuario) session.getAttribute("usuario");
		// compruebo que el dato de contraseña actual que se ha indicado en el formulario es el que tiene el usuario en la base de datos
		if(user.getClave().equals(us.hashPassword(contraAct))) {
			if(contraComp.equals(contraNueva)) {
				user.setClave(us.hashPassword(contraNueva)); // hago el hash de la contraseña nueva y la pongo al usuario
				us.editUsuario(user);
				
				model.addAttribute("mensajeOk", "Contraseña modficada correctamente");
				logger.info("Usuario "+user.getId()+" ha modificado su contraseña");
				return "usuario/ver";
			}
			else {
				model.addAttribute("mensaje", "Las contraseñas indicadas no son iguales");
				return "usuario/modificarPass";
			}
		}
		else {
			model.addAttribute("mensaje", "Tienes que indicar tu contraseña actual");
			return "usuario/modificarPass";
		}

	}
}
