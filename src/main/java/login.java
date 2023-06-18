

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;


public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public login() {
        super();

    }

	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		int status = RegisterDAO.login(email, password);					// login DAO
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies
		
		if(status == 1) {
	        
			HttpSession session = request.getSession();
			session.setAttribute("email", email);
			response.sendRedirect("profile.html");
			
			 if (email == null || request.getRequestURI().endsWith("login.html")) {
		            response.sendRedirect("index.html");
		            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		            session.invalidate(); // Invalidate the session1
			 }
				
			
			
//			
//			String requestedUrl = (String) session.getAttribute("email");
//            if (requestedUrl != null) {
//            	response.sendRedirect("profile.html");
//            } else {
//                response.sendRedirect("index.html");
//            }
		}
		else {
			request.getRequestDispatcher("login.html").include(request,  response);
            out.println("<p style=\"text-align:center; color:red; font-size:20px;\">Invalid Email or Password</p>");
        }

	}

}
