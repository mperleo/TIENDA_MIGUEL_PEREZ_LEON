package curso.java.tienda.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.service.ProductoService;

@Controller
@RequestMapping("/admin/productos")
public class ProductosAdmin {
	
	@Autowired
	private ProductoService ps;
	
	@GetMapping("")
	public String verTodos(Model model) {
		List<Producto> prods = ps.getListaProductos();
		model.addAttribute("productos", prods);
		return "admin/productos";
	}
	
	@GetMapping("verCliente/{id_prod}")
	public String verCliente(Model model, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		Producto prod = ps.getProductoXId(id_producto);
		model.addAttribute("producto", prod);
		return "detail";
	}
	
	@GetMapping("editar/{id_prod}")
	public String editar(Model model, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		Producto prod = ps.getProductoXId(id_producto);
		model.addAttribute("prod", prod);
		
		return "admin/productoEditar";
	}
	
	@PostMapping("editar/{id_prod}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_prod") String id_prod, @ModelAttribute Producto prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		prod.setId(id_producto);
		ps.editProducto(prod);
		return "redirect:/admin/productos";
	}
	
	@GetMapping("borrar/{id_prod}")
	public String borrar(Model model, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		ps.delProducto(id_producto);
		return "redirect:/admin/productos";
	}
	
	@GetMapping("nuevo")
	public String pnuevo(Model model) {
		return "admin/productoNuevo";
	}
	
	@PostMapping("nuevo/guardar")
	public String pnuevoGuardar(Model model, @ModelAttribute Producto prod) {
		ps.addProducto(prod);
		return "redirect:/admin/productos";
	}
}
