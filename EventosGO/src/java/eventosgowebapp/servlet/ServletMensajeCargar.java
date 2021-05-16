/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.ConversacionFacade;
import eventosgowebapp.dao.MensajeFacade;
import eventosgowebapp.entity.Conversacion;
import eventosgowebapp.entity.Mensaje;
import eventosgowebapp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pablo
 */
@WebServlet(name = "ServletMensajeCargar", urlPatterns = {"/ServletMensajeCargar"})
public class ServletMensajeCargar extends HttpServlet {

    @EJB
    private ConversacionFacade conversacionFacade;

    @EJB
    private MensajeFacade mensajeFacade;

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
        Conversacion conversacion = this.conversacionFacade.find(new Integer(request.getParameter("idConversacion")));
        List<Mensaje> lista = conversacion.getMensajeList();
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");

//        FALLO GORDACO COMO VALIDAR LOS MENSAJES LEIDOS DEL CHAT
//        for (Mensaje m : conversacion.getMensajeList()) {
//            if (Objects.equals(conversacion.getIdUsuario().getId(), u.getId()) || Objects.equals(u.getId(), conversacion.getIdTeleoperador().getId())) {
//                if (!Objects.equals(u.getId(), m.getIdUsuario().getId())) {
//                    lista.remove(m);
//                    m.setVisto(1);
//                    this.mensajeFacade.edit(m);
//                    lista.add(m);
//                }
//            }
//        }
//        conversacion.setMensajeList(lista);
//        this.conversacionFacade.edit(conversacion);
        
        request.setAttribute("mensajes", lista);
        request.setAttribute("conversacion", conversacion);
        request.setAttribute("user", (Usuario) request.getSession().getAttribute("usuario"));
        RequestDispatcher rd = request.getRequestDispatcher("chatConversacion.jsp");
        rd.forward(request, response);
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
