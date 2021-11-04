package curso.java.tienda.models.DAO;

import java.util.ArrayList;

import curso.java.tienda.models.entities.Rol;

public class Roles {
	
	public static ArrayList<Rol> roles = new ArrayList<Rol>();
	
	public static boolean crear() {
		return true;
	}
	public static boolean borrar() {
		return true;
	}
	public static boolean modificar() {
		return true;
	}

	public static ArrayList<Rol>seleccionar() {
		if(roles.isEmpty()) {
			Roles.generar();
		}
		return roles;
	}
	
	private static void generar() {
		roles.add(new Rol(1, "administrador"));
		roles.add(new Rol(2, "usuario registrado"));
		roles.add(new Rol(3, "usuario no registrado"));
	}
}
