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

import curso.java.tienda.models.entities.Impuesto;
import curso.java.tienda.service.ImpuestoService;

@Controller
@RequestMapping("/admin/impuestos")
public class ImpuestosAdmin {
	@Autowired
	private ImpuestoService is;
	
	private static Logger logger = LogManager.getLogger(ImpuestosAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Impuesto> impuestos = is.getListaImpuestos();
	    model.addAttribute("impuestos", impuestos);
	    return "admin/impuestos/impuestos";
	}

	@GetMapping("editar/{id_impuesto}")
	public String editar(Model model, @PathVariable("id_impuesto") String id_imp) {
	    Integer id_impuesto = Integer.parseInt(id_imp);
	    Impuesto impuesto = is.getImpuestoXId(id_impuesto);
	    model.addAttribute("impuesto", impuesto);
	    
	    return "admin/impuestos/impuestoEditar";
	}

	@PostMapping("editar/{id_impuesto}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_impuesto") String id_imp, @ModelAttribute Impuesto impuesto, RedirectAttributes redirectAttributes) {
	    Integer id_impuesto = Integer.parseInt(id_imp);
	    impuesto.setId(id_impuesto);
	    is.edit(impuesto);
	    logger.info("Impuesto id: "+id_imp+" eidtada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Impuesto editado correctamente");
	    return "redirect:/admin/impuestos";
	}

	@GetMapping("borrar/{id_impuesto}")
	public String borrar(Model model, @PathVariable("id_impuesto") String id_imp, RedirectAttributes redirectAttributes) {
	    Integer id_impuesto = Integer.parseInt(id_imp);
	    is.del(id_impuesto);
	    logger.info("Impuesto id: "+id_imp+" eliminado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Impuesto borrado correctamente");
	    return "redirect:/admin/impuestos";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "admin/impuestos/impuestoNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute Impuesto impuesto, RedirectAttributes redirectAttributes) {
		is.add(impuesto);
	    logger.info("Nueva impuesto pago guardada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Impuesto creado correctamente");
	    return "redirect:/admin/impuestos";
	}
}