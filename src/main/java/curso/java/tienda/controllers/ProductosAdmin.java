package curso.java.tienda.controllers;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.tienda.models.entities.Categoria;
import curso.java.tienda.models.entities.Impuesto;
import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.service.CategoriaService;
import curso.java.tienda.service.ImpuestoService;
import curso.java.tienda.service.ProductoService;

@Controller
@RequestMapping("/admin/productos")
public class ProductosAdmin {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private CategoriaService cs;
	@Autowired
	private ImpuestoService is;
	
	private static Logger logger = LogManager.getLogger(ProductosAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
		
		List<Producto> prods = ps.getListaProductos();
		List<Categoria> cats = cs.getListaCategorias();
		
		model.addAttribute("productos", prods);
		model.addAttribute("categorias", cats);
		return "admin/productos";
	}
	
	@PostMapping("")
	public String verCat(Model model, @RequestParam String id_cat) {
		List<Producto> productos = null;
		
		List<Categoria> cats = cs.getListaCategorias();
		
		if(id_cat.equals("todos")) {
			productos = ps.getListaProductos();
		}
		else {
			productos = ps.getListaProductosPorCat(id_cat);
		}
		
		model.addAttribute("mensajeOk", "Mostrando los productos con la categoria' "+id_cat+"'");
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", cats);
		
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
		
		List<Categoria> cats = cs.getListaCategorias();
		model.addAttribute("categorias", cats);
		
		List<Impuesto> imps = is.getListaImpuestos();
		model.addAttribute("impuestos", imps);
		
		logger.info("Producto id_prod: "+id_prod+" editado");
		return "admin/productoEditar";
	}
	
	@PostMapping("editar/{id_prod}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_prod") String id_prod, @ModelAttribute Producto prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		
		// calculo del precio con iva y lo guardo en el objeto
		Float precioIVA = (float) (prod.getPrecio() * ( 1 + (prod.getImpuesto()/100)));
		prod.setPrecioImpuesto(DoubleRounder.round(precioIVA, 3));
		prod.setId(id_producto);
		ps.editProducto(prod);
		return "redirect:/admin/productos";
	}
	
	@GetMapping("borrar/{id_prod}")
	public String borrar(Model model, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		ps.delProducto(id_producto);
		logger.info("Producto id_prod: "+id_prod+" borrado");
		return "redirect:/admin/productos";
	}
	
	@GetMapping("nuevo")
	public String pnuevo(Model model) {
		
		List<Categoria> cats = cs.getListaCategorias();
		model.addAttribute("categorias", cats);
		
		List<Impuesto> imps = is.getListaImpuestos();
		model.addAttribute("impuestos", imps);
		
		return "admin/productoNuevo";
	}
	
	@PostMapping("nuevo/guardar")
	public String pnuevoGuardar(Model model, @ModelAttribute Producto prod) {
		// calculo del precio con iva y lo guardo en el objeto
		Float precioIVA = (float) (prod.getPrecio() * ( 1 + (prod.getImpuesto()/100)));
		prod.setPrecioImpuesto(DoubleRounder.round(precioIVA, 3));
				
		ps.addProducto(prod);
		logger.info("Nuevo producto dado de alta");
		return "redirect:/admin/productos";
	}
}
