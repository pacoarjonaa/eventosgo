<%-- 
    Document   : estudios
    Created on : 18-abr-2021, 23:06:32
    Author     : juanm
--%>

<%@page import="java.util.List"%>
<%@page import="eventosgowebapp.entity.Estudio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estudios Estad&iacute;sticos</title>
        
        <%-- Boostrap  --%>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
        
        <%
            List<Estudio> estudios = (List) request.getAttribute("listaEstudios");
        %>
    </head>
    <body>
        <h1 class="display-1">Estudios Estad&iacute;sticos</h1>
        
        <table class="table table-responsive-md table-hover table-sm" title="Lista de estudios">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Titulo</th>
                    <th scope="col">Resultado</th>
                </tr>
            </thead>
            <tbody>
                <%
                for(int i = 0; i<estudios.size();i++) {
                %>
                    <tr>
                        <th scope="row"><%= (i+1) %></th>
                        <td><%= estudios.get(i).getTitulo() %></td>
                        <td><%= estudios.get(i).getResultado() %></td>
                    </tr>
                <%
                }
                %>
            </tbody>
            
        </table>
        
    </body>
</html>
