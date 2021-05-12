/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EstudioFacade;
import eventosgowebapp.dao.EventoFacade;
import eventosgowebapp.dao.UsuarioEventoFacade;
import eventosgowebapp.dao.UsuarioFacade;
import eventosgowebapp.entity.Entrada;
import eventosgowebapp.entity.Estudio;
import eventosgowebapp.entity.Evento;
import eventosgowebapp.entity.Usuario;
import eventosgowebapp.entity.UsuarioEvento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
 * @author juanm
 */
@WebServlet(name = "ServletEstudioCargar", urlPatterns = {"/ServletEstudioCargar"})
public class ServletEstudioCargar extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private EstudioFacade estudioFacade;

    @EJB
    private UsuarioEventoFacade usuarioEventoFacade;

    @EJB
    private EventoFacade eventoFacade;

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
        int pagina = 1;
        if (request.getParameter("paginaActual") != null) {
            pagina = Integer.parseInt(request.getParameter("paginaActual"));
        }
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        List<Estudio> estudios = u.getEstudioList();
        List<Integer> resultadoEstudios = new ArrayList<>();

        for (Estudio es : estudios) {
            String s = es.getResultado();
            int edadMinima;
            int edadMaxima;
            String ciudad;
            int anio;
            int masculino;
            int femenino;
            int otro;
            try (Scanner sc = new Scanner(s)) {
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

            List<UsuarioEvento> res = this.usuarioEventoFacade.findAll();

            if (edadMinima <= edadMaxima) {

                res = this.usuarioEventoFacade.filtroEdad(edadMinima, edadMaxima, res);

                if (masculino != -1 || femenino != -1 || otro != -1) {

                    int[] genero = {masculino, femenino, otro};
                    res = this.usuarioEventoFacade.filtroSexo(genero, res);

                    if (ciudad != null) {
                        res = this.usuarioEventoFacade.filtroCiudad(ciudad, res);
                    }

                    if (anio > 0) {

                        List<Evento> listEventos = this.eventoFacade.filtroAnio(anio, this.eventoFacade.findAll());
                        List<UsuarioEvento> aux = new ArrayList<>();

                        if (listEventos != null) {
                            for (UsuarioEvento u1 : res) {

                                List<Entrada> entradas = u1.getEntradaList();

                                for (Entrada e : entradas) {

                                    if (listEventos.contains(e.getIdEvento())) {
                                        aux.add(u1);
                                    }

                                }
                            }

                            res = aux;
                        }

                    }

                }

            }
            resultadoEstudios.add(res.size());
        }

        request.setAttribute("resultadoEstudios", resultadoEstudios);
        request.setAttribute("listaEstudios", estudios);
        request.setAttribute("pagina", pagina);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("estudios.jsp");
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
