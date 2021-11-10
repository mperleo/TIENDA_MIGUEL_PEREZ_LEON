package curso.java.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.tienda.models.entities.Categoria;
import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.service.CategoriaService;
import curso.java.tienda.service.ProductoService;

@Controller
@RequestMapping("")
public class Portada {
	
	@Autowired
	private ProductoService ps;
	
	@Autowired
	private CategoriaService cs;
	
	@GetMapping("")
	public String portada(Model model) {
		List<Producto> productos = ps.getListaProductos();
		model.addAttribute("productos", productos);
		return "index";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Producto> productos = ps.getListaProductos();
		model.addAttribute("productos", productos);
		return "index";
	}
	
	@GetMapping("/tienda")
	public String verCat(Model model, @RequestParam(required=false, defaultValue="todos") String id_cat) {
		List<Producto> productos = null;
		
		List<Categoria> cats = cs.getListaCategorias();
		
		if(id_cat.equals("todos")) {
			productos = ps.getListaProductos();
		}
		else {
			productos = ps.getListaProductosPorCat(id_cat);
			Categoria categoria = cs.getCategoriaXId(Integer.parseInt(id_cat));
			model.addAttribute("categoria", categoria);
		}
		
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", cats);
		model.addAttribute("nRes", productos.size());
		
		return "shop";
	}
}
