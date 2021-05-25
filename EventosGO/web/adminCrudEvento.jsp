<%-- 
    Document   : adminCrudEvento
    Created on : 11-may-2021, 15:18:46
    Author     : pacoa
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="eventosgowebapp.entity.Evento"%>
<%@page import="java.util.List"%>
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

        <%
            Evento evento = (Evento) request.getAttribute("evento");            
        %>

    </head>
    <body>
        
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Aqui ya mostrariamos todos los datos del usuario seleccionado, donde tambien podremos modificarlos o borrarlos -->


        <div class="card-body">
            <blockquote class="blockquote">
                <p>Detalles del evento</p>
            </blockquote>
            <figcaption class="blockquote-footer">
                Aqui podra editar la información que desee acerca del evento
            </figcaption>

        </div>


        <div class="card" style="width: 18rem;">
            <div class="card-header">
                Datos del Evento
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Titulo: <%= evento.getTitulo() %></li>
                <li class="list-group-item">Descripcion: <%= evento.getDescripcion() %></li>
                <li class="list-group-item">Fecha: <%= new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechaEvento()) %></li>
                <li class="list-group-item">Fecha fin reservas: <%= new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechaFinReservas()) %></li>
                <li class="list-group-item">Aforo: <%= evento.getAforo()%></li>
                <li class="list-group-item">Coste: <%= evento.getCoste()%></li>
                <li class="list-group-item">Maximo entradas/usuario: <%= evento.getMaximoEntradasUsuario()%></li>
            </ul>
        </div>

        <div>
            <form action="ServletEventosCargar">
                <input type="submit" value="Volver"/>
            </form>

            <form action="ServletAdminCrudEventoEditar">
                <input type ="hidden" name = "id" value = "<%=evento.getId() %>" />
                <input type="submit" value="Modificar"/>
            </form>

            <form method = "get" action="ServletAdminCrudEventoBorrar">
                <input type ="hidden" name = "id" value = "<%=evento.getId()%>" />
                <input type="submit" value="Borrar"/>
            </form>
        </div>


    </body>
</html>