<%-- 
    Document   : adminCrudUsuario
    Created on : Apr 26, 2021, 12:25:17 PM
    Author     : pacoa
--%>

<%@page import="eventosgowebapp.entity.Usuario"%>
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
            Usuario usuario = (Usuario) request.getAttribute("usuario");            
        %>

    </head>
    <body>
        
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <!-- Aqui ya mostrariamos todos los datos del usuario seleccionado, donde tambien podremos modificarlos o borrarlos -->


        <div class="card-body">
            <blockquote class="blockquote">
                <p>Perfil del usuario</p>
            </blockquote>
            <figcaption class="blockquote-footer">
                Aqui podra editar la información que desee acerca del usuario
            </figcaption>

        </div>


        <div class="card" style="width: 18rem;">
            <div class="card-header">
                Datos
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><%= usuario.getNombre()%></li>
                <li class="list-group-item"><%= usuario.getCorreo()%></li>
                <li class="list-group-item"><%= usuario.getContrasena()%></li>
                <li class="list-group-item"><%= usuario.getRol()%></li>
            </ul>
        </div>

        <div>
            <form action="ServletAdminUsuarioCargar">
                <input type="submit" value="Volver"/>
            </form>

            <form action="ServletAdminCrudUsuarioCrearEditar">
                <input type ="hidden" name = "id" value = "<%=usuario.getId()%>" />
                <input type="submit" value="Modificar"/>
            </form>

            <form method = "get" action="ServletAdminCrudUsuarioBorrar">
                <input type ="hidden" name = "id" value = "<%=usuario.getId()%>" />
                <input type="submit" value="Borrar"/>
            </form>
        </div>


    </body>
</html>
