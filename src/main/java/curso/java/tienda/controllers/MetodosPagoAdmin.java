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

import curso.java.tienda.models.entities.MetodoPago;
import curso.java.tienda.service.MetodoPagoService;

@Controller
@RequestMapping("/admin/metodosPago")
public class MetodosPagoAdmin {
	@Autowired
	private MetodoPagoService ms;
	
	private static Logger logger = LogManager.getLogger(MetodosPagoAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<MetodoPago> metodos = ms.getListaMetodosPago();
	    model.addAttribute("metodos", metodos);
	    return "admin/metodosPago/metodosPago";
	}

	@GetMapping("editar/{id_metodo}")
	public String editar(Model model, @PathVariable("id_metodo") String id_metod) {
	    Integer id_metodo = Integer.parseInt(id_metod);
	    MetodoPago metodo = ms.getMetodoPagoXId(id_metodo);
	    model.addAttribute("metodo", metodo);
	    
	    return "admin/metodosPago/metodoPagoEditar";
	}

	@PostMapping("editar/{id_metodo}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_metodo") String id_metod, @ModelAttribute MetodoPago metodo, RedirectAttributes redirectAttributes) {
	    Integer id_metodo = Integer.parseInt(id_metod);
	    metodo.setId(id_metodo);
	    ms.edit(metodo);
	    logger.info("MetodoPago id: "+id_metod+" eidtada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "MetodoPago editado correctamente");
	    return "redirect:/admin/metodosPago";
	}

	@GetMapping("borrar/{id_metodo}")
	public String borrar(Model model, @PathVariable("id_metodo") String id_metod, RedirectAttributes redirectAttributes) {
	    Integer id_metodo = Integer.parseInt(id_metod);
	    ms.del(id_metodo);
	    logger.info("MetodoPago id: "+id_metod+" eliminado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "MetodoPago borrado correctamente");
	    return "redirect:/admin/metodosPago";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "admin/metodosPago/metodoPagoNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute MetodoPago metodo, RedirectAttributes redirectAttributes) {
	    ms.add(metodo);
	    logger.info("Nueva metodo pago guardada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "MetodoPago creado correctamente");
	    return "redirect:/admin/metodosPago";
	}
}