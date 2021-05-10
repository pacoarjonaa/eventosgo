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
<%@page import="eventosgowebapp.entity.Evento"%>"
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
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    </head>
    <%
        String strError = (String) request.getAttribute("error");
        if (strError == null) {
            strError = "";
        }
        Evento evento = (Evento) request.getAttribute("evento");
        String strId = "", titulo = "", descripcion = "", strFechaEvento = "", strFechaEntradas = "", strCoste = "", strAforo = "", strEntradasPorUsuario = "";
        List<Etiqueta> listaEtiquetas = (List<Etiqueta>) request.getAttribute("listaEtiquetas");
        if (evento != null) {
            strId = evento.getId().toString();
            titulo = evento.getTitulo();
            descripcion = evento.getDescripcion();
            strFechaEvento = evento.getFechaEvento().toString();
            strFechaEntradas = evento.getFechaFinReservas().toString();
            strCoste = Double.toString(evento.getCoste());
            strEntradasPorUsuario = Integer.toString(evento.getMaximoEntradasUsuario());
        }
    %>

    <body>     
        
        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <h1>Datos del evento</h1>
        <form method='POST' action="ServletEventoCrear">
            <%= strError%><br/>
            <table>
                <input type="hidden" name="id" value="<%= strId%>"/>
                <tr>
                    <td>T&iacute;tulo:</td>
                    <td><input type="text" name="titulo" value="<%= titulo%>"/></td>
                </tr>
                <tr>
                    <td>Descripci&oacute;n:</td>
                    <td><input type="text" name="descripcion" size="300" value="<%= descripcion%>"/></td>
                </tr>
                <tr>
                    <td>Fecha del evento:</td>
                    <td><input type="date" name="fechaEvento" value="<%= strFechaEvento%>"/></td>
                </tr>
                <tr>
                    <td>Fecha de máxima para comprar entradas:</td>
                    <td><input type="date" name="fechaEntradas" value="<%= strFechaEntradas%>"/></td>
                </tr>
                <tr>
                    <td>Coste de la entrada:</td>
                    <td><input id="coste" type="number" name="coste" step="0.01" value="<%= strCoste%>"/>  &euro;</td>               
                </tr>
                <tr>
                    <td>Aforo del evento</td>
                    <td><input type="number" name="aforo" value="<%= strAforo%>"/></td>
                </tr>
                <tr>
                    <td>N&uacute;mero m&aacute;ximo posible de entradas a adquirir por el usuario</td>
                    <td><input type="number" name="entradas" value="<%= strEntradasPorUsuario%>"/></td>
                </tr>
                <tr>
                    <td>Etiquetas:</td>
                    <td><textarea name="etiquetas"  value="<%= listaEtiquetas%>"></textarea></td>
                </tr>
                <tr></tr>
                <tr>
                    <td colspan="10"><input type="submit" value="Guardar"></td>
                </tr>

            </table>
        </form>
    </body>
</html>
