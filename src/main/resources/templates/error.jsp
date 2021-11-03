<!DOCTYPE html>
<html>
  <%@ include file="js/piezas/head.jsp" %>
  <body>
    <div class="page-holder">
      <%@ include file="js/piezas/header.jsp" %>

          <div class="container-fluid">
          
            <div class="alert alert-danger" role="alert">
            	<p><strong>Error:</strong> No tienes permiso para acceder a esta página</p>
            	<hr>
			  <img src="./img/error.jpeg" alt="Error imagen"/>
			</div>
                 
          </div>

          <%@ include file="js/piezas/footer.jsp" %>
      	  <%@ include file="js/piezas/importScripts.jsp" %>
        </div>
    </body>      
</html>