package curso.java.tienda.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EstadisticasUtil {
	public String fechaFormato(String formato) {
		LocalDateTime fecha = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern(formato);
	    return fecha.format(myFormatObj);
	}
	
	public List<String> mesesAnombre(List<String> listaMeses){
		String[] mesesNombre = {"'Enero'","'Febrero'","'Marzo'","'Abril'","'Mayo'","'Junio'","'Julio'","'Agosto'","'Septiembre'","'Octubre'","'Noviembre'","'Diciembre'"};
		List<String> retorno = new ArrayList<String>();
		
		if(!listaMeses.isEmpty())
		for(String mes: listaMeses) {
			retorno.add(mesesNombre[Integer.parseInt(mes)]);
		}
		return retorno;
	}
}
