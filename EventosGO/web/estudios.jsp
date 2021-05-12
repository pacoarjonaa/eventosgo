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
        %>
    </head>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Sección con la tabla de los estudios -->
        <section class="container rounded shadow-sm py-3 align-middle">
            <header class="container">
                <div class="d-flex">
                    <div class="me-auto">
                        <h1 class="display-1">Estudios Estad&iacute;sticos</h1>
                    </div>
                    <div class="mt-auto">
                        <a class="btn" href="crearEstudio.jsp" role="button">
                            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-plus-circle" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                            <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                            </svg>
                        </a>
                    </div>
                </div>
            </header>
            <article>
                <table class="table table-responsive-md table-hover table-sm fs-6 text-center" title="Lista de estudios" id="tabla">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Titulo</th>
                            <th scope="col">Resultado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                            int i;
                            for (i = (pagina - 1) * 10; i < estudios.size() && i < (pagina * 10); i++) {
                        %>
                        <tr>
                            <th scope="row"><%= (i + 1)%></th>
                            <td><%= estudios.get(i).getTitulo()%></td>
                            <td><%= estudios.get(i).getResultado()%></td>
                        </tr>
                        <%
                            }
                            for (i = i; i < 10 * pagina; i++) {
                        %>
                        <tr>
                            <th scope="row"><%= (i + 1)%></th>
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
                    %>
                    <li class="page-item <%= (pagina > 1) ? "" : "disabled"%>">

                        <a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina - 1%>">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <%
                        if (pagina == totalPaginas && totalPaginas > 2) {
                    %>
                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina - 2%>"><%= pagina - 2%></a></li>
                        <%
                            }
                        %>
                        <%
                            if (pagina > 1) {
                        %>

                    <li class="page-item"><a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina - 1%>"><%= pagina - 1%></a></li>
                        <%
                            }
                        %>


                    <li class="page-item active"><a class="page-link active" href="ServletEstudioCargar?paginaActual=<%= pagina%>"><%= pagina%></a></li>
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
                    <li class="page-item <%= (pagina < totalPaginas) ? "" : "disabled"%>">
                        <a class="page-link" href="ServletEstudioCargar?paginaActual=<%= pagina + 1%>">
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
