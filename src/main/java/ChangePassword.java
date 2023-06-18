

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ChangePassword() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email")
				;
		RegisterStudent register = RegisterDAO.getStudentById(email);				// getElementById DAO
		if (email == null || request.getRequestURI().endsWith("login.html")) {
            session.invalidate(); // Invalidate the session1
            response.sendRedirect("index.html");
	 }
		
		request.getRequestDispatcher("profileNav.html").include(request,  response);
		out.println("<h1 class=\"text-center mt-[12rem] text-5xl text-[#1AB79D]\">Change Password</h1>");
		out.println("<main class=\"m-[20rem] mt-14 w-1/2 border mx-auto rounded-2xl p-8 text-3xl\">");
		out.print("<form action='Password' method='get'>");
		out.print("<table>");
		out.print("<tr><td>Old Password: </td><td><input style= \"margin: 0px 40px;\" type='password' name='opsw'/></td></tr>");
		out.print("<tr><td>New Password: </td><td><input style= \"margin: 0px 40px;\"type='password' name='npsw'/></td></tr>");
		out.print("</td></tr>");
		out.print("</table>");
		out.print("<br><div class=\"bg-[#1AB79D] text-white py-2 px-4 rounded w-fit mx-auto\"><input type = 'submit' value = 'Change Password'/></div>");
		out.print("</form>");
		out.println("</main>");
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		doGet(request, response);
//	}

}
