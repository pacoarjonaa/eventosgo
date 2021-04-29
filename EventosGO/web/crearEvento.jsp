<%-- 
    Document   : crearEvento
    Created on : Apr 28, 2021, 6:54:40 PM
    Author     : x Cristhian x
--%>

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
            if(strError == null) strError ="";
    %>
    <body>     
        <h1>Datos del evento</h1>
        <form method='POST' action="ServletEventoCrear">
            <%= strError %><br/>
            <table>
                <input type="hidden" name="id" value=""/>
                <tr>
                    <td>T&iacute;tulo:</td>
                    <td><input type="text" name="titulo"/></td>
                </tr>
                <tr>
                    <td>Descripci&oacute;n:</td>
                    <td><input type="text" name="descripcion" size="300"/></td>
                </tr>
                <tr>
                    <td>Fecha del evento:</td>
                    <td><input type="date" name="fechaEvento"/></td>
                </tr>
                <tr>
                    <td>Fecha de máxima para comprar entradas:</td>
                    <td><input type="date" name="fechaEntradas"/></td>
                </tr>
                <tr>
                    <td>Coste de la entrada:</td>
                    <td><input id="coste" type="number" name="coste" step="0.01"/>  &euro;</td>               
                </tr>
                <tr>
                    <td>Aforo del evento</td>
                    <td><input type="number" name="aforo"</td>
                </tr>
                <tr>
                    <td>N&uacute;mero m&aacute;ximo posible de entradas a adquirir por el usuario</td>
                    <td><input type="number" name="entradas"</td>
                </tr>
                <tr>
                    <td>Etiquetas:</td>
                    <td><textarea name="etiquetas"></textarea></td>
                </tr>
                <tr></tr>
                <tr>
                    <td colspan="10"><input type="submit" value="Guardar"></td>
                </tr>
                
            </table>
        </form>
    </body>
</html>
