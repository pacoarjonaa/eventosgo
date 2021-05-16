/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.EntradaFacade;
import eventosgowebapp.dao.EstudioFacade;
import eventosgowebapp.dao.EventoFacade;
import eventosgowebapp.dao.UsuarioEventoFacade;
import eventosgowebapp.entity.Entrada;
import eventosgowebapp.entity.Estudio;
import eventosgowebapp.entity.Evento;
import eventosgowebapp.entity.Usuario;
import eventosgowebapp.entity.UsuarioEvento;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
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
@WebServlet(name = "ServletCalcularEstudio", urlPatterns = {"/ServletCalcularEstudio"})
public class ServletCalcularEstudio extends HttpServlet {

    @EJB
    private EstudioFacade estudioFacade;

    @EJB
    private UsuarioEventoFacade usuarioEventoFacade;

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
        List<UsuarioEvento> lista = new ArrayList<>();
        String titulo = (request.getParameter("titulo") != null) ? new String(request.getParameter("titulo").getBytes("ISO-8859-1"), "UTF-8") : null;

        // Filtros estudio: ciudad, rango edad
        int edadMinima = Integer.parseInt(request.getParameter("edad_min"));
        int edadMaxima = Integer.parseInt(request.getParameter("edad_max"));
        String ciudad = (request.getParameter("ciudad") != null) ? new String(request.getParameter("ciudad").getBytes("ISO-8859-1"), "UTF-8") : null;
        int anio = (request.getParameter("anio") != null && !request.getParameter("anio").isEmpty()) ? Integer.parseInt(request.getParameter("anio")) : -1;
        int masculino = (request.getParameter("masculino") != null) ? Integer.parseInt(request.getParameter("masculino")) : -1;
        int femenino = (request.getParameter("femenino") != null) ? Integer.parseInt(request.getParameter("femenino")) : -1;
        int otro = (request.getParameter("otro") != null) ? Integer.parseInt(request.getParameter("otro")) : -1;

        List<UsuarioEvento> res = this.usuarioEventoFacade.findAll();

        if (edadMinima <= edadMaxima) {

            res = this.usuarioEventoFacade.filtroEdad(edadMinima, edadMaxima, res);

            if (masculino != -1 || femenino != -1 || otro != -1) {

                int[] genero = {masculino, femenino, otro};
                res = this.usuarioEventoFacade.filtroSexo(genero, res);

                if (ciudad != null && !ciudad.isEmpty()) {
                    res = this.usuarioEventoFacade.filtroCiudad(ciudad, res);
                }

                if (anio > 0) {

                    List<Evento> listEventos = this.eventoFacade.filtroAnio(anio, this.eventoFacade.findAll());
                    List<UsuarioEvento> aux = new ArrayList<>();

                    if (listEventos != null) {
                        for (UsuarioEvento u : res) {

                            List<Entrada> entradas = (this.entradaFacade.findByIdUsuario(u.getId()) == null) ? new ArrayList<>() : this.entradaFacade.findByIdUsuario(u.getId());

                            for (Entrada e : entradas) {

                                if (listEventos.contains(e.getIdEvento())) {
                                    if (!aux.contains(u)) {
                                        aux.add(u);
                                    }

                                }

                            }
                        }

                        res = aux;
                    }

                }

            }

        }

        Estudio est = null;

        if (request.getParameter("idEstudio") != null) {
            est = this.estudioFacade.find(Integer.parseInt(request.getParameter("idEstudio")));
        } else {
            est = new Estudio();
            est.setIdAnalista((Usuario) request.getSession().getAttribute("usuario"));
        }

        //Objeto estudio en local
        est.setTitulo(titulo);
        StringJoiner resultado = new StringJoiner(";");
        resultado.add(Integer.toString(edadMinima));
        resultado.add(Integer.toString(edadMaxima));
        resultado.add(ciudad);
        resultado.add(Integer.toString(anio));
        resultado.add(Integer.toString(masculino));
        resultado.add(Integer.toString(femenino));
        resultado.add(Integer.toString(otro));
        est.setResultado(resultado.toString());

        request.setAttribute("lista", res);
        request.setAttribute("estudio", est);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("confirmarEstudio.jsp");
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
