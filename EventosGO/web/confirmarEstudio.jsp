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
            int rol;
            if (request.getSession().getAttribute("usuario") != null) {
                rol = ((Usuario) request.getSession().getAttribute("usuario")).getRol();
            } else {
                rol = -1;
            }
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
                        <%                            if (rol == 1) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="ServletCreadorPrincipal">Mis Eventos</a>
                        </li>
                        <%
                            }
                        %>
                        <%
                            if (rol == 3) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Estudios</a>
                        </li>
                        <%
                            }
                        %>
                        <%
                            if (rol == 2 || rol == 4 || rol == 1) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Chat</a>
                        </li>
                        <%
                            }
                        %>
                        <%
                            if (rol == 0) {
                        %>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Lista Usuarios</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Lista Eventos</a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                    <%
                        if (rol < 0) {
                    %>
                    <a class="btn btn-outline-light m-4" href="#" role="button">Registrarse</a>
                    <a class="btn btn-outline-light" href="inicioSesion.jsp" role="button">Iniciar Sesi&oacute;n</a>
                    <%
                    } else {
                    %>
                    <a class="btn btn-outline-light m-4" href="#" role="button">Perfil</a>
                    <a class="btn btn-outline-light" href="ServletCerrarSesion" role="button">Cerrar Sesi&oacute;n</a>
                    <%
                        }
                    %>

                </div>
            </div>
        </nav>
        <!-- END Navbar -->

        <!-- Sección con la tabla de usuarios que cumplen el filtro para estudio -->
        <section class="container">
            <header class="container">
                <h1 class="display-1"><%= e.getTitulo()%></h1>
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
