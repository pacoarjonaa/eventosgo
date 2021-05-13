<%-- 
    Document   : misConversaciones
    Created on : 13-may-2021, 18:57:02
    Author     : juanm
--%>

<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatterBuilder"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="eventosgowebapp.entity.Mensaje"%>
<%@page import="java.util.List"%>
<%@page import="eventosgowebapp.entity.Conversacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Conversaciones</title>

        <!--        Boostrap -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

        <%
            List<Conversacion> lista = (List) request.getAttribute("listaConversaciones");
        %>

    </head>
    <body>
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <section class="container shadow-lg p-3 mb-5 bg-body rounded py-3 align-middle">
            <header class="container">
                <h1 class="display-1">Mis Conversaciones</h1>
            </header>
            <article class="container">
                <div class="list-group">
                    <%                        for (Conversacion c : lista) {
                            int cntSinLeer = 0;
                            if (c.getMensajeList().size() > 0) {
                                Mensaje m = c.getMensajeList().get(c.getMensajeList().size() - 1);
                                for (Mensaje m1 : c.getMensajeList()) {
                                    if (m1.getVisto() == 0 && !m1.getIdUsuario().equals((Usuario) request.getAttribute("user"))) {
                                        cntSinLeer++;
                                    }
                                }
                            }
                    %>
                    <a href="" class="list-group-item list-group-item-action">
                        <div class="d-flex w-100 justify-content-between">
                            <h3 class="mb-1"><%= c.getAsunto()%></h3>
                            <%
                                if (cntSinLeer > 0) {
                            %>
                            <span class="badge bg-primary fs-5"> <%= cntSinLeer%></span>
                            <%
                                }
                            %>

                        </div>
                        <p><%= (c.getMensajeList().size() > 0) ? c.getMensajeList().get(c.getMensajeList().size() - 1).getTexto() : ""%></p>
                        <small><%= c.getIdUsuario().getCorreo()%></small>
                    </a>
                    <%
                        }
                    %>
                </div>
            </article>
        </section>
        <!-- END Sección con la tabla de los estudios -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>

    </body>
</html>
