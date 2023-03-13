package tese.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tese.dao.UsuarioDAO;
import tese.pojo.Usuario;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/Auth/","/Auth/login","/Auth/registrar","/Auth/insertar", "/Auth/recuperar","/Auth/actualizar" })
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO user;
    
    public void init(){
        user = new UsuarioDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getServletPath();
        System.out.println("accion:" + accion);
        
        try {
            System.out.println("accion:" + accion);
            if ( "/Auth/".equals(accion) || "/Auth/login".equals(accion) ){
                    showFormLogin(request, response);
            } else 
            if ( "/Auth/login".equals(accion) ){
                    loginUsuario(request, response);
            } else 
            if ( "/Auth/registrar".equals(accion) ){
                    showFormRegistro(request, response);
            } else 
            if ( "/Auth/insertar".equals(accion) ){
                    insertUsuario(request, response);
            } else 
            if ( "/Auth/recuperar".equals(accion) ){
                    showFormRecuperar(request, response);
            } else 
            if ( "/Auth/actualizar".equals(accion) ){
                    showFormActualizar(request, response);
            }
            
        } catch (Exception ex ) {
             throw new ServletException(ex);
        }

    }
    //--
    private void showFormLogin(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("showFormRegistro:");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/login.jsp");
        dispatcher.forward(request, response);
    }
    
    private void loginUsuario(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Usuario newUsuario = user.select(email);
            System.out.println("Se encontro el Usuario");
            response.sendRedirect("index.jsp");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: insertUsuario: " + ex.getMessage());
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //-------
    private void showFormRegistro(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("showFormRegistro:");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/registro.jsp");
        dispatcher.forward(request, response);
    }
    private void insertUsuario(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        
        
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            Usuario newUsuario = new Usuario(email, password, password2);
            user.insert(newUsuario);
            System.out.println("Se inserto el Usuario");
            response.sendRedirect("login");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: insertUsuario: " + ex.getMessage());
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      ///---
    
    private void showFormRecuperar(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("showFormRegistro:");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/recuperar.jsp");
        dispatcher.forward(request, response);
    }
    
     private void showFormActualizar(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("showFormRegistro:");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/actualizar.jsp");
        dispatcher.forward(request, response);
    }
    
    //-----
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
