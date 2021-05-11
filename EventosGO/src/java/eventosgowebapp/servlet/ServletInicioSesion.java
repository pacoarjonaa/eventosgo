/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.UsuarioFacade;
import eventosgowebapp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author x Cristhian x
 */
@WebServlet(name = "ServletInicioSesion", urlPatterns = {"/ServletInicioSesion"})
public class ServletInicioSesion extends HttpServlet {
    
    @EJB
    private UsuarioFacade usuarioFacade;
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
        
        String strError = "", strTo = "inicioSesion.jsp";
        String email = (String) request.getParameter("correo");
        String pass = (String) request.getParameter("password");
        RequestDispatcher rd = request.getRequestDispatcher(strTo);
        
        Usuario user = this.usuarioFacade.findByCorreo(email);
        
        if(user == null){
            strError = "ERROR: Usuario con email introducido no registrado";
            request.setAttribute("error", strError);
            rd.forward(request, response);
        } else if(!user.getContrasena().equals(pass)){
            strError = "ERROR: La contraseña introducida es incorrecta";
            request.setAttribute("error", strError);
            rd.forward(request, response);
        } else{
            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", user);
            
            
            switch (user.getRol()){
                case 0:         // Admin
                    rd = request.getRequestDispatcher("adminPrincipal.jsp");
                    rd.forward(request, response);
                    break;
                    
                case 1:         // Creador
                    response.sendRedirect("ServletCreadorPrincipal");
                    break;
                
                case 2:         // Teleoperador
                    rd = request.getRequestDispatcher("paginaInicioWeb.jsp");
                    rd.forward(request, response);
                    break;
                   
                case 3:         // Analista
                    rd = request.getRequestDispatcher("ServletEstudioCargar");
                    request.setAttribute("listaEstudios", user.getEstudioList());
                    rd.forward(request, response);
                    break;
                
                case 4:         // Usuario evento
                    rd = request.getRequestDispatcher("paginaInicioWeb.jsp");
                    rd.forward(request, response);
                    break;
            }
        }
        
        
                
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
