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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.tienda.models.entities.Proveedor;
import curso.java.tienda.service.ProveedorService;

@Controller
@RequestMapping("/admin/proveedores")
public class ProveedorAdmin {
	@Autowired
	private ProveedorService ps;
	
	private static Logger logger = LogManager.getLogger(ProveedorAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Proveedor> proveedores = ps.getListaProveedores();
	    model.addAttribute("proveedores", proveedores);
	    return "admin/proveedores";
	}

	@GetMapping("editar/{id_proveedor}")
	public String editar(Model model, @PathVariable("id_proveedor") String id_prov) {
	    Integer id_proveedor = Integer.parseInt(id_prov);
	    Proveedor cat = ps.getProveedorXId(id_proveedor);
	    model.addAttribute("cat", cat);
	    
	    return "admin/proveedorEditar";
	}

	@PostMapping("editar/{id_proveedor}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_proveedor") String id_prov, @ModelAttribute Proveedor prov, RedirectAttributes redirectAttributes) {
	    Integer id_proveedor = Integer.parseInt(id_prov);
	    prov.setId(id_proveedor);
	    ps.edit(prov);
	    logger.info("Proveedor id: "+id_prov+" editado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Proveedor editado correctamente");
	    return "redirect:/admin/proveedores";
	}

	@GetMapping("borrar/{id_proveedor}")
	public String borrar(Model model, @PathVariable("id_proveedor") String id_prov, RedirectAttributes redirectAttributes) {
	    Integer id_proveedor = Integer.parseInt(id_prov);
	    ps.del(id_proveedor);
	    logger.info("Proveedor id: "+id_prov+" eliminado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Proveedor borrado correctamente");
	    return "redirect:/admin/proveedores";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "admin/proveedorNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute Proveedor prov, RedirectAttributes redirectAttributes) {
	    ps.add(prov);
	    logger.info("Nueva proveedor guardado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Proveedor creado correctamente");
	    return "redirect:/admin/proveedores";
	}
}