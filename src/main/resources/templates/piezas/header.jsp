<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	Stack<String> pilaAtras = (Stack<String>) session.getAttribute("pilaAtras"); 
	//meto en la pila de direcciones la direccion actual sempre que no sean la paginas de login y crear un usuario
	if(!request.getRequestURI().endsWith("login.jsp") && !request.getRequestURI().endsWith("signin.jsp") ){
		if(pilaAtras == null){
			pilaAtras = new Stack<String>();
		}
		pilaAtras.push(request.getRequestURI());
		session.setAttribute("pilaAtras", pilaAtras);
	}	
%>


      <!-- navbar-->
      <header class="header bg-header-page">
        <div class="container-fluid px-0 px-lg-3">
          <nav class="navbar navbar-expand-lg navbar-light py-3 px-lg-0"><a class="navbar-brand" href="index.jsp"><span class="font-weight-bold text-dark font-syne fs-2">Bikë<span class="text-primary">Meisters</span></span></a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto font-syne">
                <li class="nav-item">
                  <!-- Link--><a class="nav-link active" href="index.jsp">&#5125; Home</a>
                </li>
                <li class="nav-item">
                  <!-- Link--><a class="nav-link" href="shop.html">Ofertas</a>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" id="pagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categorias</a>
                  <div class="dropdown-menu mt-3" aria-labelledby="pagesDropdown">
                    <a class="dropdown-item border-0 transition-link" href="index.jsp">Homepage</a>
                    <a class="dropdown-item border-0 transition-link" href="shop.html">Category</a>
                    <a class="dropdown-item border-0 transition-link" href="detail.html">Product detail</a>
                    <a class="dropdown-item border-0 transition-link" href="cart.html">Shopping cart</a>
                    <a class="dropdown-item border-0 transition-link" href="checkout.html">Checkout</a>
                  </div>
                </li>
              </ul>
              <ul class="navbar-nav ms-auto flex-row-reverse">               
                <li class="nav-item"><a class="nav-link" href="cart.html"><i class="fas fa-dolly-flatbed mx-1 text-gray"></i>Cart<small class="text-gray"> (2)</small></a></li>
                <li class="nav-item"><a class="nav-link" href="#"><i class="far fa-heart mx-1"></i><small class="text-gray"></small></a></li>
                	
                <c:set var="usuarioNombre" scope="session" value="${sessionScope.usuarioNombre}"/>
                
                <c:if test="${usuarioNombre != null}">
                	<li class="nav-item dropdown">
                		<a class="nav-link dropdown-toggle" id="pagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                			<i class='fas fa-user-alt mx-1 text-gray'></i><c:out value="${sessionScope.usuarioNombre}"></c:out>
                		</a>
                		 <div class="dropdown-menu mt-3" aria-labelledby="pagesDropdown">
                		 	<a class="dropdown-item border-0 transition-link" href='#'>Opciones de usuario</a><hr>
                		 	<a class="dropdown-item border-0 transition-link text-danger" href='/ProyectoTienda/Login?logout=true'>Cerrar sesión</a>
                		 </div>	
                	</li>   
						
				</c:if>
 
				<c:if test="${usuarioNombre == null}">
                	<li class='nav-item'><a class='nav-link' href='login.jsp'><i class='fas fa-user-alt mx-1 text-gray'></i>Login</a></li>
                </c:if>
              </ul>
            </div>
          </nav>
        </div>
      </header>
      
      <div class="container-fluid">
      		<a href="Atras?atras='true'" class="link-primary">Atras</a><hr>
      </div>