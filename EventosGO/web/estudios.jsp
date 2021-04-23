<%-- 
    Document   : estudios
    Created on : 18-abr-2021, 23:06:32
    Author     : juanm
--%>

<%@page import="java.util.List"%>
<%@page import="eventosgowebapp.entity.Estudio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estudios Estad&iacute;sticos</title>
        
        <!--        Boostrap -->
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        
        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        
        <%
            List<Estudio> estudios = (List) request.getAttribute("listaEstudios");
        %>
    </head>
    <body>
        
        <!-- Navbar de navegación -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary fs-4 text" style="margin-bottom: 20px">
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
                    <a class="nav-link" href="#">Eventos</a>
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="#">Estudios</a>
<!--             Deshabilitar enlaces del navbar       <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>-->
                  </li>
                  <li class="nav-item">
                      <a class="nav-link" href="#">Chat</a>
                  </li>
                </ul>
                  <button class="btn btn-outline-light m-4" type="buttom">Iniciar Sesion</button>
                  <button class="btn btn-outline-light" type="buttom">Registrarse</button>
              </div>
            </div>
        </nav>
        <!-- END Navbar de paginación de los estudios -->
        
        <!-- Sección con la tabla de los estudios -->
        <section class="container rounded shadow-sm w3-padding">
            <header class="container">
                <h1 class="display-1">Estudios Estad&iacute;sticos</h1>
            </header>
            <article>
                <table class="table table-responsive-md table-hover table-sm fs-5 text-center" title="Lista de estudios">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Titulo</th>
                            <th scope="col">Resultado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        int i; 
                        for(i = 0; i<estudios.size();i++) {
                        %>
                            <tr>
                                <th scope="row"><%= (i+1) %></th>
                                <td><%= estudios.get(i).getTitulo() %></td>
                                <td><%= estudios.get(i).getResultado() %></td>
                            </tr>
                        <%
                        }
                        for(int j=10;j>i;j--) {
                            %>
                            <tr>
                                <th scope="row"><%= (12-j) %></th>
                                <td>*</td>
                                <td>*</td>
                            </tr>
                            <%
                        }
                        %>
                    </tbody>
                </table>
            </article>
            <!-- Navbar de paginación de los estudios-->
            <nav aria-label="Paginación de estudios">
                <ul class="pagination justify-content-center">
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
            <!-- END Navbar de paginación de los estudios-->
        </section>
        <!-- END Sección con la tabla de los estudios -->
    </body>
</html>