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
			 ArrayList<Usuario> usuariosLista = UsuarioDAO.seleccionarUsuarios();
             pageContext.setAttribute("usuariosLista", usuariosLista);
		 %>	

            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Usuarios</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.html">Administracion</a></li>
                            <li class="breadcrumb-item active">Usuarios</li>
                        </ol>
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
                                <th scope="col">id_rol</th>
                                <th scope="col">Email</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Apellidos</th>
                                <th scope="col">Direccion</th>
                                <th scope="col">TelÃ©fono</th>
                                <th scope="col">DNI</th>
                                <th scope="col">Acciones</th>
                              </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${pageScope.usuariosLista}" var="usuario" >
	                              <tr>
	                                <th scope="row"><c:out value="${usuario.getCodigo()}"></c:out></th>
	                                <th ><c:out value="${usuario.getId_rol()}"></c:out></th>
	                                <th ><c:out value="${usuario.getEmail()}"></c:out></th>
	                                <th ><c:out value="${usuario.getNombre()}"></c:out></th>
	                                <th ><c:out value="${usuario.getApellido1()}"></c:out> <c:out value="${usuario.getApellido2()}" ></c:out></th>
	                                <th ><c:out value="${usuario.getDireccion()}"></c:out>, <c:out value="${usuario.getProvincia()}"></c:out>,  <c:out value="${usuario.getLocalidad()}"></c:out></th>
	                                <th ><c:out value="${usuario.getTelefono()}"></c:out></th>
	                                <th ><c:out value="${usuario.getDni()}"></c:out></th>
	                                <td>
	                                    <a class="btn btn-danger btn-sm"><i class="fas fa-trash-alt"></i> Eliminar</a>
	                                    <a class="btn btn-primary btn-sm"><i class="fas fa-pen"></i> Modificar</a>
	                                </td>
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
