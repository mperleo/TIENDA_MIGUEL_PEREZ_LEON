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

/**
 * Clase tipo Controlador encargado de las funciones de login, 
 * cierre de sesión y creación de nuevos usuarios.
 * Últimos cambios, introducción del encriptado de contraseñas.
 * @version 1.2, 11/11/2021
 * @author Miguel Pérez León
 */
@Controller
@RequestMapping("/login")
public class Login {
	
	@Autowired
	private UsuarioService us;
	@Autowired
	private ConfiguracionService cs;
	
	private static Logger logger = LogManager.getLogger(Login.class);
	
	/** 
     * Método que muestra la página de login.
     * @param model objeto para mandar atributos a la vista
     * @return nombre del template que se va a mostrar en el navegador
     */
	@GetMapping("")
	public String login(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario",usuario);
		return "login";
	}
	
	/** 
     * Método que valida los datos introducidos en el formulario de login.
     * Según se validen o no los datos de inicio de sesión el usuario en el navegdor se
     * mostrara la página de inicio de la tienda o de la parte de administrador.
     * En caso de que el usuario tenga como contraseña el valor de la contraseña por defecto 
     * (configurada en un campo de la tabla configuraciones), se mandara a la página de modificación de 
     * contraseña al iniciar hasta que la modifique.
     * @param usuario objeto que recoge los datos del formulario de inicio de sesión
     * @param session objeto que contiene los datos de la sesión del navegador
     * @param model objeto para mandar atributos a la vista
     * @param redirectAttributes objeto para mandar atributos a la vista en una redirección
     * @return reidireccion a las paginas de inicio establecidas para cada tipo de usuario por rol, a la pagina de cambio de contraseña si el login es correcto o al login de nuevo si los datos no son correctos
     */
	@PostMapping("entrar")
	public String validar(@ModelAttribute Usuario usuario, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		// busco en la base de datos un registro usando los datos indicados en el modelo del formulario de login
		Usuario busqueda = us.comprobarLogin(usuario);
		
		// obtengo de la tabla configuracion el registro de la contraseña por defecto cuando se crea un usuario en el sitio a traves del formulario de administracion
		// en caso de que el usuario tenga esta contraseña se mandara a la página de cambar contraseña hasta que la cambie
		Configuracion contra_defecto = cs.getPorClave("contra_por_defecto");
		
		//si los datos del formulario son correctos se devuelve un objeto tipo usuario sino se manda un nulo 
		if(us.comprobarLogin(usuario) != null) {
			session.setAttribute("usuario", busqueda);
			logger.info("Usuario: "+usuario.getEmail()+" ha iniciado sesión");
			
			//si el usuario tiene la contraseña por defecto definida en la configuracion debe cambiarla
			if(usuario.getClave().equals(contra_defecto.getValor())) {
				redirectAttributes.addFlashAttribute("mensaje", "Debes cambiar tu contraseña");
				return "redirect:/miUsuario/modificarPass";
			}
			// si el usuario tiene un rol correspondiente a administradort o empleado se manda al index para los admin del sitio
			else if(busqueda.getId_rol()==1 || busqueda.getId_rol()==2) {
				logger.info("Administrador: "+usuario.getEmail()+" ha iniciado sesión");
				return "redirect:/admin";
			}
			// si el usuario es un usuario normal se manda al inicio de la tienda
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
	
	/** 
     * Método que elimina la sesión del navegador, elimiando todos los datos guardados en esta
     * @param session objeto que contiene los datos de la sesión del navegador
     * @return redireccion a la página de inicio de la aplicación
     */
	@GetMapping("cerrarSesion")
	public String cerrarSesion(HttpSession session){
		session.invalidate();
		return "redirect:/home";
	}
	
	/** 
     * Método que envia al usuario al formulario de creación de una nueva cuenta
     * @return nombre del template que se va a mostrar en el navegador
     */
	@GetMapping("registrate")
	public String crearCuenta(){
		return "signin";
	}
	
	/** 
     * Método que valida los datos introducidos en el formulario de creación de una nueva cuetna.
     * Según se validen o no los datos de inicio de sesión el usuario en el navegdor se
     * mostrara la página de inicio de la tienda o otra vez el fomulario de creación de una cuenta si los datos no son válidos
     * @param usuario objeto que recoge los datos del formulario de inicio de sesión
     * @param contraComp cadena que contiene el segundo campo de contraseña para hacer la validación al crear un usuario
     * @param condiciones cadena que contendra un vdalor "true" cuando se marca el imput checkbox en el formulario de creacion de una nueva cuenta, si no esta marcado la creacion no es válida
     * @param usuario objeto que recoge los datos del formulario de inicio de sesión
     * @param model objeto para mandar atributos a la vista
     * @return nombre del template que se va a mostrar en el navegador, redirección a la página de inicio o el formulario de login otra vez si no se validan los datos
     */
	@PostMapping("registrate/guardar")
	public String crearCuenta(@ModelAttribute Usuario user, @RequestParam String contraComp , @RequestParam String condiciones, Model model){
		// el usuario ha aceptado las condiciones del servicio
		if(condiciones.equals("true")) {
			user.setId_rol(3);
			// las contraseñas indicadas son la misma
			if(user.getClave().equals(contraComp)) {
				// no hay otros clientes con el mismo usuario en la base de datos
				if(us.getUsuarioXEmail(user.getEmail()) == null) {
					// creo el hash de la contraseña a partir de la indicada en el formulario
					user.setClave(us.hashPassword(user.getClave()));
					System.out.println(user.getClave());
					// guardo el usuario en la base de datos
					us.addUsuario(user);
					logger.info("Usuario nuevo dado de alta en la base de datos");
					return "redirect:/login";
				}
				// error el usuario ha indicado un correo que ya esta en la base de datos
				else {
					logger.error("Fallo en el proceso de login de un usuario, el correo ya esta en la base de datos");
					model.addAttribute("mensaje", "El correo indicado ya esta en la base de datos");
					return "signin";
				}
			}
			// error el usuario no ha indicado la misma contraseña en los campos de confirmación
			else {
				logger.error("Fallo en el proceso de login de un usuario, fallo al introducir la verificacion de la contraseña");
				model.addAttribute("mensaje", "No has indicado las mismas contraseñas");
				return "signin";
			}
		}
		// error el usuario no ha aceptado las condiciones, no ha marcado el checkbox
		else {
			logger.error("Fallo en el proceso de login de un usuario, usuario no ha acpetado las condiciones");
			model.addAttribute("mensaje", "Debes aceptar las condiciones para registrarte");
			return "signin";
		}
	}
}
