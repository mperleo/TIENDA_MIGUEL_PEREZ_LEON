package curso.java.tienda.service;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.repositories.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository pr;
	
	private static Logger logger = LogManager.getLogger(ProductoService.class);
	
	public List<Producto> getListaProductos() {
		return pr.buscarProductosOrdenFecha();
		//return listaUsuarios;
	}
	
	public List<Producto> getListaProductosPorCat(String id_cat) {
		return pr.buscarProductosPorCat(id_cat);
	}
	
	public List<Producto> getLista4ProductosPorCat(int i) {
		return pr.buscar4ProductosPorCat(i);
	}
	
	public List<Producto> getListaProductosFiltro(String id_cat, Integer orden){
		if(id_cat.equals("todos")) {
			switch (orden) {
			case 1:
				return pr.buscarProductosOrdenFecha();
			case 2:
				return pr.findAll(Sort.by("stock").descending());
			case 3:
				return pr.findAll(Sort.by("precio").ascending());
			case 4:
				return pr.findAll(Sort.by("precio").descending());
			default:
				return pr.buscarProductosOrdenFecha();
			}
		}
		else {
			switch (orden) {
			case 1:
				return pr.findByIdCategoria(Integer.parseInt(id_cat), Sort.by("fecha_alta").descending());
			case 2:
				return pr.findByIdCategoria(Integer.parseInt(id_cat), Sort.by("stock").descending());
			case 3:
				return pr.findByIdCategoria(Integer.parseInt(id_cat), Sort.by("precio").ascending());
			case 4:
				return pr.findByIdCategoria(Integer.parseInt(id_cat), Sort.by("precio").descending());
			default:
				return pr.findByIdCategoria(Integer.parseInt(id_cat), Sort.by("fecha_alta").descending());
			}
		}
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
	
	/*public void escribirExcell(List<Producto> productos) {
		LocalDateTime fecha = LocalDateTime.now(); // Create a date object
	    
		File fichero = new File("./ficheros/"+fecha.toString()+"Productos.xls");
		 
		 try{
	        	WritableWorkbook w = Workbook.createWorkbook(fichero);
	 
	        	
	        	//Creo la hoja del documento con el nombre de productos
	        	WritableSheet sheet = w.createSheet("Productos", 0);
	        	
	        	Integer i =0;
	        	//sheet.addCell( new jxl.write.Label(0, i, "id") );
	            sheet.addCell( new jxl.write.Label(0, i, "id_categoria") );
	            sheet.addCell( new jxl.write.Label(1, i, "nombre") );
	            sheet.addCell( new jxl.write.Label(2, i, "descripcion") );
	            sheet.addCell( new jxl.write.Label(3, i, "precio") );
	            sheet.addCell( new jxl.write.Label(4, i, "stock") );
	            sheet.addCell( new jxl.write.Label(5, i, "fecha_alta") );
	            sheet.addCell( new jxl.write.Label(6, i, "fecha_baja") );
	            sheet.addCell( new jxl.write.Label(7, i, "impuesto") );
	            sheet.addCell( new jxl.write.Label(8, i, "imagen") );
	            sheet.addCell( new jxl.write.Label(9, i, "precioImpuesto") );
	            i++;
	            
	        	//Hago un recorrido por la lista de productos escribiendo en la linea i los datos del producto p
	        	for(Producto p: productos) {
		            //sheet.addCell( new jxl.write.Label(0, i, p.getId().toString()) );
		            sheet.addCell( new jxl.write.Label(0, i, p.getId_categoria().toString()) );
		            sheet.addCell( new jxl.write.Label(1, i, p.getNombre()) );
		            sheet.addCell( new jxl.write.Label(2, i, p.getDescripcion()) );
		            sheet.addCell( new jxl.write.Label(3, i, p.getPrecio().toString()) );
		            sheet.addCell( new jxl.write.Label(4, i, p.getStock().toString()) );
		            sheet.addCell( new jxl.write.Label(5, i, p.getFecha_alta()) );
		            sheet.addCell( new jxl.write.Label(6, i, p.getFecha_baja()) );
		            sheet.addCell( new jxl.write.Label(7, i, p.getImpuesto().toString()) );
		            sheet.addCell( new jxl.write.Label(8, i, p.getImagen()) );
		            sheet.addCell( new jxl.write.Label(9, i, p.getPrecioImpuesto().toString()) );
		            i++;
	        	}
	        	
	        	// escribo los datos en el fichero y lo cierro
	            w.write();
	            w.close();
	            logger.info("Fichero con la copia de los productos creado");
	        	
	        } catch (Exception e) {
	        	logger.error("Error al escribir datos en el fichero de exportación de productos Error: "+e);
	        	e.printStackTrace();
	        }
	}*/
	
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
