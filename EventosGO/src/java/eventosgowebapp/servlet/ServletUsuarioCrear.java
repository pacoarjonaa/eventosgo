/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventosgowebapp.servlet;

import eventosgowebapp.dao.UsuarioEventoFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eventosgowebapp.dao.UsuarioFacade;
import eventosgowebapp.entity.Usuario;
import eventosgowebapp.entity.UsuarioEvento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author x Cristhian x
 */
@WebServlet(name = "ServletUsuarioCrear", urlPatterns = {"/ServletUsuarioCrear"})
public class ServletUsuarioCrear extends HttpServlet {
    
    @EJB
    private UsuarioFacade userFacade;
    
    @EJB
    private UsuarioEventoFacade usereventFacade;

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
        
        String strError = "";
        String id, correo, password, password2, nombre, apellidos, ciudad, domicilio;
        Integer edad, rol=4, sexo;
        Date fechaNacimiento = null;
        Usuario nuevoUsuario;
        UsuarioEvento nuevoUsuarioEvento;
        
        id = request.getParameter("id");
        correo = request.getParameter("correo");
        password = request.getParameter("pass1");
        password2 = request.getParameter("pass2");
        nombre = request.getParameter("nombre");
        apellidos = request.getParameter("apellidos");
        sexo = new Integer(request.getParameter("Sexo"));
        ciudad = request.getParameter("ciudad");
        domicilio = request.getParameter("domicilio");
        
        try {
            fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fechaNacimiento"));
        } catch (ParseException ex) {
            Logger.getLogger(ServletUsuarioCrear.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!password.equals(password2)){
            strError = "Las contraseñas no coinciden";
            request.setAttribute("error", strError);
            RequestDispatcher rd = request.getRequestDispatcher("registroUsuario.jsp");
            rd.forward(request, response);
        }
        
        if (id == null || id.isEmpty()){
            nuevoUsuario = new Usuario();    
            nuevoUsuarioEvento = new UsuarioEvento();                  // Crea un usuario nuevo
            nuevoUsuario.setRol(rol);
        } else{
            nuevoUsuario = this.userFacade.find(new Integer(id));      // Editar el cliente seleccionado
            nuevoUsuarioEvento = this.usereventFacade.find(new Integer(id));
        }

        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setCorreo(correo);
        nuevoUsuario.setContrasena(password);
    
         if(id==null || id.isEmpty()){
            this.userFacade.create(nuevoUsuario);
        } else{
            this.userFacade.edit(nuevoUsuario);
        }
        
        nuevoUsuarioEvento.setId(nuevoUsuario.getId());
        nuevoUsuarioEvento.setSexo(sexo);
        nuevoUsuarioEvento.setCiudad(ciudad);
        nuevoUsuarioEvento.setFechaNacimiento(fechaNacimiento);
        nuevoUsuarioEvento.setDomicilio(domicilio);
        nuevoUsuarioEvento.setApellidos(apellidos);
        
        if(id==null || id.isEmpty()){
            this.usereventFacade.create(nuevoUsuarioEvento);
        } else{
            this.usereventFacade.edit(nuevoUsuarioEvento);
        }
        
       
        RequestDispatcher rd = request.getRequestDispatcher("ServletUsuarioEventosDisponiblesCargar");
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
