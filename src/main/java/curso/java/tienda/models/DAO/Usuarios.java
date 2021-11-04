package curso.java.tienda.models.DAO;

import java.util.ArrayList;


import curso.java.tienda.models.entities.Usuario;

public class Usuarios {
	
	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	public static ArrayList<Usuario> usuariosLogin = new ArrayList<Usuario>();

	public static boolean crear() {
		return true;
	}
	public static boolean borrar() {
		return true;
	}
	public static boolean modificar() {
		return true;
	}
	
	public static ArrayList<Usuario>seleccionar() {
		if(usuarios.isEmpty()) {
			Usuarios.generar();
		}
		return usuarios;
	}
	
	public static Usuario seleccionarUno(Usuario buscar) {
		if(usuariosLogin.isEmpty()) {
			Usuarios.generar();
		}
		
		for(Usuario u: usuarios) {
			if(u.getEmail().equals(buscar.getEmail()) && u.getClave().equals(buscar.getClave()) ) {
				return u;
			}
		}
		
		return null;
	}
	
	private static void generar() {
		usuarios.add(new Usuario(1, 1, "admin@admin.com", "admin", "admin", "admin", "admin", "admin", "admin", "admin", "6666666", "123123313q"));
		usuarios.add(new Usuario(2, 1, "perezleon.miguel@gmail.com", "1234", "Miguel", "Pérez", "León", "calle calle", "zamora", "zamora", "6666666", "12312434w"));
		usuariosLogin.add(new Usuario("admin@admin.com", "admin"));
		usuariosLogin.add(new Usuario("perezleon.miguel@gmail.com", "1234"));
	}
}
