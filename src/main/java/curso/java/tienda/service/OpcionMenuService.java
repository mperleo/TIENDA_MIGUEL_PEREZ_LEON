package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.OpcionMenu;
import curso.java.tienda.repositories.OpcionMenuRepository;

@Service
public class OpcionMenuService {
	
	@Autowired
	private OpcionMenuRepository omr;
	
	public List<OpcionMenu> getListaProductos() {
	    return omr.findAll();
	    //return listaUsuarios;
	}

	public void addOpcionMenu(OpcionMenu om) {
	    omr.save(om);
	}

	public void delOpcionMenu(int id) {
		OpcionMenu om = omr.getById(id);
	    omr.delete(om);
	}

	public void editOpcionMenu(OpcionMenu om) {
	    omr.save(om);
	}

	public OpcionMenu getOpcionMenuXId(int id) {
		OpcionMenu om = omr.getById(id);
	    return om;
	}
}