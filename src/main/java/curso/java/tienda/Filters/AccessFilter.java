package curso.java.tienda.Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import curso.java.tienda.models.entities.Usuario;

/**
 * Filtro de acceso para controlar que solo los usuarios con permisos accedan a las partes que les corresponde de la web
 * Su uso se aplia dentro de algunas clases y con mostrar o no opciones de menu o botones en las vistas
 * @version 1, 15/11/2021
 * @author Miguel Pérez León
 */
@Component
@Order(1)
public class AccessFilter implements Filter {

	private static Logger logger = LogManager.getLogger(AccessFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession sesion = req.getSession();
		String path = req.getRequestURI();

		if (sesion == null || sesion.getAttribute("usuario") == null) {
			// si no hay usuario registrado o si no hay una sesion iniciada

			// si intenta acceder a la parte de paginas de administrador se deniega la
			// entrada y se manda al login
			if (path.contains("/admin")) {
				logger.error("Usuario ha intentado acceder a una pagina sin permisos " + req.getRequestURI());
				res.sendRedirect("/login");
			}
			// si intenta acceder a las partes publicas de la web se permite el acceso
			// si se intenta acceder a un recurso de static
			else if (path.contains("/vendor") ||path.contains("/img") || path.contains("/css")  || path.contains("/js")) {
				chain.doFilter(request, response);
			}
			// si se pide un recurso que no esta ni en la carpta administrador ni es una
			// pagina jsp se manda la peticion
			else {
				if (path.contains("/login") || path.contains("/cesta") || path.contains("error") || path.contains("/home") || path.contains("/producto") || path.contains("/tienda") || path.contains("/about") || path.equals("/")) {
					chain.doFilter(request, response);	
				}
				// si intenta acceder a las partes de la web para usuarios registrados se manda
				// al usuario a la pagina de login
				else {
					logger.warn("Usuario ha intentado acceder a un recurso de usuario registrado sin estar registrado "
							+ req.getRequestURI());
					res.sendRedirect("/login");
				}
			}

		} else {
			Usuario usuario = (Usuario) sesion.getAttribute("usuario");

			// administrador tiene acceso a todas las partes
			if (usuario.getId_rol() == 1 || usuario.getId_rol() == 2) {
				chain.doFilter(request, response);
			} else if (usuario.getId_rol() == 3) {
				// si intenta acceder a la parte de paginas de administrador se deniega la
				// entrada
				if (path.contains("/admin")) {
					logger.error("Usuario ha intentado acceder a una pagina sin permisos " + req.getRequestURI());
					res.sendRedirect("/error");
				}
				// el resto de peticiones se permiten
				else {
					chain.doFilter(request, response);
				}
			}
		}

	}
}
