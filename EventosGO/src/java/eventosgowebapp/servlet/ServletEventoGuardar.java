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
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import eventosgowebapp.dao.EventoFacade;
import eventosgowebapp.dao.EtiquetaFacade;
import eventosgowebapp.dao.EventoEtiquetaFacade;
import eventosgowebapp.entity.Etiqueta;
import eventosgowebapp.entity.Evento;
import eventosgowebapp.entity.EventoEtiqueta;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author x Cristhian x
 */
@WebServlet(name = "ServletEventoCrear", urlPatterns = {"/ServletEventoCrear"})
public class ServletEventoGuardar extends HttpServlet {

    @EJB
    private EventoFacade eventoFacade;
    
    @EJB 
    private EtiquetaFacade etiqFacade;
    
    @EJB 
    private EventoEtiquetaFacade evetFacade;
    
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
        
        String id, titulo, descripcion, strEtiquetas;
        String[] etiquetas;
        Double coste;
        Integer aforo, entradasPorUsuario;
        List<Etiqueta> listaEtiquetas = new ArrayList<>();
        Date fechaEvento=null, fechaEntradas=null;
        Evento nuevoEvento;
        String strTo = "paginaInicioWeb.jsp";
        
        id = request.getParameter("id");
        titulo = request.getParameter("titulo");
        descripcion = request.getParameter("descripcion");
        coste = new Double(request.getParameter("coste"));
        aforo = new Integer(request.getParameter("aforo"));
        entradasPorUsuario = new Integer(request.getParameter("entradas"));
        strEtiquetas = request.getParameter("etiquetas");
        
        etiquetas = strEtiquetas.split("\\W");
        for (String s : etiquetas){
            if(!s.isEmpty()){
                Etiqueta et = this.etiqFacade.findByName(s);
                if (et==null){
                    et = new Etiqueta();
                    et.setNombre(s);
                    etiqFacade.create(et);
                }
            listaEtiquetas.add(et);
            }
        }
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fechaEvento = formato.parse(request.getParameter("fechaEvento"));
            fechaEntradas = formato.parse(request.getParameter("fechaEntradas"));
        } catch (ParseException ex) {
            Logger.getLogger(ServletEventoGuardar.class.getName()).log(Level.SEVERE, null, ex);
            strTo = "crearEvento.jsp";
            request.setAttribute("error", "Formato de fecha no válido. Prueba con (yyyy-MM-dd)");
            RequestDispatcher rd = request.getRequestDispatcher(strTo);
            rd.forward(request, response);  
        }
        
        if (id == null || id.isEmpty()){
            nuevoEvento = new Evento();                 // Nuevo evento
        } else{
            nuevoEvento = this.eventoFacade.find(new Integer(id));  // Editar evento existente
        }
       
        nuevoEvento.setTitulo(titulo);
        nuevoEvento.setDescripcion(descripcion);
        nuevoEvento.setCoste(coste);
        nuevoEvento.setAforo(aforo);
        nuevoEvento.setMaximoEntradasUsuario(entradasPorUsuario);
        nuevoEvento.setFechaEvento(fechaEvento);
        nuevoEvento.setFechaFinReservas(fechaEntradas);
        
        if(id == null || id.isEmpty()){
            this.eventoFacade.create(nuevoEvento);
        } else{
            this.eventoFacade.edit(nuevoEvento);
        }
        
        List<EventoEtiqueta> listaEventoEtiqueta = new ArrayList<>();
        for(Etiqueta et : listaEtiquetas){
            EventoEtiqueta evet = new EventoEtiqueta();
            evet.setIdEtiqueta(et);
            evet.setIdEvento(nuevoEvento);
            this.evetFacade.create(evet);
            listaEventoEtiqueta.add(evet);
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
