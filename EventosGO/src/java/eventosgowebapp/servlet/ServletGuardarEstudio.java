/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EstudioFacade;
import eventosgowebapp.entity.UsuarioEvento;
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

/**
 *
 * @author Pablo
 */
@WebServlet(name = "ServletGuardarEstudio", urlPatterns = {"/ServletGuardarEstudio"})
public class ServletGuardarEstudio extends HttpServlet {

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
        List<UsuarioEvento> lista = new ArrayList<>();
        String titulo = request.getParameter("titulo");

        // Filtros estudio: ciudad, rango edad
        int edadMinima = Integer.parseInt(request.getParameter("edadMinima"));
        int edadMaxima = Integer.parseInt(request.getParameter("edadMaxima"));
        String ciudad = request.getParameter("ciudad");
        int anio = Integer.parseInt(request.getParameter("anio"));
        int masculino = -1;
        int femenino = -1;
        int otro = -1;

        if (request.getParameter("masculino") != null) {
            masculino = Integer.parseInt(request.getParameter("masculino"));
        }

        if (request.getParameter("femenino") != null) {
            femenino = Integer.parseInt(request.getParameter("femenino"));
        }

        if (request.getParameter("otro") != null) {
            otro = Integer.parseInt(request.getParameter("otro"));
        }

        if (edadMinima <= edadMaxima) {

            if (masculino != -1 || femenino != -1 || otro != -1) {
                if (masculino != -1 && femenino != -1 && otro != -1) {

                } else if (masculino != -1 && femenino != -1) {

                } else if (femenino != -1 && otro != -1) {

                } else if (otro != -1 && masculino != -1) {

                } else if (masculino != -1) {

                } else if (femenino != -1) {

                } else if (otro != -1) {

                }
            }

        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
        requestDispatcher.forward(request, response);

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
