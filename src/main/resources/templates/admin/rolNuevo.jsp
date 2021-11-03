<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, DAO.*, DTO.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <%@ include file="js/piezas/head.jsp" %>
    <body>
         <%@ include file="js/piezas/headerYnav.jsp" %>
			
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Nuevo rol</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.jsp">Administracion</a></li>
                            <li class="breadcrumb-item"><a href="roles.jsp">Roles</a></li>
                             <li class="breadcrumb-item active">Nuevo rol</li>
                        </ol>
                        <!--<div class="card mb-4">
                            <div class="card-body">
                                <p class="mb-0">
                                   Esto queda bien para poner los errores
                                </p>
                            </div>
                        </div>-->

						<form method="post" action="/ProyectoTienda/rolNuevo">
						  <div class="form-group">
						    <label for="rol">Nombre del nuevo rol</label>
						    <input type="text" class="form-control" id="rol" name="rol" placeholder="Indica el nombre del rol nuevo" required>
						  </div>
						  
						  <button type="submit" class="btn btn-primary my-2">Guardar</button>
						  
						</form>
                        
                    </div>
                </main>
                
                 <%@ include file="js/piezas/footerYscripts.jsp" %>
                 
            </div>
        </div>  <!-- cierre del contenido que se importa del archivo js/piezas/headerYnac.jsp -->

    </body>
</html>
