/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EntradaFacade;
import eventosgowebapp.dao.EventoFacade;
import eventosgowebapp.entity.Evento;
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

/**
 *
 * @author pacoa
 */
@WebServlet(name = "ServletParaComprarEntradas", urlPatterns = {"/ServletParaComprarEntradas"})
public class ServletParaComprarEntradas extends HttpServlet {

    @EJB
    private EntradaFacade entradaFacade;

    @EJB
    private EventoFacade eventoFacade;

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
        response.setContentType("text/html;charset=UTF-8");
        
        
        String idEvento = request.getParameter("id");
        Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
                
        Evento evento = this.eventoFacade.find(new Integer(idEvento));
        int entradasCompradas = this.entradaFacade.findByIdEvento(evento).size();
        int entradasCompradasUsuario = this.entradaFacade.findByIdUsuario(usuario.getId()).size();
        int entradasDisponibles = evento.getAforo() - entradasCompradas;
        
        request.setAttribute("evento", evento);
        request.setAttribute("entradasDisponibles", entradasDisponibles);
        request.setAttribute("entradasCompradasUsuario", entradasCompradasUsuario);
        
        RequestDispatcher rd = request.getRequestDispatcher("comprarEntradas.jsp");
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
