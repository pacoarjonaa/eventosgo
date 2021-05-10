<%-- 
    Document   : crearEvento
    Created on : Apr 28, 2021, 6:54:40 PM
    Author     : x Cristhian x
--%>

<%@page import="eventosgowebapp.entity.Usuario"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="eventosgowebapp.entity.EventoEtiqueta"%>
<%@page import="eventosgowebapp.dao.EtiquetaFacade"%>
<%@page import="eventosgowebapp.entity.Etiqueta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="eventosgowebapp.entity.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nuevo Evento</title>

        <!--        Boostrap -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" >

    </head>
    <%
        String strError = (String) request.getAttribute("error");
        if (strError == null) {
            strError = "";
        }

        Usuario creador = (Usuario) request.getSession().getAttribute("usuario");
        int rol = creador.getRol();
    %>

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

        <section class="container rounded shadow-sm w3-padding">
            <header class="container">
                <h1>Datos del evento</h1>
            </header>

            <form method='POST' action="ServletEventoCrear">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            T&iacute;tulo:
                        </div>
                        <div class="col-8">
                            <input type="text" name="titulo" />
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Descripci&oacute;n:
                        </div>
                        <div class="col-8">
                            <textarea name="descripcion" rows="4" cols="20"></textarea>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Fecha del evento:
                        </div>
                        <div class="col-8">
                            <input type="date" name="fechaEvento" />
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Fecha de máxima para comprar entradas:
                        </div>
                        <div class="col-8">
                            <input type="date" name="fechaEntradas" />
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Coste de la entrada:
                        </div>
                        <div class="col-8">
                            <input id="coste" type="number" name="coste" step="1.00" />  &euro;
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Aforo del evento:
                        </div>
                        <div class="col-8">
                            <input type="number" name="aforo" />
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Nº m&aacute;ximo de entradas por usuario:
                        </div>
                        <div class="col-8">
                            <input type="number" name="entradas" />
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Etiquetas:
                        </div>
                        <div class="col-8">
                            <textarea name="etiquetas"  ></textarea>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            <input type="submit" value="Crear evento">
                        </div>
                    </div>
                </div>

            </form>


        </section>



    </body>
</html>
