<%-- 
    Document   : adminPrincipal
    Created on : 23-abr-2021, 19:43:43
    Author     : Kiko BM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
        
        <!--        Boostrap -->
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        
        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        
        
        
    </head>
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
                    <a class="nav-link active" aria-current="page" href="#">Inicio</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="ServletEventosCargar">Eventos</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="#">Usuarios</a>
<!--             Deshabilitar enlaces del navbar       <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>-->
                  </li>
                 
                </ul>
                  <button class="btn btn-outline-light m-4" type="buttom">Iniciar Sesion</button>
                  <button class="btn btn-outline-light" type="buttom">Registrarse</button>
              </div>
            </div>
        </nav>
        <!-- END Navbar de paginación de los estudios -->
        
        <figure class="text-center">
            <blockquote class="blockquote">
              <p>¡Bienvenido administrador!</p>
            </blockquote>
            <figcaption class="blockquote-footer">
              Nombre del admin
            </figcaption>
        </figure>
        
        
    </body>
</html>
