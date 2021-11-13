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
import curso.java.tienda.service.ImpuestoService;
import curso.java.tienda.service.MetodoPagoService;
import curso.java.tienda.service.OpcionMenuService;
import curso.java.tienda.service.RolService;
import curso.java.tienda.service.UsuarioService;
import curso.java.tienda.models.entities.*;

@Controller
@RequestMapping("cargarDatosInicio")
public class CargarDatos {
	
	@Autowired
	private UsuarioService us;
	@Autowired
	private RolService rs;
	@Autowired
	private ConfiguracionService cs;
	@Autowired
	private OpcionMenuService os;
	@Autowired
	private ImpuestoService is;
	@Autowired
	private MetodoPagoService ms;
	
	private static Logger logger = LogManager.getLogger(CargarDatos.class);
	
	@GetMapping("")
	public String meterDatosIniciales(Model model) {
		
		if(us.getListaUsuarios().isEmpty() && rs.getListaRoles().isEmpty() && cs.getListaConfiguraciones().isEmpty() && os.getOpcionesMenu().isEmpty()) {
		
			System.out.println("Buscando si hay datos en la base de datos y creandolos si no lo estan...");
		
			ArrayList<Configuracion> configs = this.defConf();
			ArrayList<Rol> roles = this.defrol();
			ArrayList<Usuario> usuaraios = this.defUs();
			ArrayList<OpcionMenu> opciones = this.defOpcMen();
			ArrayList<Impuesto> impuestos = this.defImp();
			ArrayList<MetodoPago> metodos = this.defMetPag();
			
			
			this.addConfs(configs);
			this.addRol(roles);
			this.addUs(usuaraios);
			this.addOpcMen(opciones);
			this.addImp(impuestos);
			this.addMetPag(metodos);
			
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
		ArrayList<Rol> roles = new ArrayList<Rol>();
		roles.add(new Rol(1, "Administrador"));
		roles.add(new Rol(2, "Empleado"));
		roles.add(new Rol(3, "Usuario registrado"));
		
				
		return roles;
	}
	private void addRol(ArrayList<Rol> lista) {
		System.out.println("Creando los roles de configuracion base");
		for(Rol i: lista) {
			rs.add(i);
		}
	}
	
	private ArrayList<Usuario> defUs(){
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		users.add(new Usuario(1, "admin@admin.com", us.hashPassword("12345Password"), "admin", "admin", "admin", "admin", "admin", "admin", "admin", "admin"));
		users.add(new Usuario(2, "empleado@admin.com", us.hashPassword("12345Password"), "admin", "admin", "admin", "admin", "admin", "admin", "admin", "admin"));
		
		return users;
	}
	private void addUs(ArrayList<Usuario> lista) {
		System.out.println("Creando los usuarios de configuracion base");
		for(Usuario i: lista) {
			us.addUsuario(i);
		}
	}
	
	private ArrayList<OpcionMenu> defOpcMen(){
		ArrayList<OpcionMenu> opMens = new ArrayList<OpcionMenu>();
		opMens.add(new OpcionMenu(1, "Opciones menú","/admin/opcionesMenu/"));
		opMens.add(new OpcionMenu(1, "Configuraciones","/admin/configuraciones"));
		opMens.add(new OpcionMenu(1, "Métodos de pago","/admin/metodosPago"));
		opMens.add(new OpcionMenu(1, "Impuestos","/admin/impuestos"));
		opMens.add(new OpcionMenu(2, "Categorias","/admin/opcionesMenu/"));
		opMens.add(new OpcionMenu(2, "Usuarios","/admin/Usuarios/"));
		opMens.add(new OpcionMenu(2, "Productos","/admin/productos"));
		opMens.add(new OpcionMenu(2, "Pedidos","/admin/pedidos/"));
		opMens.add(new OpcionMenu(2, "Proveedores","/admin/proveedores"));
		opMens.add(new OpcionMenu(3, "Finalizar pedido","/cesta/pedido"));
		opMens.add(new OpcionMenu(3, "Pedidos","/pedidos/"));
		opMens.add(new OpcionMenu(3, "Cancelar pedido","/pedidos/ver"));
		opMens.add(new OpcionMenu(3, "Ver pedido","/pedidos/cancelar"));
		opMens.add(new OpcionMenu(3, "Ver perfil","/miUsuario/ver"));
		opMens.add(new OpcionMenu(3, "Modificar perfil","/miUsuario/modificar"));
		opMens.add(new OpcionMenu(3, "Modificar contraseña","/miUsuario/modificarPass"));
		opMens.add(new OpcionMenu(3, "Cerrar sesión","/login/cerrarSesion"));
		
		return opMens;
	}
	private void addOpcMen(ArrayList<OpcionMenu> lista) {
		System.out.println("Creando los usuarios de configuracion base");
		for(OpcionMenu i: lista) {
			os.addOpcionMenu(i);
		}
	}
	
	private ArrayList<Impuesto> defImp(){
		ArrayList<Impuesto> imps = new ArrayList<Impuesto>();
		imps.add(new Impuesto(1, (float) 21.0));
		imps.add(new Impuesto(2, (float) 10.0));
		imps.add(new Impuesto(3, (float) 4.0));
		
		return imps;
	}
	private void addImp(ArrayList<Impuesto> lista) {
		System.out.println("Creando los usuarios de configuracion base");
		for(Impuesto i: lista) {
			is.add(i);
		}
	}
	
	private ArrayList<MetodoPago> defMetPag(){
		ArrayList<MetodoPago> metPags = new ArrayList<MetodoPago>();
		metPags.add(new MetodoPago(1, "Tarjeta"));
		metPags.add(new MetodoPago(2, "PayPal"));
		
		return metPags;
	}
	private void addMetPag(ArrayList<MetodoPago> lista) {
		System.out.println("Creando los usuarios de configuracion base");
		for(MetodoPago i: lista) {
			ms.add(i);
		}
	}

}
