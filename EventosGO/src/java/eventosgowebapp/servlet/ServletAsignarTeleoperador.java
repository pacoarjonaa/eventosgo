/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.ConversacionFacade;
import eventosgowebapp.dao.UsuarioFacade;
import eventosgowebapp.entity.Conversacion;
import eventosgowebapp.entity.Mensaje;
import eventosgowebapp.entity.Usuario;
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
 * @author Pablo
 */
@WebServlet(name = "ServletAsignarTeleoperador", urlPatterns = {"/ServletAsignarTeleoperador"})
public class ServletAsignarTeleoperador extends HttpServlet {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private ConversacionFacade conversacionFacade;

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
        HttpSession httpSession = request.getSession();
        Usuario usuario = (Usuario) httpSession.getAttribute("usuario");
        String asunto = new String(request.getParameter("asunto").getBytes("ISO-8859-1"), "UTF-8");
        List<Usuario> teleoperadores = usuarioFacade.findByRol(2);
        
        if (teleoperadores == null || teleoperadores.isEmpty()) {
            request.setAttribute("error", "No hay teleoperadores disponibles");
        } else {
            Usuario tlp = teleoperadores.get(0);
            List<Conversacion> conv = new ArrayList<>();
            conv.addAll(teleoperadores.get(0).getConversacionList());
            conv.addAll(teleoperadores.get(0).getConversacionList1());
            int num = conv.size();
            for (int i = 1; i < teleoperadores.size(); i++) {
                List<Conversacion> conversaciones = new ArrayList<>();
                conversaciones.addAll(teleoperadores.get(i).getConversacionList());
                conversaciones.addAll(teleoperadores.get(i).getConversacionList1());
                if (num > conversaciones.size()) {
                    tlp = teleoperadores.get(i);
                    num = conversaciones.size();
                }
            }
            Conversacion c = new Conversacion();
            c.setAsunto(asunto);
            c.setIdTeleoperador(tlp);
            c.setIdUsuario(usuario);
            List<Mensaje> listaMensaje = new ArrayList<>();
            c.setMensajeList(listaMensaje);
            
            List<Conversacion> listaC = new ArrayList<>();
            listaC.addAll(usuario.getConversacionList());
            listaC.addAll(usuario.getConversacionList1());
            listaC.add(c);
            usuario.setConversacionList(listaC);
            
            List<Conversacion> listaC2 = new ArrayList<>();
            listaC2.addAll(tlp.getConversacionList());
            listaC2.addAll(tlp.getConversacionList1());
            listaC2.add(c);
            tlp.setConversacionList(listaC2);
            
            this.conversacionFacade.create(c);
            this.usuarioFacade.edit(usuario);
            this.usuarioFacade.edit(tlp);
        }
        
        response.sendRedirect("ServletConversacionListar");
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
