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

        <!-- Iconos de Boostrap -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

        <%
            List<Conversacion> lista = (List) request.getAttribute("listaConversaciones");
            String error = (request.getAttribute("error") != null) ? request.getAttribute("error").toString() : "";
        %>

    </head>
    <body>
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <section class="container shadow-lg p-3 mb-5 bg-body rounded py-3 align-middle">
            <%                if (!error.isEmpty()) {
            %>
            <div class="alert alert-warning d-flex align-items-center" role="alert">
                <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Warning:">
                <symbol id="exclamation-triangle-fill" fill="currentColor" viewBox="0 0 16 16">
                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </symbol>
                </svg>
                <div>
                    No hay teleoperadores disponibles. Disculpe las molestias.
                </div>
            </div>
            <%
                }
            %>
            <header class="container">
                <h1 class="display-1">Mis Conversaciones</h1>
                <hr class="my-4">
                <%                    if (rol != 2) {
                %>
                <div class="container">
                    <div class="alert alert-info d-flex align-items-center" role="alert">
                        <i class="bi bi-info-circle-fill" style="font-size: 1.7em"></i>
                        <div class="ms-3">
                            <div>
                                Aqu&iacute; puede solicitar una conversaci&oacute;n con un teleoperador.
                            </div>
                        </div>
                    </div>
                    <form action="ServletAsignarTeleoperador" class="row g-3 justify-content-center">
                        <div class="col-auto">
                            <label class="form-label">Asunto</label>
                        </div>
                        <div class="col-auto">
                            <input type="text" class="form-control" name="asunto">
                        </div>
                        <div class="col-auto">
                            <input type="submit" class="btn btn-primary mb-3" value="Solicitar">
                        </div>
                    </form>
                </div>
                <%
                } else {
                %>
                <div class="mt-auto align-middle">
                    <a class="btn btn-outline-dark btn-circle btn-circle-sm m-1" href="" role="button">
                        <i class="bi bi-plus">Consultar chats</i>
                    </a>
                </div>
                <%
                    }
                %>
            </header>
        </section>
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
                    <small><%= (rol == 2) ? c.getIdUsuario().getCorreo() : c.getIdTeleoperador().getNombre()%></small>
                </a>
                <%
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
                    <small><%= (rol == 2) ? c.getIdUsuario().getCorreo() : c.getIdTeleoperador().getNombre()%></small>
                </a>
                <%
                    }
                %>
            </div>
        </article>
        <!-- END Sección conversacion -->

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>

    </body>
</html>
