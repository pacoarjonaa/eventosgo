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
        String resultado = new String (request.getParameter("resultado").getBytes("ISO-8859-1"), "UTF-8");;
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        List<Estudio> lista = u.getEstudioList();
        Estudio e;
        
        if (request.getParameter("estudio") != null) {
            e = this.estudioFacade.find(Integer.parseInt(request.getParameter("estudio")));
            lista.remove(e);
        } else {
            e = new Estudio();
            e.setIdAnalista(u);
        }
        e.setTitulo(titulo);
        e.setResultado(resultado);
        lista.add(e);
        u.setEstudioList(lista);
        if (request.getParameter("estudio") != null) {
            this.estudioFacade.edit(e);
        } else {
            this.estudioFacade.create(e);
        }

        this.usuarioFacade.edit(u);

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
