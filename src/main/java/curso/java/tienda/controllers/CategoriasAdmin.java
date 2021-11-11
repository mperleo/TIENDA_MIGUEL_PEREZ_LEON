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

import curso.java.tienda.models.entities.Categoria;
import curso.java.tienda.service.CategoriaService;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriasAdmin {
	@Autowired
	private CategoriaService cs;
	
	private static Logger logger = LogManager.getLogger(CategoriasAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Categoria> cats = cs.getListaCategorias();
	    model.addAttribute("categorias", cats);
	    return "admin/categorias";
	}

	@GetMapping("editar/{id_categoria}")
	public String editar(Model model, @PathVariable("id_categoria") String id_cat) {
	    Integer id_categoria = Integer.parseInt(id_cat);
	    Categoria cat = cs.getCategoriaXId(id_categoria);
	    model.addAttribute("cat", cat);
	    
	    return "admin/categoriasEditar";
	}

	@PostMapping("editar/{id_categoria}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_categoria") String id_cat, @ModelAttribute Categoria cat, RedirectAttributes redirectAttributes) {
	    Integer id_categoria = Integer.parseInt(id_cat);
	    cat.setId(id_categoria);
	    cs.edit(cat);
	    logger.info("Categoria id: "+id_cat+" eidtada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Categoría editada correctamente");
	    return "redirect:/admin/categorias";
	}

	@GetMapping("borrar/{id_categoria}")
	public String borrar(Model model, @PathVariable("id_categoria") String id_cat, RedirectAttributes redirectAttributes) {
	    Integer id_categoria = Integer.parseInt(id_cat);
	    cs.del(id_categoria);
	    logger.info("Categoria id: "+id_cat+" eliminada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Categoría borrada correctamente");
	    return "redirect:/admin/categorias";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "admin/categoriasNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute Categoria cat, RedirectAttributes redirectAttributes) {
	    cs.add(cat);
	    logger.info("Nueva categoria guardada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Categoría creada correctamente");
	    return "redirect:/admin/categorias";
	}
}
