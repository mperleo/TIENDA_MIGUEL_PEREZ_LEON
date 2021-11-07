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

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.service.UsuarioService;

@Controller
@RequestMapping("/login")
public class Login {
	
	@Autowired
	private UsuarioService us;
	
	private static Logger logger = LogManager.getLogger(Login.class);
	
	@GetMapping("")
	public String login(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario",usuario);
		return "login";
	}
	
	@PostMapping("entrar")
	public String validar(@ModelAttribute Usuario usuario, HttpSession session, Model model) {
		Usuario busqueda = us.comprobarLogin(usuario);
		if(us.comprobarLogin(usuario) !=null) {
			session.setAttribute("usuario", busqueda);
			logger.info("Usuario: "+usuario.getEmail()+" ha iniciado sesión");
			if(busqueda.getId_rol()==1) {
				logger.info("Administrador: "+usuario.getEmail()+" ha iniciado sesión");
				return "redirect:/admin";
			}
			else {
				return "redirect:/home";
			}
		}
		else {
			session.setAttribute("mensaje", "Error: Los datos indicados no estan en la base de datos");
			logger.error("Fallo de inicio de sesion "+usuario.getClave()+" "+usuario.getEmail());
			return "login";
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
