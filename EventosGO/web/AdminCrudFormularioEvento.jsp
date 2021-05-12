<%-- 
    Document   : AdminCrudFormularioEvento
    Created on : 11-may-2021, 21:19:37
    Author     : pacoa
--%>

<%@page import="eventosgowebapp.entity.EventoEtiqueta"%>
<%@page import="eventosgowebapp.entity.Evento"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <% 
            Evento evento = (Evento)request.getAttribute("evento"); 
            String strError = (String) request.getAttribute("error");
            
        if (strError == null) {
            strError = "";
        }

        Usuario creador = (Usuario) request.getSession().getAttribute("usuario");
        %>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MOdificar el Evento <%= evento.getTitulo() %></title>

        <!--        Boostrap -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" >

    </head>
    <body>     
        
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <section class="container rounded shadow-sm w3-padding">
            <header class="container">
                <h1>Datos del evento a modificar</h1>
            </header>

            <form method='POST' action="ServletEventoGuardar">
                <div class="container">
                    <div class="row">
                        <div class="col-8">
                            <input type="text" name="id" value="<%= evento.getId() %>" hidden/>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col-8">
                            <input type="text" name="idCreador" value="<%= creador.getId() %>" hidden/>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            T&iacute;tulo:
                        </div>
                        <div class="col-8">
                            <input type="text" name="titulo" placeholder="<%= evento.getTitulo() %>" />
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Descripci&oacute;n:
                        </div>
                        <div class="col-8">
                            <textarea name="descripcion" rows="4" cols="20" placeholder="<%= evento.getDescripcion() %>"></textarea>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Fecha del evento:
                        </div>
                        <div class="col-8">
                            <input type="date" name="fechaEvento" placeholder="<%= new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechaEvento()) %>"/>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Fecha de máxima para comprar entradas:
                        </div>
                        <div class="col-8">
                            <input type="date" name="fechaEntradas" placeholder="<%= new SimpleDateFormat("dd/MM/yyyy").format(evento.getFechaFinReservas()) %>"/>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Coste de la entrada:
                        </div>
                        <div class="col-8">
                            <input id="coste" type="number" name="coste" step="1.00" placeholder="<%= evento.getCoste() %>"/>  &euro;
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Aforo del evento:
                        </div>
                        <div class="col-8">
                            <input type="number" name="aforo" placeholder="<%= evento.getAforo() %>"/>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Nº m&aacute;ximo de entradas por usuario:
                        </div>
                        <div class="col-8">
                            <input type="number" name="entradas" placeholder="<%= evento.getMaximoEntradasUsuario() %>"/>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            Etiquetas:
                        </div>
                        <div class="col-8">
                            <textarea name="etiquetas"  placeholder="..."></textarea>
                        </div> 
                    </div>
                    <div class="row">
                        <div class="col">
                            <input type="submit" value="Modificar evento">
                        </div>
                    </div>
                </div>

            </form>


        </section>



    </body>
</html>

