package curso.java.tienda.Tasks;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import curso.java.tienda.models.entities.Configuracion;
import curso.java.tienda.models.entities.Pedido;
import curso.java.tienda.service.ConfiguracionService;
import curso.java.tienda.service.PedidoService;

@Component
public class ProcesarPedidosTask {
	@Autowired
	private PedidoService ps;
	@Autowired
	private ConfiguracionService cs;
	
	private static Logger logger = LogManager.getLogger(ProcesarPedidosTask.class);
	
	@Scheduled(fixedRate = 60000, initialDelay = 60000)
	public void principal() {
		logger.info("INICIO DEL PROCESADO DE PEDIDOS AUTOMÁTICO");
		
		List<Pedido> pendientes = ps.getListaPedidosPorEstado("pendiente");
		HashMap<String, Configuracion> datos_factura = cs.getDatosFactura();
		
		if(pendientes!=null) {
			for(Pedido ped: pendientes) {
				// -------- MODIFICO LOS DATOS DEL PEDIDO Y GUARDO
				ped.setEstado("enviado");
				
				Integer nFactNuevo = Integer.parseInt(datos_factura.get("factura_num").getValor())  +1;
				// actualizo el número de la factura al hacer el cmaibio de estado y lo meto
				datos_factura.get("factura_num").setValor(nFactNuevo.toString());
				// guardo el numero de la factura al pedido
				ped.setNum_factura(nFactNuevo.toString());
				// guardo el pedido modificado en la bbdd, con el nuevo estado y el número de la factura
				ps.editPedido(ped);
				
				logger.info("PROCESADO DE PEDIDOS AUTOMÁTICO: pedido id:' "+ped.getId()+"' procesado");
			
			}
		}
		else {
			logger.info("PROCESADO DE PEDIDOS AUTOMÁTICO: no hay pedidos pendientes");
		}

		cs.editConfiguracion(datos_factura.get("factura_num"));
		logger.info("PROCESADO DE PEDIDOS AUTOMÁTICO: pedidos procesados");
	}
}
