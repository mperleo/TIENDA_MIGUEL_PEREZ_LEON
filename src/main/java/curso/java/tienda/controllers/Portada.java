package curso.java.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.tienda.models.entities.Categoria;
import curso.java.tienda.models.entities.Descuento;
import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.service.CategoriaService;
import curso.java.tienda.service.DescuentoService;
import curso.java.tienda.service.ProductoService;
import curso.java.tienda.service.UsuarioService;
import curso.java.tienda.utils.FechasUtil;

@Controller
@RequestMapping("")
public class Portada {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private CategoriaService cs;
	@Autowired
	private UsuarioService us;
	@Autowired
	private DescuentoService ds;
	
	@Autowired
	private FechasUtil eu;
	
	@GetMapping("")
	public String portada(Model model) {
		
		// si se entra en la aplicacion y no hay datos en la base de datos se crean los datos
		if(us.getListaUsuarios().isEmpty()) {
			return "redirect:/cargarDatosInicio";
		}
		
		Descuento descuentoAct = ds.getDescuentoAct(eu.fechaFormato("yyyy-MM-dd"));
		model.addAttribute("descuentoAct", descuentoAct);
		
		List<Producto> productos = ps.getListaProductosFiltro("todos", 1);
		model.addAttribute("productos", productos);
		return "index";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		Descuento descuentoAct = ds.getDescuentoAct(eu.fechaFormato("yyyy-MM-dd"));
		model.addAttribute("descuentoAct", descuentoAct);
		List<Producto> productos = ps.getListaProductosFiltro("todos", 1);
		model.addAttribute("productos", productos);
		return "index";
	}
	
	@GetMapping("/tienda")
	public String verCat(Model model, @RequestParam(required=false, defaultValue="todos") String id_cat, @RequestParam(required=false, defaultValue="1") String orden) {
		List<Producto> productos = null;
		
		List<Categoria> cats = cs.getListaCategorias();
		
		productos = ps.getListaProductosFiltro(id_cat, Integer.parseInt(orden));
		
		if(!id_cat.equals("todos")) {
			Categoria categoria = cs.getCategoriaXId(Integer.parseInt(id_cat));
			model.addAttribute("categoria", categoria);
		}
		
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", cats);
		model.addAttribute("nRes", productos.size());
		
		return "shop";
	}
}
