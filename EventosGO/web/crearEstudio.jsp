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
        %>
    </head>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->

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
