package curso.java.tienda.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.tienda.models.entities.Categoria;
import curso.java.tienda.models.entities.Impuesto;
import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.models.entities.Proveedor;
import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.service.CategoriaService;
import curso.java.tienda.service.ImpuestoService;
import curso.java.tienda.service.ProductoService;
import curso.java.tienda.service.ProveedorService;
import curso.java.tienda.utils.ProductosUtil;

@Controller
@RequestMapping("/admin/productos")
public class ProductosAdmin {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private ProductosUtil pu;
	@Autowired
	private CategoriaService cs;
	@Autowired
	private ImpuestoService is;
	@Autowired
	private ProveedorService prs;
	
	private static Logger logger = LogManager.getLogger(ProductosAdmin.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
		
		List<Producto> prods = ps.getListaProductos();
		List<Categoria> cats = cs.getListaCategorias();
		
		List<Proveedor> provs = prs.getListaProveedores();
		model.addAttribute("proveedores", provs);
		
		model.addAttribute("productos", prods);
		model.addAttribute("categorias", cats);
		return "admin/productos/productos";
	}
	
	@PostMapping("")
	public String verFiltro(Model model, @RequestParam(required=false, defaultValue="todos") String id_cat,@RequestParam(required=false, defaultValue="todos") String id_prov) {
		List<Producto> productos = null;
		
		List<Categoria> cats = cs.getListaCategorias();
		
		if(!id_cat.equals("todos")) {
			productos = ps.getListaProductosPorCat(id_cat);
		}
		if(!id_prov.equals("todos")) {
			productos = ps.getListaProductosPorProv(id_prov);
		}
		else if(id_cat.equals("todos") || id_prov.equals("todos")) {
			productos = ps.getListaProductos();
		}
		
		
		List<Proveedor> provs = prs.getListaProveedores();
		model.addAttribute("proveedores", provs);
		
		model.addAttribute("mensajeOk", "Mostrando los productos con la categoria' "+id_cat+"'");
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", cats);
		
		return "admin/productos/productos";
	}
	
	@GetMapping("verCliente/{id_prod}")
	public String verCliente(Model model, @PathVariable("id_prod") String id_prod) {
		Integer id_producto = Integer.parseInt(id_prod);
		Producto prod = ps.getProductoXId(id_producto);

		List<Producto> prodsReco = ps.getLista4ProductosPorCat(prod.getCategoria().getId());
		model.addAttribute("producto", prod);
		model.addAttribute("prodsReco", prodsReco);
		return "producto/detail";
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
		
		List<Proveedor> provs = prs.getListaProveedores();
		model.addAttribute("proveedores", provs);
		
		logger.info("Producto id_prod: "+id_prod+" editado");
		return "admin/productos/productoEditar";
	}
	
	@PostMapping("editar/{id_prod}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_prod") String id_prod, @ModelAttribute Producto prod, RedirectAttributes redirectAttributes) {
		Integer id_producto = Integer.parseInt(id_prod);
		
		// calculo del precio con iva y lo guardo en el objeto
		Float precioIVA = (float) (prod.getPrecio() * ( 1 + (prod.getImpuesto()/100)));
		prod.setPrecioImpuesto(DoubleRounder.round(precioIVA, 3));
		prod.setId(id_producto); 
		ps.editProducto(prod);
		redirectAttributes.addFlashAttribute("mensajeOk", "Producto editado correctamente");
		return "redirect:/admin/productos";
	}
	
	@GetMapping("borrar/{id_prod}")
	public String borrar(Model model, @PathVariable("id_prod") String id_prod, RedirectAttributes redirectAttributes) {
		Integer id_producto = Integer.parseInt(id_prod);
		ps.delProducto(id_producto);
		logger.info("Producto id_prod: "+id_prod+" borrado");
		redirectAttributes.addFlashAttribute("mensajeOk", "Producto borrado correctamente");
		return "redirect:/admin/productos";
	}
	
	@GetMapping("nuevo")
	public String pnuevo(Model model) {
		
		List<Categoria> cats = cs.getListaCategorias();
		model.addAttribute("categorias", cats);
		
		List<Impuesto> imps = is.getListaImpuestos();
		model.addAttribute("impuestos", imps);
		
		List<Proveedor> provs = prs.getListaProveedores();
		model.addAttribute("proveedores", provs);
		
		return "admin/productos/productoNuevo";
	}
	
	@PostMapping("nuevo/guardar")
	public String pnuevoGuardar(Model model, @ModelAttribute Producto prod,RedirectAttributes redirectAttributes) {
		// calculo del precio con iva y lo guardo en el objeto
		Float precioIVA = (float) (prod.getPrecio() * ( 1 + (prod.getImpuesto()/100)));
		prod.setPrecioImpuesto(DoubleRounder.round(precioIVA, 3));
				
		ps.addProducto(prod);
		logger.info("Nuevo producto dado de alta");
		redirectAttributes.addFlashAttribute("mensajeOk", "Producto creado correctamente");
		return "redirect:/admin/productos";
	}
	
	@GetMapping("exportar")
	public String exportarExcell(RedirectAttributes redirectAttributes) {
		 List<Producto> prods = ps.getListaProductos();
		 
		 pu.escribirExcell(prods);
		
		 redirectAttributes.addFlashAttribute("mensajeOk", "Productos exportados correctamente");
		 logger.info("Productos exportados correctamente");
		 return "redirect:/admin/productos";
		 
	}
	
	@GetMapping("recuperarDatos/")
	public String listaFicheros(Model model, HttpSession session) {
		Usuario user = (Usuario)session.getAttribute("usuario");
		if(user != null && user.getId_rol()==1) {
			List<String> ficheros = pu.ficherosExcellDatos();
			
			model.addAttribute("ficheros", ficheros);
			return "admin/productos/productoListaExcell";
		}
		else {
			model.addAttribute("mensaje", "No tienes permiso para acceder");
			return "error";
		}
	}
	
	@PostMapping("recuperarDatos/recuperar")
	public String recuperarProductos(Model model , HttpSession session, @RequestParam String nomFich, RedirectAttributes redirectAttributes) {
		Usuario user = (Usuario)session.getAttribute("usuario");
		// compruebo que el usuario tiene permisos para hacer la operacion
		if(user != null && user.getId_rol()==1) {
			
			// busco los nombres de los ficheros que contienen los datos exportados en xml
			ArrayList<Producto> prods = pu.leerExcell(nomFich);
			if(prods != null) {
				// si se encuentran datos se guardan en la base de datos
				for(Producto p: prods){
					ps.addProducto(p);
				}
				redirectAttributes.addFlashAttribute("mensajeOk", "Datos del fichero cargados en la base de datos");
				return "redirect:/admin/productos";
			}
			else {
				redirectAttributes.addFlashAttribute("mensaje", "Error al cargar datos del fichero");
				return "redirect:/admin/productos/recuperarDatos";
			}
		}
		model.addAttribute("mensaje", "No tienes permiso para acceder");
		return "error";
	}
}