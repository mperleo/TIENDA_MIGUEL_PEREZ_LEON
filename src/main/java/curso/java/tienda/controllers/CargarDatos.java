package curso.java.tienda.controllers;

import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.OpcionMenuService;
import curso.java.tienda.service.RolService;
import curso.java.tienda.service.UsuarioService;
import curso.java.tienda.models.entities.*;

@Controller
@RequestMapping("cargarDatosInicio")
public class CargarDatos {
	
	@Autowired
	private UsuarioService us = new UsuarioService();
	@Autowired
	private RolService rs = new RolService();
	@Autowired
	private ConfiguracionService cs = new ConfiguracionService();
	@Autowired
	private OpcionMenuService os = new OpcionMenuService();
	
	private static Logger logger = LogManager.getLogger(CargarDatos.class);
	
	@GetMapping("")
	public String meterDatosIniciales(Model model) {
		
		if(us.getListaUsuarios().isEmpty() && rs.getListaRoles().isEmpty() && cs.getListaConfiguraciones().isEmpty() && os.getOpcionesMenu().isEmpty()) {
		
			System.out.println("Buscando si hay datos en la base de datos y creandolos si no lo estan...");
		
			ArrayList<Configuracion> configs = this.defConf();
			ArrayList<Rol> roles = this.defrol();
			ArrayList<Usuario> usuaraios = this.defUs();
			ArrayList<OpcionMenu> opciones = this.defOpcMen();
			
			this.addConfs(configs);
			this.addRol(roles);
			this.addUs(usuaraios);
			this.addOpcMen(opciones);
			
			logger.info("Datos iniciales insertados en la base de datos");
			return "redirect:/home";
		}	
		else {
			logger.error("Intento de insertar datos iniciales cuando hay datos en la aplicación");
			model.addAttribute("mensaje", "No se puede realizar la operacion indicada");
			return "error";
		}

	}
	
	private ArrayList<Configuracion> defConf(){
		ArrayList<Configuracion> configs = new ArrayList<Configuracion>();
		configs.add(new Configuracion("factura_num", "0", "numero" ));
		configs.add(new Configuracion("factura_nombre_empresa", "0", "BikëMeisters" ));
		configs.add(new Configuracion("factura_nif", "0", "B123124242-ZA" ));
		configs.add(new Configuracion("factura_direccion", "0", "Calle Santa Teresa 28, Zamora, Zamora, 49011" ));
		configs.add(new Configuracion("contra_por_defecto", "0", "12345Password" ));
		
		return configs;
	}
	private void addConfs(ArrayList<Configuracion> lista) {
		System.out.println("Creando las opciones de configuracion base");
		for(Configuracion i: lista) {
			cs.addConfiguracion(i);
		}
		
	}
	
	private ArrayList<Rol> defrol(){
		ArrayList<Rol> configs = new ArrayList<Rol>();
		configs.add(new Rol(1, "Administrador"));
		configs.add(new Rol(2, "Empleado"));
		configs.add(new Rol(3, "Usuario registrado"));
		
				
		return configs;
	}
	private void addRol(ArrayList<Rol> lista) {
		System.out.println("Creando los roles de configuracion base");
		for(Rol i: lista) {
			rs.addRol(i);
		}
	}
	
	private ArrayList<Usuario> defUs(){
		ArrayList<Usuario> configs = new ArrayList<Usuario>();
		configs.add(new Usuario(1, "admin@admin.com", "12345Password", "admin", "admin", "admin", "admin", "admin", "admin", "admin", "admin"));
		configs.add(new Usuario(2, "empleado@admin.com", "12345Password", "admin", "admin", "admin", "admin", "admin", "admin", "admin", "admin"));
		
		return configs;
	}
	private void addUs(ArrayList<Usuario> lista) {
		System.out.println("Creando los usuarios de configuracion base");
		for(Usuario i: lista) {
			us.addUsuario(i);
		}
	}
	
	private ArrayList<OpcionMenu> defOpcMen(){
		ArrayList<OpcionMenu> configs = new ArrayList<OpcionMenu>();
		configs.add(new OpcionMenu(1, "Opciones menú","/admin/opcionesMenu/"));
		configs.add(new OpcionMenu(2, "Categorias","/admin/opcionesMenu/"));
		configs.add(new OpcionMenu(2, "Usuarios","/admin/Usuarios/"));
		configs.add(new OpcionMenu(1, "Configuraciones","/admin/configuraciones"));
		configs.add(new OpcionMenu(2, "Productos","/admin/productos"));
		configs.add(new OpcionMenu(2, "Pedidos","/admin/pedidos/"));
		configs.add(new OpcionMenu(3, "Finalizar pedido","/cesta/pedido"));
		configs.add(new OpcionMenu(3, "Pedidos","/pedidos/"));
		configs.add(new OpcionMenu(3, "Cancelar pedido","/pedidos/ver"));
		configs.add(new OpcionMenu(3, "Ver pedido","/pedidos/cancelar"));
		configs.add(new OpcionMenu(3, "Ver perfil","/miUsuario/ver"));
		configs.add(new OpcionMenu(3, "Modificar perfil","/miUsuario/modificar"));
		configs.add(new OpcionMenu(3, "Modificar contraseña","/miUsuario/modificarPass"));
		configs.add(new OpcionMenu(3, "Cerrar sesión","/login/cerrarSesion"));
		
		return configs;
	}
	private void addOpcMen(ArrayList<OpcionMenu> lista) {
		System.out.println("Creando los usuarios de configuracion base");
		for(OpcionMenu i: lista) {
			os.addOpcionMenu(i);
		}
	}

}
