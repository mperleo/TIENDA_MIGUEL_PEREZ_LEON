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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.tienda.models.entities.Configuracion;
import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.UsuarioService;

@Controller
@RequestMapping("/login")
public class Login {
	
	@Autowired
	private UsuarioService us;
	@Autowired
	private ConfiguracionService cs;
	
	private static Logger logger = LogManager.getLogger(Login.class);
	
	@GetMapping("")
	public String login(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario",usuario);
		return "login";
	}
	
	@PostMapping("entrar")
	public String validar(@ModelAttribute Usuario usuario, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		Usuario busqueda = us.comprobarLogin(usuario);
		Configuracion contra_defecto = cs.getPorClave("contra_por_defecto");
		if(us.comprobarLogin(usuario) != null) {
			session.setAttribute("usuario", busqueda);
			logger.info("Usuario: "+usuario.getEmail()+" ha iniciado sesión");
			
			//si el usuario tiene la contraseña por defecto definida en la configuracion debe cambiarla
			if(usuario.getClave().equals(contra_defecto.getValor())) {
				redirectAttributes.addFlashAttribute("mensaje", "Debes cambiar tu contraseña");
				return "redirect:/miUsuario/modificarPass";
			}
			else if(busqueda.getId_rol()==1 || busqueda.getId_rol()==2) {
				logger.info("Administrador: "+usuario.getEmail()+" ha iniciado sesión");
				return "redirect:/admin";
			}
			else {
				return "redirect:/home";
			}
		}
		else {
			session.setAttribute("mensaje", "Error: Los datos indicados no estan en la base de datos");
			redirectAttributes.addFlashAttribute("mensaje", "Error: Los datos indicados no estan en la base de datos");
			logger.error("Fallo de inicio de sesion "+usuario.getClave()+" "+usuario.getEmail());
			return "redirect:/login";
		}
	}
	
	@GetMapping("cerrarSesion")
	public String cerrarSesion(HttpSession session){
		session.invalidate();
		return "redirect:/home";
	}
	
	@GetMapping("registrate")
	public String crearCuenta(){
		return "signin";
	}
	
	@PostMapping("registrate/guardar")
	public String crearCuenta(@ModelAttribute Usuario user, @RequestParam String contraComp , @RequestParam String condiciones, Model model){
		if(condiciones.equals("true")) {
			user.setId_rol(3);
			if(user.getClave().equals(contraComp)) {
				if(us.getUsuarioXEmail(user.getEmail()) == null) {
					us.addUsuario(user);
					return "redirect:/login";
				}
				else {
					logger.error("Fallo en el proceso de login de un usuario, el correo ya esta en la base de datos");
					model.addAttribute("mensaje", "El correo indicado ya esta en la base de datos");
					return "signin";
				}
			}
			else {
				logger.error("Fallo en el proceso de login de un usuario, fallo al introducir la verificacion de la contraseña");
				model.addAttribute("mensaje", "No has indicado las mismas contraseñas");
				return "signin";
			}
		}
		else {
			logger.error("Fallo en el proceso de login de un usuario, usuario no ha acpetado las condiciones");
			model.addAttribute("mensaje", "Debes aceptar las condiciones para registrarte");
			return "signin";
		}
	}
}
