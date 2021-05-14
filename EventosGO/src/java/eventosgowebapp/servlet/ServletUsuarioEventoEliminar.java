/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EntradaFacade;
import eventosgowebapp.dao.EventoFacade;
import eventosgowebapp.dao.UsuarioEventoFacade;
import eventosgowebapp.entity.Entrada;
import eventosgowebapp.entity.Evento;
import eventosgowebapp.entity.UsuarioEvento;
import eventosgowebapp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author x Cristhian x
 */
@WebServlet(name = "ServletUsuarioEventoEliminar", urlPatterns = {"/ServletUsuarioEventoEliminar"})
public class ServletUsuarioEventoEliminar extends HttpServlet {

    @EJB
    private EventoFacade eventoFacade;
    
    @EJB
    private EntradaFacade entradaFacade;
    
    @EJB
    private UsuarioEventoFacade userEventoFacade;
    
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
        
        Usuario user = (Usuario) request.getSession().getAttribute("usuario");
        UsuarioEvento userEvento = (UsuarioEvento) request.getSession().getAttribute("usuarioEvento");
        String idEvento = request.getParameter("eventoid");
        Evento evento = this.eventoFacade.find(new Integer(idEvento));
        Date fechaActual = new Date();
        List<Integer> idEventosToRemove = (List) request.getSession().getAttribute("remove");
        
        for(Entrada e : this.entradaFacade.findByIdUsuarioAndIdEvento(userEvento, evento)){
            if(fechaActual.before(evento.getFechaEvento())){
                this.entradaFacade.remove(e);
                userEvento.getEntradaList().remove(e);
                this.userEventoFacade.edit(userEvento);
            }  else if(idEventosToRemove!=null){
                idEventosToRemove.add(e.getIdEvento().getId());
            } else{
                idEventosToRemove = new ArrayList();
                idEventosToRemove.add(e.getIdEvento().getId());
                
            }
        }
        
        if(idEventosToRemove != null){
            request.getSession().setAttribute("remove", idEventosToRemove);
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("ServletUsuarioEventosCargar");
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
