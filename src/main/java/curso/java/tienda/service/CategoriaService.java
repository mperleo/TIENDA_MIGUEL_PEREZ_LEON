package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Categoria;
import curso.java.tienda.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository cr;
	
	public List<Categoria> getListaCategorias() {
	    return cr.findAll();
	    //return listaUsuarios;
	}

	public void add(Categoria c) {
	    cr.save(c);
	}

	public void del(int id) {
		Categoria c = cr.getById(id);
	    cr.delete(c);
	}

	public void edit(Categoria c) {
	    cr.save(c);
	}

	public Categoria getCategoriaXId(int id) {
		Categoria c = cr.getById(id);
	    return c;
	}
}
