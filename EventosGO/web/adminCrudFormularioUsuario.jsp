<%-- 
    Document   : adminCrudFormularioUsuario
    Created on : Apr 28, 2021, 1:08:02 PM
    Author     : pacoa
--%>

<%@page import="eventosgowebapp.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Datos del usuario</title>

        <!--        Boostrap -->

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


    </head>
    <%
        Usuario usuario = (Usuario) request.getAttribute("usuario");
        String strNombre = "", strEmail = "", strPassword = "", strId = "", strRol = "";
        int rol2 = -1;

        if (usuario != null) {
            strNombre = usuario.getNombre();
            strEmail = usuario.getCorreo();
            strPassword = usuario.getContrasena();
            strId = usuario.getId().toString();
            rol2 = usuario.getRol();

            if (rol2 > 0) {
                strRol = Integer.toString(rol2);
            }
        }

    %>    
    <body>        

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

        <div>
            <h1>Datos del usuario</h1>
            <form action = "ServletAdminCrudUsuarioGuardar">
                <table>
                    <input type="hidden" name="id" value="<%= strId%>" />
                    <tr>
                        <td>Nombre:</td>
                        <td><input type="text" name="nombre" maxlength="30" size="30" value="<%= strNombre%>" /></td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email"  maxlength="40" size="40" value="<%= strEmail%>" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" name="password" maxlength="30" size="30"  value="<%= strPassword%>"/> 
                    </tr>
                    <tr>
                        <td>Rol</td>
                        <td><input type="text" name="rol"  maxlength="40" size="40" value="<%= rol2%>" /></td>
                    </tr>
                    <tr>            
                        <td colspan="2">
                            <input type="submit" value="Guardar"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>

