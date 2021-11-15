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
	
	public List<Valoracion> getValoracionesXProd(int id_producto) {
		List<Valoracion> v = vr.findByProd(id_producto);
	    return v;
	}
	
	public Valoracion getValoracionByProdAndUser(Integer id_producto,Integer id_usuario) {
		Valoracion v = vr.findByProdAndUser(id_producto, id_usuario);
		return v;
	}
	
	public boolean getPuedeValorarUsuario(Integer id_usuario, Integer id_producto) {
		String res = vr.puedeValorarUsuario(id_usuario, id_producto);
		if(res.equals("true")) {
			return true;
		}else {
			return false;
		}
	}

	public List<Valoracion> getListaValoracionesIdUsuario(Integer id) {
		return vr.getValoracionesUsuario(id);
	}
}