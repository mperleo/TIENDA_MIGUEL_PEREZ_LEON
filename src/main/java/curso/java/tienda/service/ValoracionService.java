package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Valoracion;
import curso.java.tienda.repositories.ValoracionRepository;

@Service
public class ValoracionService {
	
	@Autowired
	private ValoracionRepository vr;
	
	public List<Valoracion> getListaValoracions() {
	    return vr.findAll();
	}

	public void add(Valoracion v) {
	    vr.save(v);
	}

	public void del(int id) {
		Valoracion v = vr.getById(id);
	    vr.delete(v);
	}

	public void edit(Valoracion v) {
	    vr.save(v);
	}

	public Valoracion getValoracionXId(int id) {
		Valoracion v = vr.getById(id);
	    return v;
	}
}