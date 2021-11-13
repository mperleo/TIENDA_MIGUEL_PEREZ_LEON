package curso.java.tienda.controllers;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.tienda.models.entities.Rol;
import curso.java.tienda.service.RolService;

@Controller
@RequestMapping("/admin/roles")
public class RolesAdmin {
	@Autowired
	private RolService rs;
	
	private static Logger logger = LogManager.getLogger(RolesAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Rol> roles = rs.getListaRoles();
	    model.addAttribute("roles", roles);
	    return "admin/roles/roles";
	}

	@GetMapping("editar/{id_rol}")
	public String editar(Model model, @PathVariable("id_rol") String id_r) {
	    Integer id_rol = Integer.parseInt(id_r);
	    Rol rol = rs.getRolXId(id_rol);
	    model.addAttribute("rol", rol);
	    
	    return "admin/roles/rolEditar";
	}

	@PostMapping("editar/{id_rol}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_rol") String id_r, @RequestParam String rol, RedirectAttributes redirectAttributes) {
	    Integer id_rol = Integer.parseInt(id_r);
	    Rol rolEditar = rs.getRolXId(id_rol);
	    rolEditar.setRol(rol);
	    rs.edit(rolEditar);
	    logger.info("Rol id: "+id_r+" eidtado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Rol editado correctamente");
	    return "redirect:/admin/roles";
	}

	@GetMapping("borrar/{id_rol}")
	public String borrar(Model model, @PathVariable("id_rol") String id_r, RedirectAttributes redirectAttributes) {
	    Integer id_rol = Integer.parseInt(id_r);
	    rs.del(id_rol);
	    logger.info("Rol id: "+id_r+" eliminado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Rol borrado correctamente");
	    return "redirect:/admin/roles";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "admin/roles/rolNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @RequestParam String rol, RedirectAttributes redirectAttributes) {
		rs.add(new Rol(rol));
	    logger.info("Nuevo rol guardado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Rol creado correctamente");
	    return "redirect:/admin/roles";
	}
}