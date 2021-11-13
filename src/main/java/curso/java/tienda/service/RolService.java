package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Rol;
import curso.java.tienda.repositories.RolRepository;

@Service
public class RolService {
	
	@Autowired
	private RolRepository rr;
	
	public List<Rol> getListaRoles() {
	    return rr.findAll();
	}

	public void add(Rol r ) {
	    rr.save(r);
	}

	public void del(int id) {
	    Rol r = rr.getById(id);
	    rr.delete(r);
	}

	public void edit(Rol r) {
	    rr.save(r);
	}

	public Rol getRolXId(int id) {
	    Rol r = rr.getById(id);
	    return r;
	}
}
