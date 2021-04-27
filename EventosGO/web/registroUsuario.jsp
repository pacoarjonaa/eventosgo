<%-- 
    Document   : registroUsuario
    Created on : 26-abr-2021, 11:17:48
    Author     : alumno
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Usuario</title>
        
        <!--        Boostrap -->
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        
        <!--        W3 CSS -->
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        
        
    </head>

    <body>     
        <h1>Datos del usuario</h1>
        <form method='POST' action="ServletUsuarioCrear">
            <table>
                <input type="hidden" name="id" value=""/>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre"/></td>
                </tr>
                <tr>
                    <td>Apellidos:</td>
                    <td><input type="text" name="apellidos"/></td>
                </tr>
                <tr>
                    <td>Correo:</td>
                    <td><input type="text" name="correo"/></td>
                </tr>
                <tr>
                    <td>Contraseña</td>
                    <td><input type="password" name="pass1"/></td>
                </tr>
                <tr>
                    <td>Confirmar contraseña</td>
                    <td><input type="password" name="pass2"</td>
                </tr>
                <tr></tr>
                <tr>
                    <td>Sexo:</td>
                    <td><select name="Sexo">
                            <option value="">
                            <option value="hombre">Hombre</option>
                            <option value="mujer">Mujer</option>
                            <option value="otro">Otro</option>
                        </select></td>
                </tr>
                <tr>
                    <td>Ciudad de residencia:</td>
                    <td><input type="text" name="ciudad"/></td>
                </tr>
                <tr>
                    <td>Edad:</td>
                    <td><input type="text" name="edad" maxlength="2" size="2"/></td>
                </tr>
                <tr></tr>
                <tr>
                    <td colspan="10"><input type="submit" value="Guardar"></td>
                </tr>
                
            </table>
        </form>
         
    </body>
</html>
