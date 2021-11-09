package curso.java.tienda.controllers;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.tienda.service.DetallePedidoService;
import curso.java.tienda.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
public class PedidosUsuario {

	
	@Autowired
	private PedidoService peds;
	
	@Autowired
	private DetallePedidoService dps;
	
	private static Logger logger = LogManager.getLogger(PedidosUsuario.class);
	
	
}
