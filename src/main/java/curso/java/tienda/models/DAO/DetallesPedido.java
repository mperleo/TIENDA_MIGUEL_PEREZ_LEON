package curso.java.tienda.models.DAO;

import java.util.ArrayList;

import curso.java.tienda.models.entities.DetallePedido;

public class DetallesPedido {
	
	public ArrayList<DetallePedido> detallesPedidos  = new ArrayList<DetallePedido>();
	
	public static boolean crear() {
		return true;
	}
	public static boolean borrar() {
		return true;
	}
	public static boolean modificar() {
		return true;
	}
	public static ArrayList<DetallePedido>seleccionar() {
		return null;
	}

}
