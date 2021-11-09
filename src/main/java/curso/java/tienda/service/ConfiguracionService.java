package curso.java.tienda.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.tienda.models.entities.Configuracion;
import curso.java.tienda.repositories.ConfiguracionRepository;

@Service
public class ConfiguracionService {
	
	@Autowired
	private ConfiguracionRepository cr;
	
	public List<Configuracion> getListaConfiguraciones() {
	    return cr.findAll();
	    //return listaUsuarios;
	}

	public void addConfiguracion(Configuracion c) {
	    cr.save(c);
	}

	public void delConfiguracion(int id) {
		Configuracion c = cr.getById(id);
	    cr.delete(c);
	}

	public void editConfiguracion(Configuracion c) {
	    cr.save(c);
	}

	public Configuracion getConfiguracionXId(int id) {
		Configuracion c = cr.getById(id);
	    return c;
	}
	
	public HashMap<String, Configuracion> getDatosFactura() {
		List<Configuracion> c = cr.buscarDatosFactura();
		
		HashMap<String, Configuracion> mapaFactura = new HashMap<String, Configuracion>();
		
		for(Configuracion i: c){
			mapaFactura.put(i.getClave(), i);
		}
			
	    return mapaFactura;
	}
}
