package curso.java.tienda.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.tienda.models.entities.Rol;
import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.RolService;
import curso.java.tienda.service.UsuarioService;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuariosAdmin {
	
	@Autowired
	private UsuarioService us;
	@Autowired
	private RolService rs;
	@Autowired
	private ConfiguracionService cs;
	
	private static Logger logger = LogManager.getLogger(UsuariosAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Usuario> users = us.getListaUsuarios();
	    List<Rol> roles = rs.getListaRoles();
	    
	    model.addAttribute("roles", roles);
	    model.addAttribute("usuarios", users);
	    return "admin/usuarios";
	}
	
	@PostMapping("")
	public String verRol(Model model, @RequestParam String id_rol) {
		List<Usuario> usuarios = null;
		List<Rol> roles = rs.getListaRoles();
		
		if(id_rol.equals("todos")) {
			usuarios = us.getListaUsuarios();
		}
		else {
			usuarios = us.getListaUsuarioPorRol(id_rol);
		}
		
		model.addAttribute("roles", roles);
		model.addAttribute("mensajeOk", "Mostrando los usuarios con el rol' "+id_rol+"'");
		model.addAttribute("usuarios", usuarios);
		
		return "admin/usuarios";
	}

	@GetMapping("editar/{id_usuario}")
	public String editar(Model model, @PathVariable("id_usuario") String id_usuario) {
	    Integer id_producto = Integer.parseInt(id_usuario);
	    Usuario user = us.getUsuarioXId(id_producto);
	    
	    List<Rol> roles = rs.getListaRoles();
	    
	    model.addAttribute("roles", roles);
	    model.addAttribute("usuario", user);
	    
	    return "admin/usuarioEditar";
	}

	@PostMapping("editar/{id_user}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_user") String id_user, @ModelAttribute Usuario user) {
	    Integer id_usuario = Integer.parseInt(id_user);
	    Usuario datosAnt = us.getUsuarioXId(id_usuario);
	    
	    // pongo los datos que no estan en el formulario
	    user.setId(id_usuario);
	    user.setClave(datosAnt.getClave());
	    
	    us.editUsuario(user);
	    return "redirect:/admin/usuarios";
	}

	@GetMapping("borrar/{id_usuario}")
	public String borrar(Model model, @PathVariable("id_usuario") String id_usuario, HttpSession session) {
		Usuario admin = (Usuario) session.getAttribute("usuario");
		if(admin.getId_rol()==1 && admin != null) {
			Integer id_us = Integer.parseInt(id_usuario);
		    us.delUsuario(id_us);
		    return "redirect:/admin/usuarios";
		}
		else {
			logger.error("Intento de borrado de un usuario sin permisos");
			session.setAttribute("mensaje", "no tienes permiso para realizar esa accion");
			return "error";
		}
		
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
		List<Rol> roles = rs.getListaRoles();
		model.addAttribute("roles", roles);
	    return "admin/usuarioNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute Usuario user) {
		//obtengo la contraseña por defecto el altas de la tabla de configuracion y meto el hash para guardarlo en la base de datos
		String passDefecto = cs.getPorClave("contra_por_defecto").getValor();
		user.setClave(us.hashPassword(passDefecto));
		
		// guardo el usuario en la base de datos
	    us.addUsuario(user);
	    return "redirect:/admin/usuarios";
	}
	
}
