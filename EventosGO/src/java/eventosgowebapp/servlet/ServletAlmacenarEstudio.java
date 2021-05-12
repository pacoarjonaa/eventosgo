/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EstudioFacade;
import eventosgowebapp.dao.UsuarioFacade;
import eventosgowebapp.entity.Estudio;
import eventosgowebapp.entity.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juanm
 */
@WebServlet(name = "ServletAlmacenarEstudio", urlPatterns = {"/ServletAlmacenarEstudio"})
public class ServletAlmacenarEstudio extends HttpServlet {

    @EJB
    private EstudioFacade estudioFacade;

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
        String titulo = new String(request.getParameter("titulo").getBytes("ISO-8859-1"), "UTF-8");
        String resultado = request.getParameter("resultado");

        if (request.getParameter("estudio") != null) {
            Estudio e = this.estudioFacade.findByID(Integer.parseInt(request.getParameter("estudio")));
            Usuario u = (Usuario) request.getSession().getAttribute("usuario");
            List<Estudio> lista = u.getEstudioList();
            lista.remove(e);
            e.setTitulo(titulo);
            e.setResultado(resultado);
            lista.add(e);
            u.setEstudioList(lista);
            this.estudioFacade.edit(e);
            this.usuarioFacade.edit(u);
        } else {
            Estudio e = new Estudio();
            Usuario u = (Usuario) request.getSession().getAttribute("usuario");
            e.setIdAnalista(u);
            e.setTitulo(titulo);
            e.setResultado(resultado);
            this.estudioFacade.create(e);
            List<Estudio> lista = u.getEstudioList();
            lista.add(e);
            u.setEstudioList(lista);
            this.usuarioFacade.edit(u);
        }

        response.sendRedirect("ServletEstudioCargar");
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
