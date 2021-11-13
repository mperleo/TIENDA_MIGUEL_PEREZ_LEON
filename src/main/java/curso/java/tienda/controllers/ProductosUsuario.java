package curso.java.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.entities.Categoria;
import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.service.CategoriaService;
import curso.java.tienda.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductosUsuario {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private CategoriaService cs;
	
	@GetMapping("ver/{id_prod}")
	public String producto(Model model, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		Producto prod = ps.getProductoXId(id_producto);
		
		Categoria cat = cs.getCategoriaXId(prod.getId_categoria());
		List<Producto> prodsReco = ps.getLista4ProductosPorCat(prod.getId_categoria());
		model.addAttribute("categoria", cat);
		model.addAttribute("producto", prod);
		model.addAttribute("prodsReco", prodsReco);
		return "detail";
	}
}
