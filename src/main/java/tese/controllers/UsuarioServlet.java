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
import javax.servlet.http.HttpSession;
import tese.dao.UsuarioDAO;
import tese.pojo.Usuario;

@WebServlet(name = "UsuarioServlet", urlPatterns = 
{"/Auth/","/Auth/login",
"/Auth/registrar","/Auth/insertar", 
"/Auth/recuperar","/Auth/actualizar" })
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsuarioDAO userDato;
    private final int _GET_ = 0;
    private final int _POST_= 0;
    
    public void init(){
        user = new UsuarioDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, 
String metodo, String accion)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");

        System.out.println("processRequest:metodo " + metodo + " + accion:" + accion);
        try {
            if ( "/Auth/".equals(accion) ) {
                showFormLogin(request, response);
            }
            if ( "GET".equals (metodo ) && "/Auth/login".equals(accion) ){
                    showFormLogin(request, response);
            }    
            if ( "POST".equals (metodo ) &&"/Auth/login".equals(accion) ){
                    loginUsuario(request, response);
            }    
             
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
    //-- Mostrar formulario
    private void showFormLogin(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("showFormRegistro:");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/login.jsp");
        dispatcher.forward(request, response);
    }
    //-- Validiar login
    private void loginUsuario(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        System.out.println("UsuarioServlet: POST. loginUsuario ---" );
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Usuario newUsuario = user.select(email);
            
            if ( newUsuario == null ) {
                	RequestDispatcher rd = getServletContext().getRequestDispatcher("/auth/login.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>EL nombre de usuario o contraseña es incorrecto.</font>");
			rd.include(request, response);
            } else {
                System.out.println("Se encontro el Usuario: " + newUsuario);
             //    request.getRequestDispatcher( "/index.jsp").include( request, response);
              //  RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
              //  rd.forward(request, response);
              
              HttpSession session = request.getSession();
			session.setAttribute("USUARIO", email);
			session.setMaxInactiveInterval(30*60);
                        String destino = request.getContextPath() + "/index.jsp";
                        System.out.println(">> : " + destino );
              // response.sendRedirect("/index.jsp");
              response.sendRedirect(destino);
             
           //   RequestDispatcher rd = getServletContext().getRequestDispatcher(destino);
           //   rd.forward(request, response);
              
            }
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
            System.out.println("Se inserto el Usuario:" + email );
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
        String accion = request.getServletPath();
        System.out.println("UsuarioSevlet.doGet:" + accion);
        
        processRequest(request, response, "GET", accion);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getServletPath();
        System.out.println("UsuarioSevlet.doPost" + accion);
        
        processRequest(request, response, "POST", accion);
    }


    @Override
    public String getServletInfo() {
        return "Short description: getServletInfo";
    }// </editor-fold>

}
