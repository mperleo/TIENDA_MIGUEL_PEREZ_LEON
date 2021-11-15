package curso.java.tienda.utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CategoriasUtil {
	
	private static Logger logger = LogManager.getLogger(CategoriasUtil.class);
	
	public String subirImagen(String id_cat, MultipartFile file) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
	    URL appResourceURL = loader.getResource("static");
	    String dbConfigFileRoute = appResourceURL.getPath();
	    //dbConfigFileRoute = dbConfigFileRoute.substring(1, dbConfigFileRoute.length());
	    int separador = dbConfigFileRoute.lastIndexOf("/");
	    dbConfigFileRoute = dbConfigFileRoute.substring(1, separador);
	    String ruta = "/"+dbConfigFileRoute + "/static/img/cats/"+id_cat+ this.extensionFichero(file.getOriginalFilename());
	    String rutaRetorno = "cats/"+id_cat+ this.extensionFichero(file.getOriginalFilename());
	    
	    //ruta = "C:\\" + file.getOriginalFilename();
	    
	    //guardar en el fichero
	    try {
			Files.copy(file.getInputStream(), Paths.get(ruta));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error al subir una imagen de categoría al servidor Error: "+e);
			e.printStackTrace();
		}
	    
	    return rutaRetorno;
	} 
	
	/**
	 * Funcion que obtiene la extension de un fichero a partir de su nombre
	 * Para los ficheros que tienen la extension de 3 caractreres con substring de -4 se obtendra . + extension
	 * Si la extension es de más de 3 caracteres no se obtendra el . por eso se hace el substring con los 5 ultimos
	 * @param nombreFichero nombre del fichero del que se quiere obtener la extensión
	 * @return extension del fichero indicado como parámetro
	 */
	public String extensionFichero(String nombrefichero) {
		String extension = nombrefichero.substring(nombrefichero.length() - 4);
		// si la extension es ej: '.png' con los 4 últimos caracteres del nombre tenemos la extension completa
		if(extension.contains(".")) {
			return extension;
		}
		// si la extension es ej: '.jpeg' con los 5 últimos caracteres del nombre tenemos la extension completa
		else {
			extension = nombrefichero.substring(nombrefichero.length() - 5);
			return extension;
		}
	}
}
