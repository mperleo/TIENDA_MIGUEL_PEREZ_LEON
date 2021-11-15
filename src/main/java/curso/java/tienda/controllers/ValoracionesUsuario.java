package curso.java.tienda.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.models.entities.Usuario;
import curso.java.tienda.models.entities.Valoracion;
import curso.java.tienda.service.ProductoService;
import curso.java.tienda.service.ValoracionService;

@Controller
@RequestMapping("valoraciones")
public class ValoracionesUsuario {
	@Autowired
	private ValoracionService vs;
	@Autowired
	private ProductoService ps;
	
	private static Logger logger = LogManager.getLogger(ValoracionesUsuario.class);
	
	@GetMapping("")
	public String verTodos(Model model) {
	    List<Valoracion> valoraciones = vs.getListaValoracions();
	    model.addAttribute("valoraciones", valoraciones);
	    return "valoraciones/valoraciones";
	}

	@GetMapping("editar/{id_valoracion}")
	public String editar(Model model, @PathVariable("id_valoracion") String id_valor) {
	    Integer id_valoracion = Integer.parseInt(id_valor);
	    Valoracion valoracion = vs.getValoracionXId(id_valoracion);
	    model.addAttribute("valoracion", valoracion);
	    
	    return "valoraciones/valoracionEditar";
	}

	@PostMapping("editar/{id_valoracion}/guardar")
	public String editarGuardar(Model model, @PathVariable("id_valoracion") String id_valor, @Valid @ModelAttribute("valoracion") Valoracion valoracion , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			valoracion.setId(Integer.parseInt(id_valor));
			 model.addAttribute(valoracion);
			 return "valoraciones/valoracionEditar";
		 }else {  
			Integer id_valoracion = Integer.parseInt(id_valor);
		    Valoracion valSinEditar = vs.getValoracionXId(id_valoracion);
		    
		    valoracion.setId(id_valoracion);
		    valoracion.setProducto(valSinEditar.getProducto());
		    valoracion.setUsuario(valSinEditar.getUsuario());
		    
		    vs.edit(valoracion);
		    logger.info("Valoracion id: "+id_valor+" editado");
		    redirectAttributes.addFlashAttribute("mensajeOk", "Valoracion editado correctamente");
		    return "redirect:/valoraciones";
		 }   
	}

	@GetMapping("borrar/{id_valoracion}")
	public String borrar(Model model, @PathVariable("id_valoracion") String id_valor, RedirectAttributes redirectAttributes) {
	    Integer id_valoracion = Integer.parseInt(id_valor);
	    vs.del(id_valoracion);
	    logger.info("Valoracion id: "+id_valor+" eliminado");
	    redirectAttributes.addFlashAttribute("mensajeOk", "Valoracion borrado correctamente");
	    return "redirect:/valoraciones";
	}
	
	@GetMapping("nuevo/{id_prod}/")
	public String nuevo(Model model, @PathVariable("id_prod") String id_prod, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		//Busco si el usuario ha valorado el producto antes
		Valoracion valoracion = vs.getValoracionByProdAndUser(Integer.parseInt(id_prod), usuario.getId());
		
		// si hay una valoracion lo mando a la funcion de editar una valoracion
		if(valoracion != null) {
			return "redirect:/valoraciones/editar/"+valoracion.getId()+"/";
		}
		else {
			valoracion = new Valoracion();
		}
	    model.addAttribute("valoracion", valoracion);
	    model.addAttribute("id_prod", id_prod);
	    return "valoraciones/valoracionNuevo";
	}


	@PostMapping("nuevo/{id_producto}/guardar")
	public String nuevoGuardar(Model model, @PathVariable("id_producto") String id_producto, @Valid @ModelAttribute("valoracion") Valoracion valoracion , BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) {
		 if(bindingResult.hasErrors()) {
			 model.addAttribute("valoracion", valoracion);
			 return "valoraciones/valoracionNuevo";
		 }else {
			 Usuario usuario = (Usuario) session.getAttribute("usuario");
			 Producto producto = ps.getProductoXId(Integer.parseInt(id_producto));
			 valoracion.setUsuario(usuario);
			 valoracion.setProducto(producto);
			
			 vs.add(valoracion);
			
			 logger.info("Nueva valoracion guardado");
			 redirectAttributes.addFlashAttribute("mensajeOk", "Valoracion creado correctamente");
			 return "redirect:/valoraciones";
		 }

	}
}