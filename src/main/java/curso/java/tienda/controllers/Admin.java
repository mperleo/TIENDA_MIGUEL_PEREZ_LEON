package curso.java.tienda.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.service.PedidoService;
import curso.java.tienda.service.ProductoService;
import curso.java.tienda.service.UsuarioService;
import curso.java.tienda.utils.EstadisticasUtil;

@Controller
@RequestMapping("/admin")
public class Admin {
	
	private static Logger logger = LogManager.getLogger(Admin.class);
	
	@Autowired
	private ProductoService ps;
	@Autowired
	private UsuarioService us;
	@Autowired
	private PedidoService pds;
	@Autowired
	private EstadisticasUtil eu;
	
	@GetMapping("")
	public String portada(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null && usuario.getId_rol() <= 2) {
			
			Integer numUs = ps.count();
			model.addAttribute("numUs", numUs);
			
			Integer numProds = us.count();
			model.addAttribute("numProds", numProds);
			
		    Double totalPeds = pds.getTotalPedidosEsteMes(eu.fechaFormato("yyyy-MM"));
		    model.addAttribute("totalPeds", totalPeds);
		    
		    List<String> totalPdsY = pds.getTotalPedidosMeses(eu.fechaFormato("yyyy"));
		    model.addAttribute("totalPdsY",totalPdsY);
		    
		    List<String> mesesPdsY = pds.getMesesPedidos(eu.fechaFormato("yyyy"));
		    model.addAttribute("mesesPdsY",mesesPdsY);
		    
		    List<Producto> prodsPocoSotck = ps.getListaProductosPocoStock();
		    model.addAttribute("prodsPocoSotck",prodsPocoSotck);
		    
		    List<String> totalPorCat = pds.getTotalPedidosPorCategoria(eu.fechaFormato("yyyy"));
		    model.addAttribute("totalPorCat",totalPorCat);
		    
		    List<String> nombresCat = pds.getTotalPedidosPorCategoriaNombreCategoria(eu.fechaFormato("yyyy"));
		    model.addAttribute("nombresCat",nombresCat);
		    
			return "admin/index";
		}
		else {
			model.addAttribute("mensaje", "no tienes permiso para acceder a esta página");
			logger.error("Intento de acceso sin permisos en pagina admin");
			return "error";
		}
	}
}
