<%-- 
    Document   : adminCrudFormularioUsuario
    Created on : Apr 28, 2021, 1:08:02 PM
    Author     : pacoa
--%>

<%@page import="eventosgowebapp.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos del usuario</title>
        
         <!--        Boostrap -->
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        
        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        
        
    </head>
        <%
        Usuario usuario = (Usuario)request.getAttribute("usuario");
        String strNombre = "", strEmail = "", strPassword = "", strId = "", strRol = "";
        int rol = 0;
        
        if (usuario != null) {
           strNombre = usuario.getNombre();
           strEmail = usuario.getCorreo();
           strPassword = usuario.getContrasena();
           strId = usuario.getId().toString();
           rol = usuario.getRol();
           
           if(rol > 0){
               strRol = rol+"";
           }
        }

    %>    
   <body>        
        
       <!-- Navbar de navegación -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary fs-5 text" style="margin-bottom: 20px">
            <div class="container-fluid">
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
                <a class="navbar-brand fs-2" href="#">EventosGO</a>
              <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                  <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="#">Inicio</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="ServletEventosCargar">Eventos</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="ServletAdminUsuarioCargar">Usuarios</a>
<!--             Deshabilitar enlaces del navbar       <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>-->
                  </li>
                 
                </ul>
                
                  <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                      <input type="search" class="form-control form-control-dark" placeholder="Search...">
                  </form>
                  
                  <button class="btn btn-outline-light m-4" type="buttom">Perfil</button>
                  <button class="btn btn-outline-light" type="buttom">Cerrar Sesion</button>
                 
              </div>
            </div>
        </nav>
        <!-- END Navbar de paginación de los estudios -->
        
        <div>
        <h1>Datos del usuario</h1>
        <form action = "ServletAdminCrudUsuarioGuardar">
            <table>
                <input type="hidden" name="id" value="<%= strId %>" />
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" maxlength="30" size="30" value="<%= strNombre %>" /></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="text" name="email"  maxlength="40" size="40" value="<%= strEmail %>" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" name="password" maxlength="30" size="30"  value="<%= strPassword %>"/> 
                </tr>
                 <tr>
                   <td>Rol</td>
                    <td><input type="text" name="rol"  maxlength="40" size="40" value="<%= rol %>" /></td>
                </tr>
                <tr>            
                    <td colspan="2">
                       <input type="submit" value="Guardar"/>
                    </td>
                </tr>
            </table>
        </form>
        </div>
    </body>
</html>

