package tese.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SesionServlet", urlPatterns = {"/Auth/LoginServlet"})
public class LoginSesionServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private final String emailConst = "admin1@gmail.com";
    private final String passwordConst = "password";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("LoginSesionServlet:");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/auth/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(emailConst.equals(email) && passwordConst.equals(password)){
			HttpSession session = request.getSession();
			session.setAttribute("USUARIO", email);
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("USUARIO", email);
			userName.setMaxAge(30*60);
			response.addCookie(userName);
                        
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/auth/login.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>EL nombre de usuario o contraseña es incorrecto.</font>");
			rd.include(request, response);
		}
    }

}
