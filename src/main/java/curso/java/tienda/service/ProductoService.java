package curso.java.tienda.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import curso.java.tienda.models.entities.Producto;
import curso.java.tienda.repositories.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository pr;
	
	//private static Logger logger = LogManager.getLogger(ProductoService.class);
	
	public List<Producto> getListaProductos() {
		return pr.findAll();
		//return listaUsuarios;
	}
	
	public List<Producto> getListaProductosPorCat(String id_cat) {
		return pr.buscarProductosPorCat(id_cat);
	}
	
	public List<Producto> getListaProductosPorProv(String id_prov) {
		return pr.buscarProductosPorProv(id_prov);
	}
	
	public List<Producto> getLista4ProductosPorCat(int i) {
		return pr.buscar4ProductosPorCat(i);
	}
	
	public List<Producto> getListaProductosPocoStock() {
		return pr.buscarProductosPocoStock();
	}
	
	public Integer getIdUltimoProducto() {
		return pr.buscarIdUltimoProdcuto();
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
	
	/**
	 * Función que devuelve el número de productos que hay almacenados en la base de datos
	 * @return numero de usuarios productos en la base de datos
	 */
	public Integer count() {
		return pr.numProds();
	}
}
