/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EstudioFacade;
import eventosgowebapp.entity.Estudio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juanm
 */
@WebServlet(name = "ServletEstudioEditar", urlPatterns = {"/ServletEstudioEditar"})
public class ServletEstudioEditar extends HttpServlet {
    
    @EJB
    private EstudioFacade estudioFacade;

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
        int idEstudio = Integer.parseInt(request.getParameter("estudio"));
        
        Estudio estudio = estudioFacade.findByID(idEstudio);
        String resultado = estudio.getResultado();
        
        int edadMinima;
        int edadMaxima;
        String ciudad;
        int anio;
        int masculino;
        int femenino;
        int otro;
        
        
        try (Scanner sc = new Scanner(resultado)) {
            sc.useDelimiter(";");
            edadMinima = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            edadMaxima = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            String aux = sc.next();
            ciudad = (sc.hasNext() && !aux.isEmpty()) ? aux : null;
            anio = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            masculino = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            femenino = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
            otro = (sc.hasNext()) ? Integer.parseInt(sc.next()) : -1;
        }
        
        request.setAttribute("idEstudio", estudio.getId());
        request.setAttribute("titulo", estudio.getTitulo());
        request.setAttribute("eMin", edadMinima);
        request.setAttribute("eMax", edadMaxima);
        request.setAttribute("ciudad", ciudad);
        request.setAttribute("anio", anio);
        request.setAttribute("masculino", masculino);
        request.setAttribute("femenino", femenino);
        request.setAttribute("otro", otro);
        
        RequestDispatcher rd = request.getRequestDispatcher("editarEstudio.jsp");
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
