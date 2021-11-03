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
                        <h1 class="mt-4">Nueva categoria</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.jsp">Administracion</a></li>
                            <li class="breadcrumb-item"><a href="categorias.jsp">Categorias</a></li>
                             <li class="breadcrumb-item active">Nueva Categoria</li>
                        </ol>
                        <!--<div class="card mb-4">
                            <div class="card-body">
                                <p class="mb-0">
                                   Esto queda bien para poner los errores
                                </p>
                            </div>
                        </div>-->

						<form method="post" action="/ProyectoTienda/categoriaNuevo">
						  <div class="form-group">
						    <label for="nombre">Nombre de la categoria</label>
						    <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Indica el nombre de la nueva categoria" required>
						    <label for="descripcion">Descripción de la categoria</label>
						    <input type="text" class="form-control" id="descripcion" name="descripcion" placeholder="Indica la descripción de la categoria" required>
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
