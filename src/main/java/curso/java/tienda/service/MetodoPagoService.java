package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.MetodoPago;
import curso.java.tienda.repositories.MetodoPagoRepository;

@Service
public class MetodoPagoService {
	
	@Autowired
	private MetodoPagoRepository mr;
	
	public List<MetodoPago> getListaMetodosPago() {
	    return mr.findAll();
	}

	public void add(MetodoPago m) {
	    mr.save(m);
	}

	public void del(int id) {
		MetodoPago m = mr.getById(id);
	    mr.delete(m);
	}

	public void edit(MetodoPago m) {
	    mr.save(m);
	}

	public MetodoPago getMetodoPagoXId(int id) {
		MetodoPago m = mr.getById(id);
	    return m;
	}
}