package curso.java.tienda.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.models.entities.Valoracion;
import curso.java.tienda.service.ProductoService;
import curso.java.tienda.service.ValoracionService;

@Controller
@RequestMapping("/producto")
public class ProductosUsuario {
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private ValoracionService vs;
	
	@RequestMapping("ver/{id_prod}")
	public String producto(Model model, @PathVariable("id_prod") String id_prod, HttpSession session) {
		Integer id_producto = Integer.parseInt(id_prod);
		Producto prod = ps.getProductoXId(id_producto);
		List<Producto> prodsReco = ps.getLista4ProductosPorCat(prod.getCategoria().getId());
		List<Valoracion> valoraciones = vs.getValoracionesXProd(id_producto);
		
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null) {
			boolean puedeValorar = vs.getPuedeValorarUsuario(usuario.getId(), prod.getId());
			model.addAttribute("puedeValorar",puedeValorar);
		}		
	
		model.addAttribute("valoraciones", valoraciones);
		model.addAttribute("producto", prod);
		model.addAttribute("prodsReco", prodsReco);
		
		
		return "producto/detail";
	}
}
