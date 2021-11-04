package curso.java.tienda.models.DAO;

import java.util.ArrayList;

import curso.java.tienda.models.entities.Pedido;

public class Pedidos {
	
	public ArrayList<Pedido> pedidos  = new ArrayList<Pedido>();
	
	public static boolean crear() {
		return true;
	}
	public static boolean borrar() {
		return true;
	}
	public static boolean modificar() {
		return true;
	}
	public static ArrayList<Pedido>seleccionar() {
		return null;
	}

}
