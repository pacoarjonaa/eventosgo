/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.UsuarioFacade;
import eventosgowebapp.entity.Usuario;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author pacoa
 */
@WebServlet(name = "ServletAdminCrudUsuarioGuardar", urlPatterns = {"/ServletAdminCrudUsuarioGuardar"})
public class ServletAdminCrudUsuarioGuardar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @EJB
    private UsuarioFacade UsuarioFacade;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        // Aqui lo mismo, no hace falta autenticacion porque solo se puede una vez que ya haya iniciado sesion el admin
        
            String  nombre, email, password, id;
            int rol;
            
            Usuario usuario;
           
            id =  request.getParameter("id");
            nombre = request.getParameter("nombre");
            email = request.getParameter("email");      
            password = request.getParameter("password");
            rol = Integer.parseInt(request.getParameter("rol"));
            
            if (id == null || id.isEmpty()) { // Crear nuevo usuario
                usuario = new Usuario();            
            } else { // Editar usuario existente
                usuario = this.UsuarioFacade.find(new Integer(id));
            }
            usuario.setNombre(nombre);
            usuario.setCorreo(email);
            usuario.setContrasena(password);
            usuario.setRol(rol);

            if (id == null || id.isEmpty()) { // Crear nuevo cliente        
                this.UsuarioFacade.create(usuario);
            } else { // Editar cliente existente
                this.UsuarioFacade.edit(usuario);
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
