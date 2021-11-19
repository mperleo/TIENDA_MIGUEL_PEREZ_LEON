package curso.java.tienda.utils;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import curso.java.tienda.models.entities.DetallePedido;
import curso.java.tienda.models.entities.Pedido;

@Component
public class PedidosUtil {

	/**
	 * Escribir pdf
	 * @param args
	 * @return 
	 */
	public String generarPdf(Pedido pedido, List<DetallePedido> lineas) {
		// TODO Auto-generated method stub
		LocalDateTime fecha = LocalDateTime.now(); // Create a date object
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		String nombreFichero = "./ficherosPdf/"+fecha.format(formatoFecha)+"-Pedido-"+pedido.getNum_factura()+".pdf";
	    try {      
	    	//Obtenemos la instancia del archivo a utilizar
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(nombreFichero));
	    	
		    //Para insertar cabeceras/pies en todas las p�ginas
	    	writer.setPageEvent(new PDFHeaderFooter());
	        
		    //Abrimos el documento para edici�n
		    documento.open();
		    
		    //PARRAFO para poner un margen con la cabecera
			Paragraph paragraph = new Paragraph();
			paragraph.add("\n\n");
	    	documento.add(paragraph);
	    	
	    	
	    	//TABLAS
		    
			//Instanciamos una tabla de X columnas
	    	PdfPTable tabla = null;
		    if(pedido.getCodigoDescuento() != null) {
		    	tabla = new PdfPTable(5); // con descuento
		    }
		    else {
		    	tabla = new PdfPTable(3); // sin descuento
		    }
		    
			PdfPCell cabecera = new PdfPCell();
			//Propiedades concretas de formato
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
			cabecera.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			cabecera.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
			cabecera.setPaddingBottom(3);
			cabecera.setPaddingTop(3);
			
			// meto los datos de la cabecera de la tabla
			cabecera.setPhrase(new Phrase("Número factura"));
		    tabla.addCell(cabecera);
		    cabecera.setPhrase(new Phrase("Método de pago"));
		    tabla.addCell(cabecera);
		    cabecera.setPhrase(new Phrase("Fecha"));
		    tabla.addCell(cabecera);
		    if(pedido.getCodigoDescuento() != null) {
			    cabecera.setPhrase(new Phrase("Código de descuento"));
			    tabla.addCell(cabecera);
			    cabecera.setPhrase(new Phrase("% Descuento"));
			    tabla.addCell(cabecera);
		    }    
		    
		    // meto los datos del pedido
		    tabla.addCell(pedido.getNum_factura());
		    tabla.addCell(pedido.getMetodo_pago());
		    tabla.addCell(pedido.getFecha());
		    if(pedido.getCodigoDescuento() != null) {
		    	tabla.addCell(pedido.getCodigoDescuento());
		    	tabla.addCell(pedido.getDescuento().toString());
		    }

		    documento.add(tabla);
		    
		    // detalles del pedido
		    //PARRAFO para poner un margen con la cabecera
			paragraph = new Paragraph();
			paragraph.add("\n");
	    	documento.add(paragraph);
	    	
		    tabla = new PdfPTable(6);

		    cabecera.setPhrase(new Phrase("Ref. producto"));
		    tabla.addCell(cabecera);
		    cabecera.setPhrase(new Phrase("Producto"));
		    tabla.addCell(cabecera);
		    cabecera.setPhrase(new Phrase("Unidades"));
		    tabla.addCell(cabecera);
		    cabecera.setPhrase(new Phrase("Precio unidad"));
		    tabla.addCell(cabecera);
		    cabecera.setPhrase(new Phrase("Impuesto"));
		    tabla.addCell(cabecera);
		    cabecera.setPhrase(new Phrase("Total"));
		    tabla.addCell(cabecera);
		    for(DetallePedido linea: lineas) {
		    	tabla.addCell(linea.getProducto().toString());
		    	tabla.addCell(linea.getProductoNombre());
		    	tabla.addCell(linea.getUnidades().toString());
		    	tabla.addCell(linea.getPrecioUnidad().toString()+" €");
		    	tabla.addCell(linea.getImpuesto().toString());
		    	tabla.addCell(linea.getTotal().toString()+" €");
		    }

		    documento.add(tabla);
		    
		    // total del pedido completo
		    tabla = new PdfPTable(2);
		    
		    cabecera.setPhrase(new Phrase("Total:"));
		    tabla.addCell(cabecera);
		    tabla.addCell(pedido.getTotal().toString()+" €");
		    
		    documento.add(tabla);
	    	
		    documento.close(); //Cerramos el documento
		    writer.close(); //Cerramos writer
		    
			return nombreFichero;
	    } catch (Exception ex) {
	    	ex.getMessage();
	    	return null;
	    }
	}
	
}
