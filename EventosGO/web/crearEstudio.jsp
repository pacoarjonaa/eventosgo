<%-- 
    Document   : crearEstudio
    Created on : 29-abr-2021, 14:11:36
    Author     : juanm
--%>

<%@page import="eventosgowebapp.entity.Usuario"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Estudio</title>
        <!--        Boostrap -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <%
            SimpleDateFormat anio = new SimpleDateFormat("YYYY");
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

        <!-- Formulario de crear un Estudio -->

        <form class="container shadow-lg p-3 mb-5 bg-body rounded" action="ServletGuardarEstudio">
            <div class="alert alert-info mb-3">Todos los estudios estadísticos que eres capaz de crear serán vinculados a los usuarios que han asistido a eventos, restringido a los filtros que t&uacute; como analista has considerado de estudio.</div>
            <div class="mb-3">
                <label for="title" class="form-label">T&iacute;tulo</label>
                <input type="text" class="form-control" id="titulo"/>
            </div>
            <div class="mb-3">
                <label for="year" class="form-label">A&ntilde;o evento</label>
                <input type="number" class="form-control" name="anio" min="2000" max="<%= anio.format(new Date())%>"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Edad m&iacute;nima</label>
                <input type="range" class="form-range" name="edad_min" onchange="updateTextInput(this.value, 'emin');" value="0"/>
                <input type="text" readonly class="form-control-plaintext text-center" value="" id="emin"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Edad m&aacute;xima</label>
                <input type="range" class="form-range" name="edad_max" onchange="updateTextInput(this.value, 'emax');" value="100"/>
                <input type="text" readonly class="form-control-plaintext text-center" value="" id="emax"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Sexo</label>
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" value="0" name="masculino">
                    <label class="form-check-label" for="masculino">Masculino</label>
                </div>
                <div class="form-check form-switch">
                    <input class="form-check-input" type="checkbox" value="1" name="femenino">
                    <label class="form-check-label" for="femenino">Femenino</label>
                </div>
                <div class="mb-3 form-check form-switch">
                    <input class="form-check-input" type="checkbox" value="2" name="otro">
                    <label class="form-check-label" for="otro">Otro</label>
                </div>
            </div>

            <div class="mb-3">
                <label for="city" class="form-label">Ciudad</label>
                <input type="text" class="form-control" name="ciudad"/>
            </div>
            <div class="pt-3">
                <button type="submit" class="btn btn-primary btn-lg">Siguiente</button>
                <button type="reset" class="btn btn-secondary btn-sm">Limpiar</button>
                <a class="btn btn-danger btn-sm" href="ServletEstudioCargar">Cancelar</a>
            </div>
        </form>
        <script>
            function updateTextInput(val, inp) {
                document.getElementById(inp).value = val;
            }
        </script>

        <!-- END Formulario de crear un Estudio -->

    </body>
</html>
