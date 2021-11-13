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

import curso.java.tienda.models.entities.Valoracion;
import curso.java.tienda.service.ValoracionService;

@Controller
@RequestMapping("valoraciones")
public class ValoracionesUsuario {
	@Autowired
	private ValoracionService vs;
	
	private static Logger logger = LogManager.getLogger(ValoracionesUsuario.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Valoracion> valoraciones = vs.getListaValoracions();
	    model.addAttribute("valoraciones", valoraciones);
	    return "valoraciones/valoraciones";
	}

	@GetMapping("editar/{id_valoracion}")
	public String editar(Model model, @PathVariable("id_valoracion") String id_valor) {
	    Integer id_valoracion = Integer.parseInt(id_valor);
	    Valoracion valoracion = vs.getValoracionXId(id_valoracion);
	    model.addAttribute("valoracion", valoracion);
	    
	    return "valoraciones/valoracionesEditar";
	}

	@PostMapping("editar/{id_valoracion}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_valoracion") String id_valor, @ModelAttribute Valoracion valoracion, RedirectAttributes redirectAttributes) {
	    Integer id_valoracion = Integer.parseInt(id_valor);
	    valoracion.setId(id_valoracion);
	    vs.edit(valoracion);
	    logger.info("Valoracion id: "+id_valor+" eidtado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Valoracion editado correctamente");
	    return "redirect:valoraciones";
	}

	@GetMapping("borrar/{id_valoracion}")
	public String borrar(Model model, @PathVariable("id_valoracion") String id_valor, RedirectAttributes redirectAttributes) {
	    Integer id_valoracion = Integer.parseInt(id_valor);
	    vs.del(id_valoracion);
	    logger.info("Valoracion id: "+id_valor+" eliminado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Valoracion borrado correctamente");
	    return "redirect:valoraciones";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "valoraciones/valoracionesNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute Valoracion valoracion, RedirectAttributes redirectAttributes) {
	    vs.add(valoracion);
	    logger.info("Nueva valoracion guardado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Valoracion creado correctamente");
	    return "redirect:valoraciones";
	}
}