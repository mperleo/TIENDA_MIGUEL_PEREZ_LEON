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

import curso.java.tienda.service.OpcionMenuService;
import curso.java.tienda.models.entities.OpcionMenu;

@Controller
@RequestMapping("/admin/opcionesMenu")
public class OpcionesMenuAdmin {
	
	@Autowired
	private OpcionMenuService oms;
	
	private static Logger logger = LogManager.getLogger(OpcionesMenuAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<OpcionMenu> configs = oms.getOpcionesMenu();
	    model.addAttribute("opciones", configs);
	    return "admin/opcionesMenu";
	}

	@GetMapping("editar/{id_op}")
	public String editar(Model model, @PathVariable("id_op") String id_op) {
	    Integer id_opcion = Integer.parseInt(id_op);
	    OpcionMenu opmen = oms.getOpcionMenuXId(id_opcion);
	    model.addAttribute("opcion", opmen);
	    
	    return "admin/opcionesMenuEditar";
	}

	@PostMapping("editar/{id_op}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_op") String id_op, @ModelAttribute OpcionMenu opmen) {
	    Integer id_opcion = Integer.parseInt(id_op);
	    opmen.setId(id_opcion);
	    oms.editOpcionMenu(opmen);
	    logger.info("opcion menu id: "+id_op+" editada");
	    return "redirect:/admin/opcionesMenu";
	}

	@GetMapping("borrar/{id_op}")
	public String borrar(Model model, @PathVariable("id_op") String id_op) {
	    Integer id_opcion = Integer.parseInt(id_op);
	    oms.delOpcionMenu(id_opcion);
	    logger.info("opcion menu id: "+id_op+" borrada");
	    return "redirect:/admin/opcionesMenu";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "admin/opcionesMenuNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute OpcionMenu opmen) {
	    oms.addOpcionMenu(opmen);
	    logger.info("Nueva opción de menu");
	    return "redirect:/admin/opcionesMenu";
	}
}
