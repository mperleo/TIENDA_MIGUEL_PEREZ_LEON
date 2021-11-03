<!DOCTYPE html>
<html>
  <%@ include file="js/piezas/head.jsp" %>
  <body>
    <div class="page-holder">
      <%@ include file="js/piezas/header.jsp" %>

          <div class="container-fluid">
          
            <div class="card mb-4" id="forms">
                <div class="card-header font-syne">Inicia sesión</div>
                <div class="card-body">
                  <form action="/ProyectoTienda/Login" method="POST">
                    <fieldset>
                      <div class="form-group">
                        <label class="text-small text-uppercase"for="email">Correo electronico</label>
                        <input class="form-control" id="email" name="email" type="email" placeholder="Indica tu correo">
                      </div>
                      <div class="form-group">
                        <label class="text-small text-uppercase"for="pass">Contraseña</label>
                        <input class="form-control" id="email" name="pass" type="password" placeholder="Indica tu contraseÃ±a">
                      </div>
                      <div class="form-check form-group">
                        <input class="form-check-input" id="checkbox-1" type="checkbox">
                        <label class="text-small text-uppercase"class="form-check-label" for="checkbox-1">Recuerdame</label>
                      </div>
                      <button class="btn btn-primary font-syne" type="submit">Iniciar sesión</button>
                    </fieldset>
                  </form>
                </div>  
            </div>  

            <div class="card mb-4" id="forms">    
                <div class="card-header font-syne">No tienes un usuario, registrate</div>
                <div class="card-body">
                  <form class="form-inline" action="signin.html" method="post">               
                        <button class="btn btn-primary font-syne" type="submit">Crea tu usuario</button>
                  </form>   
                </div> 
            </div>   
                 
          </div>

          <%@ include file="js/piezas/footer.jsp" %>
      	  <%@ include file="js/piezas/importScripts.jsp" %>
        </div>
    </body>      
</html>