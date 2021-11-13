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

import curso.java.tienda.models.entities.Configuracion;
import curso.java.tienda.service.ConfiguracionService;

@Controller
@RequestMapping("/admin/configuraciones")
public class ConfiguracionAdmin {
	
	@Autowired
	private ConfiguracionService cs;
	
	private static Logger logger = LogManager.getLogger(ConfiguracionAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Configuracion> configs = cs.getListaConfiguraciones();
	    model.addAttribute("configuraciones", configs);
	    return "admin/configuraciones";
	}

	@GetMapping("editar/{id_configuracion}")
	public String editar(Model model, @PathVariable("id_configuracion") String id_config) {
	    Integer id_configuracion = Integer.parseInt(id_config);
	    Configuracion config = cs.getConfiguracionXId(id_configuracion);
	    model.addAttribute("conf", config);
	    
	    return "admin/configuracionEditar";
	}

	@PostMapping("editar/{id_configuracion}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_configuracion") String id_config, @ModelAttribute Configuracion config, RedirectAttributes redirectAttributes) {
	    Integer id_configuracion = Integer.parseInt(id_config);
	    config.setId(id_configuracion);
	    cs.editConfiguracion(config);
	    logger.info("Configuracion editada con exito");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Configuración editada correctamente");
	    return "redirect:/admin/configuraciones";
	}

	@GetMapping("borrar/{id_configuracion}")
	public String borrar(Model model, @PathVariable("id_configuracion") String id_config, RedirectAttributes redirectAttributes) {
	    Integer id_configuracion = Integer.parseInt(id_config);
	    cs.delConfiguracion(id_configuracion);
	    logger.info("Configuracion borrada con exito");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Configuración borrada correctamente");
	    return "redirect:/admin/configuraciones";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "admin/configuracionNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute Configuracion config, RedirectAttributes redirectAttributes) {
	    cs.addConfiguracion(config);
	    logger.info("Configuracion guardada con exito");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Configuración creada correctamente");
	    return "redirect:/admin/configuraciones";
	}
	
}
