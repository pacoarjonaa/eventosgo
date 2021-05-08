<%-- 
    Document   : confirmarEstudio
    Created on : 07-may-2021, 21:58:53
    Author     : juanm
--%>

<%@page import="eventosgowebapp.entity.Estudio"%>
<%@page import="java.util.List"%>
<%@page import="eventosgowebapp.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Usuario> lista;
            Estudio e;
        %>
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
                            <a class="nav-link" aria-current="page" href="paginaInicioWeb.jsp">Inicio</a>
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

                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                        <input type="search" class="form-control form-control-dark" placeholder="Search...">
                    </form>
                    <button class="btn btn-outline-light m-4" type="buttom">Perfil</button>
                    <button class="btn btn-outline-light" type="buttom">Cerrar Sesion</button>
                </div>
            </div>
        </nav>
        <!-- END Navbar de paginación de los estudios -->

        <!-- Sección con la tabla de usuarios que cumplen el filtro para estudio -->
        <section class="container">
            <header class="container">
                <h1 class="display-1"><%= e.getTitulo() %></h1>
            </header>
            <article>
                <table class="table table-responsive-md table-hover table-sm fs-6 text-center" title="Lista de estudios">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Edad</th>
                            <th scope="col">Ciudad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row"></th>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </article>
        </section>        
        <!-- END Sección con la tabla de usuarios que cumplen el filtro para estudio -->
    </body>
</html>
