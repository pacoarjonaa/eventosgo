/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.ConversacionFacade;
import eventosgowebapp.dao.MensajeFacade;
import eventosgowebapp.dao.UsuarioFacade;
import eventosgowebapp.entity.Conversacion;
import eventosgowebapp.entity.Mensaje;
import eventosgowebapp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "ServletMensajeEnviar", urlPatterns = {"/ServletMensajeEnviar"})
public class ServletMensajeEnviar extends HttpServlet {

    @EJB
    private ConversacionFacade conversacionFacade;
    
    @EJB
    private MensajeFacade mensajeFacade;
    
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
        String texto = new String(request.getParameter("mensaje").getBytes("ISO-8859-1"), "UTF-8");
        Conversacion conversacion = this.conversacionFacade.find(Integer.parseInt(request.getParameter("idConversacion")));
        Usuario usuario = this.usuarioFacade.find(Integer.parseInt(request.getParameter("idUsuario")));
        
        Mensaje mensaje = new Mensaje();
        mensaje.setFecha(new Date());
        mensaje.setHora(new Date());
        mensaje.setIdConversacion(conversacion);
        mensaje.setIdUsuario(usuario);
        mensaje.setTexto(texto);
        mensaje.setVisto(0);

        List<Mensaje> lista = conversacion.getMensajeList();
        lista.add(mensaje);
        conversacion.setMensajeList(lista);
        this.mensajeFacade.create(mensaje);
        this.conversacionFacade.edit(conversacion);
        
        response.sendRedirect("ServletMensajeCargar?idConversacion="+conversacion.getId());
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
