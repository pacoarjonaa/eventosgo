<%--
    Document   : estudios
    Created on : 18-abr-2021, 23:06:32
    Author     : juanm
--%>

<%@page import="eventosgowebapp.entity.Usuario"%>
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
            int pagina = (Integer) request.getAttribute("pagina");
            int rol;
            if (request.getSession().getAttribute("usuario") != null) {
                rol = ((Usuario) request.getSession().getAttribute("usuario")).getRol();
            } else {
                rol = -1;
            }

        %>
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
        <!-- END Navbar de paginación de los estudios -->

        <!-- Sección con la tabla de los estudios -->
        <section class="container rounded shadow-sm w3-padding">
            <header class="container">
                <h1 class="display-1">Estudios Estad&iacute;sticos</h1>
                <a class="btn" href="crearEstudio.jsp" role="button"><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                    <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                    </svg></a>
            </header>
            <article>
                <table class="table table-responsive-md table-hover table-sm fs-6 text-center" title="Lista de estudios">
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
                            for (i = (pagina - 1) * 10; i < estudios.size(); i++) {
                        %>
                        <tr>
                            <th scope="row"><%= (i + 1)%></th>
                            <td><%= estudios.get(i).getTitulo()%></td>
                            <td><%= estudios.get(i).getResultado()%></td>
                        </tr>
                        <%
                            }
                            for (int j = 10; j > i; j--) {
                        %>
                        <tr>
                            <th scope="row"><%= (12 - j)%></th>
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
                    <%
                        int totalPaginas = (Integer) (estudios.size() / 10) + 1;
                        if (pagina > totalPaginas) {
                    %>
                    <li class="page-item">

                        <a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina - 1%>" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <%
                        }
                    %>
                    <%
                        if (pagina > 1) {
                    %>

                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina - 1%>"><%= pagina - 1%></a></li>
                        <%
                            }
                            if (pagina == totalPaginas && totalPaginas > 2) {
                        %>
                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina - 2%>"><%= pagina - 2%></a></li>
                        <%
                            }
                        %>

                    <li class="page-item"><a class="page-link active" href="ServletEstudioCargar?paginaActual=<%= pagina%>"><%= pagina%></a></li>
                        <%
                            if (pagina < totalPaginas) {
                        %>
                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina + 1%>"><%= pagina + 1%></a></li>
                        <%
                            if (pagina == 1 && totalPaginas > 2) {
                        %>
                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina + 2%>"><%= pagina + 2%></a></li>
                        <%
                                }
                            }
                        %>
                        <%
                            if (pagina < totalPaginas) {
                        %>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <%
                        }
                    %>

                </ul>
            </nav>
            <!-- END Navbar de paginación de los estudios-->
        </section>
        <!-- END Sección con la tabla de los estudios -->
    </body>
</html>
