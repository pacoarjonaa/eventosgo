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
