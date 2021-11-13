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

import curso.java.tienda.models.entities.Descuento;
import curso.java.tienda.service.DescuentoService;

@Controller
@RequestMapping("/admin/descuentos")
public class DescuentosAdmin {
	@Autowired
	private DescuentoService ds;
	
	private static Logger logger = LogManager.getLogger(DescuentosAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Descuento> descuentos = ds.getListaDescuentos();
	    model.addAttribute("descuentos", descuentos);
	    return "admin/descuentos/descuentos";
	}

	@GetMapping("editar/{id_descuento}")
	public String editar(Model model, @PathVariable("id_descuento") String id_desc) {
	    Integer id_descuento = Integer.parseInt(id_desc);
	    Descuento descuento = ds.getDescuentoXId(id_descuento);
	    model.addAttribute("descuento", descuento);
	    
	    return "admin/descuentos/descuentosEditar";
	}

	@PostMapping("editar/{id_descuento}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_descuento") String id_desc, @ModelAttribute Descuento descuento, RedirectAttributes redirectAttributes) {
	    Integer id_descuento = Integer.parseInt(id_desc);
	    descuento.setId(id_descuento);
	    ds.edit(descuento);
	    logger.info("Descuento id: "+id_desc+" eidtado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Descuento editado correctamente");
	    return "redirect:/admin/descuentos";
	}

	@GetMapping("borrar/{id_descuento}")
	public String borrar(Model model, @PathVariable("id_descuento") String id_desc, RedirectAttributes redirectAttributes) {
	    Integer id_descuento = Integer.parseInt(id_desc);
	    ds.del(id_descuento);
	    logger.info("Descuento id: "+id_desc+" eliminado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Descuento borrado correctamente");
	    return "redirect:/admin/descuentos";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "admin/descuentos/descuentosNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute Descuento descuento, RedirectAttributes redirectAttributes) {
	    ds.add(descuento);
	    logger.info("Nueva descuento guardado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Descuento creado correctamente");
	    return "redirect:/admin/descuentos";
	}
}