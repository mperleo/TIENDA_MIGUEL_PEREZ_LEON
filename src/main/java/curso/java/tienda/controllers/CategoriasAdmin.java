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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.tienda.models.entities.Categoria;
import curso.java.tienda.service.CategoriaService;
import curso.java.tienda.utils.CategoriasUtil;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriasAdmin {
	@Autowired
	private CategoriaService cs;
	@Autowired
	private CategoriasUtil cu;
	
	private static Logger logger = LogManager.getLogger(CategoriasAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Categoria> cats = cs.getListaCategorias();
	    model.addAttribute("categorias", cats);
	    return "admin/categorias/categorias";
	}

	@GetMapping("editar/{id_categoria}")
	public String editar(Model model, @PathVariable("id_categoria") String id_cat) {
	    Integer id_categoria = Integer.parseInt(id_cat);
	    Categoria cat = cs.getCategoriaXId(id_categoria);
	    model.addAttribute("cat", cat);
	    
	    return "admin/categorias/categoriasEditar";
	}

	@PostMapping("editar/{id_categoria}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_categoria") String id_cat, @ModelAttribute Categoria cat, RedirectAttributes redirectAttributes, @RequestParam(required=false,name="archivo") MultipartFile file) {
	    Integer id_categoria = Integer.parseInt(id_cat);
	    cat.setId(id_categoria);
	    if(!file.isEmpty()) {
			cat.setImagen(cu.subirImagen(id_cat, file));
		}
	    cs.edit(cat);
	    
	    logger.info("Categoria id: "+id_cat+" eidtada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Categor??a editada correctamente");
	    return "redirect:/admin/categorias";
	}

	@GetMapping("borrar/{id_categoria}")
	public String borrar(Model model, @PathVariable("id_categoria") String id_cat, RedirectAttributes redirectAttributes) {
	    Integer id_categoria = Integer.parseInt(id_cat);
	    cs.del(id_categoria);
	    logger.info("Categoria id: "+id_cat+" eliminada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Categor??a borrada correctamente");
	    return "redirect:/admin/categorias";
	}

	@GetMapping("nuevo")
	public String nuevo(Model model) {
	    return "admin/categorias/categoriasNuevo";
	}

	@PostMapping("nuevo/guardar")
	public String nuevoGuardar(Model model, @ModelAttribute Categoria cat, RedirectAttributes redirectAttributes, @RequestParam(required=false,name="archivo") MultipartFile file) {
	    cs.add(cat);
	    if(!file.isEmpty()) {
			Integer id_ultimo = cs.getIdUltimaCat();
			cat.setImagen(cu.subirImagen(id_ultimo.toString(), file));
			cs.add(cat);
		}
	    logger.info("Nueva categoria guardada");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Categor??a creada correctamente");
	    return "redirect:/admin/categorias";
	}
}
