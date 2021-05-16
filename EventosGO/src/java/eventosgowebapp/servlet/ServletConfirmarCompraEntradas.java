/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EntradaFacade;
import eventosgowebapp.dao.EventoFacade;
import eventosgowebapp.dao.UsuarioEventoFacade;
import eventosgowebapp.dao.UsuarioFacade;
import eventosgowebapp.entity.Entrada;
import eventosgowebapp.entity.Evento;
import eventosgowebapp.entity.Usuario;
import eventosgowebapp.entity.UsuarioEvento;
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
@WebServlet(name = "ServletConfirmarCompraEntradas", urlPatterns = {"/ServletConfirmarCompraEntradas"})
public class ServletConfirmarCompraEntradas extends HttpServlet {

    @EJB
    private EventoFacade eventoFacade;

    @EJB
    private UsuarioEventoFacade usuarioEventoFacade;

    @EJB
    private EntradaFacade entradaFacade;

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
        
        
        String idEvento = request.getParameter("idEvento");
        String idUsuario = request.getParameter("idUsuario");
        Integer numEntradas = new Integer(request.getParameter("entradas"));
        Integer entradasCompradasUsuario = new Integer(request.getParameter("entradasCompradasUsuario"));
        Integer entradasDisponibles = new Integer(request.getParameter("entradasDisponibles"));
                
        Evento evento = this.eventoFacade.find(new Integer(idEvento));
        UsuarioEvento usuario = this.usuarioEventoFacade.find(new Integer(idUsuario));  // Aqui esta el error
        Entrada entrada;
        String strError = "";
        
        RequestDispatcher rd = request.getRequestDispatcher("ServletParaComprarEntradas?id=" + idEvento);
       
        int i = 0;
        if(entradasDisponibles - numEntradas < 0){
            strError = "El numero de entradas seleccionada hace superar el aforo máximo de este evento.";
            request.setAttribute("error", strError);
            rd.forward(request, response);
        } else if(numEntradas + entradasCompradasUsuario > evento.getMaximoEntradasUsuario()){
            strError = "El numero de entradas seleccionadas hace superar el numero maximo de entradas por usuario para este evento.";
            request.setAttribute("error", strError);
            rd.forward(request, response);
        }else{
            while(i < numEntradas){
                entrada = new Entrada();
                entrada.setIdEvento(evento);
                entrada.setIdUsuario(usuario);
                this.entradaFacade.create(entrada);
                i++;
            }
           
            RequestDispatcher rd2 = request.getRequestDispatcher("ServletUsuarioEventosCargar");
            rd2.forward(request, response);
        
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
