<%-- 
    Document   : confirmarEstudio
    Created on : 07-may-2021, 21:58:53
    Author     : juanm
--%>

<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="eventosgowebapp.entity.UsuarioEvento"%>
<%@page import="eventosgowebapp.entity.Estudio"%>
<%@page import="java.util.List"%>
<%@page import="eventosgowebapp.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Estudio</title>
        <!--        Boostrap -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
        <%
            List<UsuarioEvento> lista = (List) request.getAttribute("lista");
            Estudio e = (Estudio) request.getAttribute("estudio");
        %>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Indicador de progreso -->
        <div class="container py-4">
            <div class="position-relative m-4">
                <div class="progress" style="height: 3px;">
                    <div class="progress-bar" role="progressbar" style="width: 100%;" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <button type="button" class="position-absolute top-0 start-0 translate-middle btn btn-lg btn-primary rounded-pill" style="width: 3rem; height:3rem;">1</button>
                <button type="button" class="position-absolute top-0 start-100 translate-middle btn btn-lg btn-primary rounded-pill" style="width: 3rem; height:3rem;">2</button>
            </div>
        </div>
        <!-- END Indicador de progreso -->

        <!-- Sección con la tabla de usuarios que cumplen el filtro para estudio -->
        <section class="container">
            <header class="container">
                <h1 class="display-1"><%= e.getTitulo()%></h1>
                <p class="fs-4">Total de usuarios: <%= lista.size()%></p>
                <a href="ServletAlmacenarEstudio?estudio=<%= e%>" class="btn btn-primary" role="button">Crear Estudio</a>
                <a href="ServletEstudioCargar" class="btn btn-secondary btn-sm" role="button">Cancelar</a>
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
                        <%
                            int i = 1;
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD/MM/YYYY");
                            SimpleDateFormat sdt = new SimpleDateFormat("DD/MM/YYYY");
                            LocalDate ld = LocalDate.now();
                            for (UsuarioEvento u : lista) {
                        %>
                        <tr>
                            <th scope="row">i</th>
                            <td><%= u.getUsuario().getNombre()%></td>
                            <%
                                LocalDate fecha_nac = LocalDate.parse(sdt.format(u.getFechaNacimiento()), dtf);
                                int edad = Period.between(fecha_nac, ld).getYears();
                            %>
                            <td><%= edad%></td>
                            <td><%= u.getCiudad()%></td>
                        </tr>
                        <%
                            }
                        %>

                    </tbody>
                </table>
            </article>
        </section>        
        <!-- END Sección con la tabla de usuarios que cumplen el filtro para estudio -->
    </body>
</html>
