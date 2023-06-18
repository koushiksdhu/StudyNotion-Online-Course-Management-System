

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class Password extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Password() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("email");
		String opsw = request.getParameter("opsw");
		String npsw = request.getParameter("npsw");
		
		if (email == null || request.getRequestURI().endsWith("login.html")) {
            session.invalidate(); // Invalidate the session1
            response.sendRedirect("index.html");
	 }
		
		int status = RegisterDAO.updatePassword(email, opsw, npsw);					// updatePassword DAO
		if(status > 0) {
			request.getRequestDispatcher("ChangePassword").include(request,  response);
			out.println("<p style=\"text-align:center; color:green; font-size:20px;\">Password Changed Successfully!</p>");
		}else {
			request.getRequestDispatcher("ChangePassword").include(request,  response);
			out.println("<p style=\"text-align:center; color:red; font-size:20px;\">Sorry! Unable to change password.</p>");
		}
		out.close();
		
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		doGet(request, response);
//	}

}
