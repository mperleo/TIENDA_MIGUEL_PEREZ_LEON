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
	
	public List<OpcionMenu> getOpcionesMenu() {
	    return omr.findAll();
	}
	
	public List<OpcionMenu> getOpcionesMenuRol(Integer id_rol) {
	    return omr.buscarOpcionesRol(id_rol);
	}
	
	public List<OpcionMenu> getOpcionesMenuRolLista(Integer id_rol) {
	    return omr.buscarOpcionesTodosRoles(id_rol);
	}

	public void addOpcionMenu(OpcionMenu om) {
	    omr.save(om);
	}

	public void delOpcionMenu(Integer id_opcion) {
		OpcionMenu om = omr.getById(id_opcion);
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
