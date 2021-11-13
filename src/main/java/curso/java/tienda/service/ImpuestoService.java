package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Impuesto;
import curso.java.tienda.repositories.ImpuestoRepository;

@Service
public class ImpuestoService {
	
	@Autowired
	private ImpuestoRepository ir;
	
	public List<Impuesto> getListaImpuestos() {
	    return ir.findAll();
	}

	public void add(Impuesto i) {
	    ir.save(i);
	}

	public void del(int id) {
		Impuesto i = ir.getById(id);
	    ir.delete(i);
	}

	public void edit(Impuesto i) {
	    ir.save(i);
	}

	public Impuesto getImpuestoXId(int id) {
		Impuesto i = ir.getById(id);
	    return i;
	}
}