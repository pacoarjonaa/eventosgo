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

        <!-- Botones redondos -->
        <style>
            .btn-circle {
                width: 45px;
                height: 45px;
                line-height: 45px;
                text-align: center;
                padding: 0;
                border-radius: 50%;
            }

            .btn-circle i {
                position: relative;
                top: -1px;
            }

            .btn-circle-sm {
                width: 35px;
                height: 35px;
                line-height: 35px;
                font-size: 0.9rem;
            }
        </style>
        <!-- END Botones redondos -->
        

        <%
            List<Estudio> estudios = (List) request.getAttribute("listaEstudios");
            int pagina = (Integer) request.getAttribute("pagina");
            List<Integer> resultados = (List) request.getAttribute("resultadoEstudios");
        %>
    </head>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Sección con la tabla de los estudios -->
        <div class="container">
            <div class="d-flex">
                <div class="me-auto">
                    <h1 class="display-1">Estudios Estad&iacute;sticos</h1>
                </div>
                <div class="mt-auto">

                    <a class="btn btn-outline-dark btn-circle btn-circle-sm" href="crearEstudio.jsp" role="button">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-plus-circle mb-1" viewBox="0 0 16 16">
                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                        </svg>
                    </a>
                </div>
            </div>
        </div>
        <section class="container shadow-lg p-3 mb-5 bg-body rounded py-3 align-middle">
            <article>
                <table class="table table-responsive-md table-hover table-sm fs-6 text-center align-middle" id="tabla">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Titulo</th>
                            <th scope="col">Resultado</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                            int i;
                            for (i = (pagina - 1) * 10; i < estudios.size() && i < (pagina * 10); i++) {
                        %>
                        <tr>
                            <th scope="row"><%= (i + 1)%></th>
                            <td><%= estudios.get(i).getTitulo()%></td>
                            <td><%= resultados.get(i)%></td>
                            <td>
                                <a class="btn btn-outline-info" href="ServletEstudioVer?estudio=<%= estudios.get(i).getId()%>" role="button">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
                                    <path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
                                    <path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/>
                                    </svg>
                                </a>
                                <a class="btn btn-outline-success" href="ServletEstudioEditar?estudio=<%= estudios.get(i).getId()%>" role="button">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                    <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                    <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                    </svg>
                                </a>
                                <a class="btn btn-outline-danger" href="ServletEstudioEliminar?estudio=<%= estudios.get(i).getId()%>" role="button">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                    <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                    <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                    </svg>
                                </a>
                            </td>
                        </tr>
                        <%
                            }
                            for (i = i; i < 10 * pagina; i++) {
                        %>
                        <tr>
                            <th scope="row"><%= (i + 1)%></th>
                            <td>*</td>
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
