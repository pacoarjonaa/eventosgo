/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eventosgowebapp.dao.UsuarioFacade;
import eventosgowebapp.entity.Usuario;
import javax.ejb.EJB;

/**
 *
 * @author x Cristhian x
 */
@WebServlet(name = "ServletUsuarioCrear", urlPatterns = {"/ServletUsuarioCrear"})
public class ServletUsuarioCrear extends HttpServlet {
    
    @EJB
    private UsuarioFacade userFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id, correo, password, nombre, sexo, ciudad;
        Integer edad, rol=3;
        Usuario nuevoUsuario;
        
        id = request.getParameter("id");
        correo = request.getParameter("correo");
        password = request.getParameter("pass1");
        nombre = request.getParameter("nombre");
        sexo = request.getParameter("Sexo");
        ciudad = request.getParameter("ciudad");
        edad = new Integer(request.getParameter("edad"));
        
        if (id == null || id.isEmpty()){
            nuevoUsuario = new Usuario();       // Crea un usuario nuevo
            nuevoUsuario.setRol(rol);
        } else{
            nuevoUsuario = this.userFacade.find(new Integer(id));      // Editar el cliente seleccionado
        }
        
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContrasena(password);
    
        // nuevoUsuario.setSexo(sexo);
        // nuevoUsuario.setCiudad(ciudad)
        // nuevoUsuario.setEdad(edad);
        
        if(id==null || id.isEmpty()){
            this.userFacade.create(nuevoUsuario);
        } else{
            this.userFacade.edit(nuevoUsuario);
        }
        
        response.sendRedirect("ServletAdminUsuarioCargar");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
