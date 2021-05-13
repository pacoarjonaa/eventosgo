/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EntradaFacade;
import eventosgowebapp.dao.EventoFacade;
import eventosgowebapp.entity.Entrada;
import eventosgowebapp.entity.Evento;
import eventosgowebapp.entity.Usuario;
import eventosgowebapp.entity.UsuarioEvento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "ServletUsuarioEventosCargar", urlPatterns = {"/ServletUsuarioEventosCargar"})
public class ServletUsuarioEventosCargar extends HttpServlet {

    @EJB 
    private EventoFacade eventoFacade;
    
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
        
        Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
        UsuarioEvento userEvento = (UsuarioEvento) request.getSession().getAttribute("usuarioEvento");
        String filtroEvento = request.getParameter("filtroEvento");
        Map<Evento, Integer> eventosMap = new HashMap();
        List<Integer> idEventosToRemove = (List) request.getAttribute("remove");
      
        /*
        
       
        if(idEventosToRemove != null){
            for(Entrada e : this.entradaFacade.findByIdUsuario(userEvento.getId())){
                Evento evento = this.eventoFacade.find(e.getIdEvento());
                if(!eventosMap.containsKey(evento) && !idEventosToRemove.contains(evento.getId())) {
                    if(filtroEvento==null || filtroEvento.isEmpty()){
                        eventosMap.put(evento, 1);
                    } else{
                        Evento ev = this.eventoFacade.findBySimiliarNameAndId(filtroEvento, evento.getId());
                        eventosMap.put(evento, 1);
                    }    
                } else if(!idEventosToRemove.contains(evento.getId())){
                    if(filtroEvento==null || filtroEvento.isEmpty()){
                        eventosMap.put(evento, eventosMap.get(evento)+1);
                    } else{
                        Evento ev = this.eventoFacade.findBySimiliarNameAndId(filtroEvento, evento.getId());
                        eventosMap.put(ev, eventosMap.get(ev)+1);
                    } 
                }
            }
        } else{
            for(Entrada e : this.entradaFacade.findByIdUsuario(userEvento.getId())){
                Evento evento = this.eventoFacade.find(e.getIdEvento().getId());
                if(!eventosMap.containsKey(evento)) {
                    if(filtroEvento==null || filtroEvento.isEmpty()){
                        eventosMap.put(evento, 1);
                    } else{
                        Evento ev = this.eventoFacade.findBySimiliarNameAndId(filtroEvento, evento.getId());
                        
                        eventosMap.put(ev, 1);
                    }    
                } else {
                    if(filtroEvento==null || filtroEvento.isEmpty()){
                        eventosMap.put(evento, eventosMap.get(evento)+1);
                    } else{
                        Evento ev = this.eventoFacade.findBySimiliarNameAndId(filtroEvento, evento.getId());
                        eventosMap.put(ev, eventosMap.get(ev)+1);
                    } 
                }
            }
        }
        
        */
        
        for(Entrada e: this.entradaFacade.findByIdUsuario(userEvento.getId())){
            Evento evento;
            if(filtroEvento==null || filtroEvento.isEmpty()){
                evento = eventoFacade.find(e.getIdEvento().getId());
            } else{
                evento = eventoFacade.findBySimiliarNameAndId(filtroEvento, e.getIdEvento().getId());
            }
            if(evento!=null){
                if(!eventosMap.containsKey(evento)){
                    eventosMap.put(evento, 1);
                } else{
                    eventosMap.put(evento, eventosMap.get(evento)+1);
                }
            }
        }
            
            
        request.setAttribute("eventos", eventosMap);
        RequestDispatcher rd = request.getRequestDispatcher("usuarioEventos.jsp");
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
