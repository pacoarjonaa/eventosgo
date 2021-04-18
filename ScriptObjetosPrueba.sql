-- SCRIPT DE DATOS PARA PRUEBAS

-- Usuarios
INSERT INTO USUARIO (CORREO, CONTRASENA, NOMBRE, ROL) VALUES ('pablo@gmail.com', '12345', 'Pablo', 3); -- Usuario tipo Analista
INSERT INTO USUARIO (CORREO, CONTRASENA, NOMBRE, ROL) VALUES ('juanma@gmail.com', '12345', 'Juanma', 3); -- Usuario tipo Analista
INSERT INTO USUARIO (CORREO, CONTRASENA, NOMBRE, ROL) VALUES ('ruben@gmail.com', '12345', 'Ruben', 3); -- Usuario tipo Analista

-- Estudios
INSERT INTO ESTUDIO (ID_ANALISTA, TITULO, RESULTADO) VALUES (1, 'Prueba metodos estudio', 'Positivo'); -- Borrar