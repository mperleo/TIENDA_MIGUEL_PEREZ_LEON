package curso.java.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.entities.Descuento;
import curso.java.tienda.models.entities.Producto;
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
	
	@GetMapping("/about")
	public String aCercaDe() {
		return "about";
	}
}
