package curso.java.tienda.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import curso.java.tienda.models.entities.Producto;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Component
public class ProductosUtil {
	
	private static Logger logger = LogManager.getLogger(ProductosUtil.class);
	
	/**
	 * Funcion que recorre los ficheros de la carpeta donde se guardan los 
	 * ficheros de exportado de los datos de la tabla productos
	 * obteniendo la ruta de todos y metiendolos en una lista de cadenas
	 * 
	 * @return arrayList con las direcciones de los ficheros con los datos de los productos
	 * */
	public ArrayList<String> ficherosExcellDatos(){
		ArrayList<String> ficheros = new ArrayList<String>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("./ficherosExcell/"))) {
		    for (Path file: stream) {
		        ficheros.add(file.getFileName().toString());
		    }
		    
		    return ficheros;
		} catch (IOException | DirectoryIteratorException e) {
			logger.error("Error al acceder a la carpeta de ficheros excell Error: "+e);
			return null;
		}
	}

	
	public void escribirExcell(List<Producto> productos) {
		LocalDateTime fecha = LocalDateTime.now(); // Create a date object
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
	    
		File fichero = new File("./ficherosExcell/"+fecha.format(formatoFecha)+"Productos.xls");
		 
		 try{
        	WritableWorkbook w = Workbook.createWorkbook(fichero);
 
        	
        	//Creo la hoja del documento con el nombre de productos
        	WritableSheet sheet = w.createSheet("Productos", 0);
        	
        	Integer i =0;
        	//sheet.addCell( new jxl.write.Label(0, i, "id") );
            sheet.addCell( new jxl.write.Label(0, i, "id_categoria") );
            sheet.addCell( new jxl.write.Label(1, i, "nombre") );
            sheet.addCell( new jxl.write.Label(2, i, "descripcion") );
            sheet.addCell( new jxl.write.Label(3, i, "precio") );
            sheet.addCell( new jxl.write.Label(4, i, "stock") );
            sheet.addCell( new jxl.write.Label(5, i, "fecha_alta") );
            sheet.addCell( new jxl.write.Label(6, i, "fecha_baja") );
            sheet.addCell( new jxl.write.Label(7, i, "impuesto") );
            sheet.addCell( new jxl.write.Label(8, i, "imagen") );
            sheet.addCell( new jxl.write.Label(9, i, "precioImpuesto") );
            i++;
            
        	//Hago un recorrido por la lista de productos escribiendo en la linea i los datos del producto p
        	for(Producto p: productos) {
	            //sheet.addCell( new jxl.write.Label(0, i, p.getId().toString()) );
	            sheet.addCell( new jxl.write.Label(0, i, p.getId_categoria().toString()) );
	            sheet.addCell( new jxl.write.Label(1, i, p.getNombre()) );
	            sheet.addCell( new jxl.write.Label(2, i, p.getDescripcion()) );
	            sheet.addCell( new jxl.write.Label(3, i, p.getPrecio().toString()) );
	            sheet.addCell( new jxl.write.Label(4, i, p.getStock().toString()) );
	            sheet.addCell( new jxl.write.Label(5, i, p.getFecha_alta()) );
	            sheet.addCell( new jxl.write.Label(6, i, p.getFecha_baja()) );
	            sheet.addCell( new jxl.write.Label(7, i, p.getImpuesto().toString()) );
	            sheet.addCell( new jxl.write.Label(8, i, p.getImagen()) );
	            sheet.addCell( new jxl.write.Label(9, i, p.getPrecioImpuesto().toString()) );
	            i++;
        	}
        	
        	// escribo los datos en el fichero y lo cierro
            w.write();
            w.close();
            logger.info("Fichero con la copia de los productos creado");
        	
        } catch (Exception e) {
        	logger.error("Error al escribir datos en el fichero de exportación de productos Error: "+e);
        	e.printStackTrace();
        }
	}	
	
	public ArrayList<Producto> leerExcell(String path){

		File fichero = new File("./ficherosExcell/"+path);
		
		 try{
			 Workbook w = Workbook.getWorkbook(fichero);
	        	
	        //Se lee la primera hoja de la excel
	        Sheet sheet = w.getSheet(0);
	        
	        //recorro todas las posiciones del fichero leyendo datos
	        ArrayList<Producto> productos = new ArrayList<Producto>();
	        
	        for (int f=1; f<sheet.getRows(); f++) {

	        	Producto p = new Producto();
	        	
	    		/*for(int c=0;c<sheet.getColumns();c++) {
	    			contenido += sheet.getCell(c,f).getContents() + "\t";// + sheet.getCell(c, f).getContents();
	    		}*/
	    		
	    		p.setId_categoria(Integer.parseInt( sheet.getCell(0,f).getContents()));
	            p.setNombre(sheet.getCell(1,f).getContents());
	            p.setDescripcion(sheet.getCell(2,f).getContents());
	            p.setPrecio(Double.parseDouble(sheet.getCell(3,f).getContents()));
	            p.setStock(Integer.parseInt(sheet.getCell(4,f).getContents()));
	            p.setFecha_alta(sheet.getCell(5,f).getContents());
	            p.setFecha_baja(sheet.getCell(6,f).getContents());
	            p.setImpuesto(Float.parseFloat( sheet.getCell(7,f).getContents()));
	            p.setImagen(sheet.getCell(8,f).getContents());
	            p.setPrecioImpuesto(Double.parseDouble( sheet.getCell(9,f).getContents()));
	            
	            productos.add(p);
	    		
	    		
	    	}
	        
	        logger.info("Datos del fichero: ["+path+"] leidos correctamente");
	        return productos;
			 
		 } catch (Exception e) {
	        	logger.error("Error al leer datos en el fichero: ["+path+"] Error: "+e);
	        	e.printStackTrace();
	        	return null;
	    }
		
	}
}