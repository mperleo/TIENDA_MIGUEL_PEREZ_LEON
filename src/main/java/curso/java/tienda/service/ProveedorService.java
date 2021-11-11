package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Proveedor;
import curso.java.tienda.repositories.ProveedorRepository;

@Service
public class ProveedorService {
	
	@Autowired
	private ProveedorRepository pr;
	
	public List<Proveedor> getListaProveedores() {
	    return pr.findAll();
	    //return listaUsuarios;
	}

	public void add(Proveedor r) {
	    pr.save(r);
	}

	public void del(int id) {
		Proveedor r = pr.getById(id);
	    pr.delete(r);
	}

	public void edit(Proveedor r) {
	    pr.save(r);
	}

	public Proveedor getProveedorXId(int id) {
		Proveedor r = pr.getById(id);
	    return r;
	}
}