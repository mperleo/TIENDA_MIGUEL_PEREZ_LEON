<!DOCTYPE html>
<html>
  <%@ include file="js/piezas/head.jsp" %>
  <body>
    <div class="page-holder">
      <%@ include file="js/piezas/header.jsp" %>
         
          <div class="container-fluid">
            <div class="card mb-4" id="forms">    
                <div class="card-header font-syne">Registrate</div>
                <div class="card-body">
                  <form class="form-inline">
                    <fieldset>
                      <legend>Indica tus datos personales</legend>
                      <hr class="my-2">
                      <div class="form-group">
                        <label class="text-small text-uppercase"for="exampleInputEmail">Nombre</label>
                        <input class="form-control" id="exampleInputEmail" type="text" placeholder="Indica tu correo">
                      </div>
                      <div class="form-group">
                        <div class=row>
                          <div class="col-md-6">
                            <label class="text-small text-uppercase"for="exampleInputEmail">Primer apellido</label>
                            <input class="form-control" id="exampleInputEmail" type="text" placeholder="Indica tu correo">
                          </div>
                          <div class="col-md-6">
                            <label class="text-small text-uppercase"for="exampleInputEmail">Segundo apellido</label>
                            <input class="form-control" id="exampleInputEmail" type="text" placeholder="Indica tu correo">
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <label class="text-small text-uppercase"for="exampleInputEmail">DNI</label>
                        <input class="form-control" id="exampleInputEmail" type="email" placeholder="Indica tu correo">
                      </div>
                    </fieldset>
                    <br>
                    <fieldset>
                      <legend>Indica tu direccion</legend>
                      <hr class="my-2">
                      <div class="form-group">
                        <label class="text-small text-uppercase"for="exampleInputEmail">Direccion</label>
                        <input class="form-control" id="exampleInputEmail" type="text" placeholder="Indica tu correo">
                      </div>
                      <div class="form-group">
                        <div class=row>
                          <div class="col-md-6">
                            <label class="text-small text-uppercase"class="text-small text-uppercase" for="exampleInputEmail">Provincia</label>
                            <input class="form-control" id="exampleInputEmail" type="text" placeholder="Indica tu correo">
                          </div>
                          <div class="col-md-6">
                            <label class="text-small text-uppercase"class="text-small text-uppercase" for="exampleInputEmail">Localidad</label>
                            <input class="form-control" id="exampleInputEmail" type="text" placeholder="Indica tu correo">
                          </div>
                        </div>
                      </div>
                    </fieldset>
                    <br>
                    <fieldset>
                      <legend>Indica tu contraseña</legend>
                      <hr class="my-2">
                      <div class="form-group">
                        <label class="text-small text-uppercase"for="exampleInputPassword">Contraseña</label>
                        <input class="form-control" id="exampleInputPassword" type="password" placeholder="Indica tu contraseÃ±a">
                      </div>
                      <div class="form-group">
                      	<div class="input-group flex-column flex-sm-row mb-3">
                          <label class="text-small text-uppercase"for="exampleInputPassword">Repite la contraseña</label>
                          <input class="form-control" id="exampleInputPassword2" type="password2" placeholder="Indica tu contraseÃ±a">
                          <button class="btn btn-dark btn-block" id="button-addon2" type="submit">Subscribe</button>
                        </div>
                      </div>
                    </fieldset>
                    <hr class="my-4">  
                    <fieldset>
                        <div class="form-check form-group">
                          <input class="form-check-input" id="checkbox-1" type="checkbox">
                          <label class="text-small text-uppercase"class="form-check-label" for="checkbox-1">Acepto los terminos y condiciones</label>
                        </div>
                        <button class="btn btn-primary font-syne" type="submit">Crear nuevo usuario</button>
                      </fieldset>
                  </form>   
                </div> 
            </div>        
          </div>

          <%@ include file="js/piezas/footer.jsp" %>
      	  <%@ include file="js/piezas/importScripts.jsp" %>
        </div>
    </body>      
</html>