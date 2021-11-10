package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.repositories.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository pr;
	
	public List<Producto> getListaProductos() {
		return pr.findAll();
		//return listaUsuarios;
	}
	
	public List<Producto> getListaProductosPorCat(String id_cat) {
		return pr.buscarProductosPorCat(id_cat);
	}
	
	public void addProducto(Producto p) {
		pr.save(p);
	}
	
	public void delProducto(int id) {
		Producto p = pr.getById(id);
		pr.delete(p);
	}
	
	public void editProducto(Producto p) {
		pr.save(p);
	}
	
	public Producto getProductoXId(int id) {
		Producto p = pr.getById(id);
		return p;
	}
	
	/*
	 * 
	 * public static ArrayList<Producto> seleccionar() {
		ArrayList<Producto> productos  = new ArrayList<Producto>();
		
		productos.add(new Producto(1, 1, "Cannondale TopStone carbon 4", "muy bonita", (Double)800.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "cannondale-topstone-carbon-4-champagne-1.jpg"));
		productos.add(new Producto(2, 1, "Cannondale TopStone carbon 6", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "cannondale-topstone-carbon-6-beetle-green-1.jpg"));
		productos.add(new Producto(3, 1, "Cube access ws", "muy bonita", (Double)800.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "cube-access-ws-exc-women-stonegreynfern-1.jpg"));
		productos.add(new Producto(4, 1, "Cube aim ex", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "cube-aim-ex-bluenred-1.jpg"));
		productos.add(new Producto(5, 1, "Excelsior Touring 3 speed", "muy bonita", (Double)800.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "excelsior-touring-3-speed-tsp-opal-blue-boss-blue-1.jpg"));
		productos.add(new Producto(6, 1, "Fixie blackheath", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "fixie-inc-blackheath-petrol-red-1.jpg"));
		productos.add(new Producto(7, 1, "Johni-Loco viena", "muy bonita", (Double)800.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "johnny-loco-vienna-urban-cruiser-step-over-piano-black-1.jpg"));
		productos.add(new Producto(8, 1, "Kona operator", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "kona-operator-cr-gloss-dark-green-metallic-green-2.jpg"));
		productos.add(new Producto(9, 1, "Marin Headlands 2", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "marin-headlands-2-gloss-teal-carbon-magenta-1.jpg"));
		productos.add(new Producto(10, 1, "Orbea orca aero", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "orbea-orca-aero-m20i-team-black-1.jpg"));
		productos.add(new Producto(11, 1, "Ortler Van Dyck wave", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "ortler-van-dyck-wave-red-1.jpg"));
		productos.add(new Producto(12, 1, "Polygon Strattos", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "polygon-strattos-s3-white-1.jpg"));
		productos.add(new Producto(13, 1, "Santa Cruz TallBoy 4", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "santa-cruz-tallboy-4-cc-29-x01-reserve-gloss-black-1.jpg"));
		productos.add(new Producto(14, 1, "Willier GTR team rim", "muy cara", (Double)6000.0, 12, "2020-01-12", "2020-01-12", (float) 21.0, "wilier-gtr-team-rim-105-black-red-glossy-finish-1.jpg"));
		
		return productos;
	}*/
}
