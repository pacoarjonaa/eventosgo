<%-- 
    Document   : chatConversacion
    Created on : 16-may-2021, 12:51:22
    Author     : juanm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%@include file="cabecera.jsp" %> <!-- Introduce la cabecera -->



        <section class="container">
            <header class="mb-5">
                <h1 class="display-1">Asunto</h1>
                <h2 class="display-3">Intermediario</h2>
                <hr class="my-3">
            </header>
            <article class="container shadow p-3 mb-2 bg-body rounded">
                <!-- Mensaje de Usuario -->
                <div class="row ">
                    <div class="col-auto justify-content-start">
                        <div class="alert-dark text-start mb-4 px-5 py-2 me-5">
                            <p>Hola soy un mensaje</p>
                            <span class="">Hora</span>
                        </div>
                    </div>
                </div>
                <!-- END Mensaje -->

                <!-- Mensaje de Suyo -->
                <div class="row justify-content-end">
                    <div class="col-auto">
                        <div class="alert-success text-end px-5 py-2 ms-5 mb-4">
                            <p class="text-break">Hola soy un mensaje tuyo</p>
                            <span class="">Hora</span>
                        </div>
                    </div>
                </div>
                <!-- END Mensaje -->
            </article>
            <div class="container">
                <form action="ServletMensajeEnviar" class="">
                    <input type="text" hidden="" value="" name="id"/>
                    <input type="text" class="form-control" placeholder="Enviar un mensaje" name="mensaje"/>
                    <button class="btn btn-primary">
                        <i class="bi bi-arrow-right-square-fill"></i>
                    </button>
                </form>
            </div>
        </section>
    </div>

</body>
</html>
