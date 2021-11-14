package curso.java.tienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Descuento;
import curso.java.tienda.repositories.DescuentoRepository;

@Service
public class DescuentoService {
	
	@Autowired
	private DescuentoRepository dr;
	
	public List<Descuento> getListaDescuentos() {
	    return dr.findAll();
	}
	
	public Descuento getDescuentoAct(String fecha) {
		return dr.getDescActivo(fecha);
	}
	
	public Descuento getDescuentoPorCodigo(String fecha, String codigo) {
		return dr.getDescPorCodigo(fecha, codigo);
	}

	public void add(Descuento d) {
	    dr.save(d);
	}

	public void del(int id) {
		Descuento d = dr.getById(id);
	    dr.delete(d);
	}

	public void edit(Descuento d) {
	    dr.save(d);
	}

	public Descuento getDescuentoXId(int id) {
		Descuento d = dr.getById(id);
	    return d;
	}
}