<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, DAO.*, DTO.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <%@ include file="js/piezas/head.jsp" %>
    <body>
         <%@ include file="js/piezas/headerYnav.jsp" %>
			
		 <%
			 ArrayList<Categoria> categorias = CategoriaDAO.seleccionarCategorias();
		 	 pageContext.setAttribute("categorias", categorias);
		 %>	
            <div id="layoutSidenav_content">
                <main>
                	
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Categorias</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.jsp">Administracion</a></li>
                            <li class="breadcrumb-item active">Categorias</li>
                            
                        </ol>
                        
                        <a class="btn btn-primary " href="categoriaNuevo.jsp" role="button">Nueva categoria</a>
                        
                        <!--<div class="card mb-4">
                            <div class="card-body">
                                <p class="mb-0">
                                   Esto queda bien para poner los errores
                                </p>
                            </div>
                        </div>-->

                        <table class="table table-bordered table-striped table-hover">
                            <thead class="table-dark">
                              <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Descripcion</th>
                                <th scope="col">Acciones</th>
                              </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${pageScope.categorias}" var="cat">
	                              <tr>
	                                <th scope="row"><c:out value="${cat.getId()}"></c:out></th>
	                                <th><c:out value="${cat.getNombre()}"></c:out></th>
	                                 <th><c:out value="${cat.getDescripcion()}"></c:out></th>
	                                <th>
	                                    <a class="btn btn-danger btn-sm"><i class="fas fa-trash-alt"></i> Eliminar</a>
	                                    <a class="btn btn-primary btn-sm"><i class="fas fa-pen"></i> Modificar</a>
	                                </th>
	                              </tr>
                                </c:forEach>
                              
                            </tbody>
                        </table>

                        <div class="d-flex justify-content-end">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                                </ul>
                            </nav>
                        </div>
                        
                    </div>
                </main>
                
                 <%@ include file="js/piezas/footerYscripts.jsp" %>
                 
            </div>
        </div>  <!-- cierre del contenido que se importa del archivo js/piezas/headerYnac.jsp -->

    </body>
</html>
