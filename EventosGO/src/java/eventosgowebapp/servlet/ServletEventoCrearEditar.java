/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EtiquetaFacade;
import eventosgowebapp.dao.EventoFacade;
import eventosgowebapp.entity.Etiqueta;
import eventosgowebapp.entity.Evento;
import eventosgowebapp.entity.EventoEtiqueta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ServletEventoCrearEditar", urlPatterns = {"/ServletEventoCrearEditar"})
public class ServletEventoCrearEditar extends HttpServlet {
    
    @EJB
    private EventoFacade eventoFacade;
    
    @EJB
    private EtiquetaFacade etiquetaFacade;

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
    
        String strTo = "crearEvento.jsp";                    
        String strId = request.getParameter("id");
         List<Etiqueta> listEtiquetas = new ArrayList<>();

        if (strId != null) { // Es editar evento
  
            Evento evento = this.eventoFacade.find(new Integer(strId));
            request.setAttribute("evento", evento);       
           
            for(EventoEtiqueta evet : evento.getEventoEtiquetaList()){
                listEtiquetas.add(this.etiquetaFacade.find(evet.getIdEtiqueta()));
            }
            request.setAttribute("listaEtiquetas", listEtiquetas);
        } 
        
        RequestDispatcher rd = request.getRequestDispatcher(strTo);
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
