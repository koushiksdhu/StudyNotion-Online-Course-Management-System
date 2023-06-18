import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.io.IOException;
import java.io.PrintWriter;

public class EditProfile extends HttpServlet implements HttpSessionListener{
	private static final long serialVersionUID = 1L;
    public EditProfile() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");
		
		if (email == null || request.getRequestURI().endsWith("login.html")) {
            session.invalidate(); // Invalidate the session1
            response.sendRedirect("index.html");
	 }		
		RegisterStudent register = RegisterDAO.getStudentById(email);			// getStudentById DAO
		
		request.getRequestDispatcher("profileNav.html").include(request,  response);
		out.println("<h1 class=\"text-center mt-[12rem] text-5xl text-[#1AB79D]\">Update Profile</h1>");
		out.println("<main class=\"m-[20rem] mt-14 w-1/2 border mx-auto rounded-2xl p-8 text-3x1\">");
		out.print("<form action='Edit' method='get'>");
		out.print("<table>");
		out.print("<tr><td></td><td> </td><td><input type='hidden' name='id' value='"+register.getStudent_id()+"'/></td></tr>");
		out.print("<tr><td>Name:  </td><td> </td><td><input type='text' name='name' value='"+register.getName()+"'/></td></tr>");
		out.print("<tr><td>Email:  </td><td> </td><td><input type='email' name='email' value='"+register.getEmail()+"'/></td></tr>");
		out.print("<tr><td>Contact:  </td><td> </td><td><input type='text' name='contact' value='"+register.getcontact()+"'/></td></tr>");
		out.print("<tr><td>DOB:  </td><td></td> <td><input type='text' name='dob' value='"+register.getDob()+"'/></td></tr>");
		out.print("<tr><td>Gender:  </td><td> </td>");
		out.print("<td><select name='gender' value='\"+register.getGender()+\"'>");
		out.print("<option value=\"Male\" >Male</option>");
		out.print("<option value=\"Female\" >Female</option>");
		out.print("<option value=\"Other\" >Other</option>");
		out.print("</select>");
		out.print("<tr><td colspan = 1>Nationality:  </td><td> </td><td  colspan = 1><input type='text' name='nationality' value='"+register.getNationality()+"'/></td></tr>");
		out.print("<tr><td>Address:  </td><td></td> <td><textarea type='text' name='address' >"+register.getAddress()+"</textarea></td></tr>");
		out.print("</td></tr>");		
		out.print("</table>");
		
		out.print("<div class=\"bg-[#1AB79D] text-white py-2 px-4 rounded w-fit mx-auto \"><input type = 'submit' value = 'Save' /></div>");
		out.print("</form>");
		out.print("</main>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		if (session.getAttribute("email") == null) {
//            response.sendRedirect("index.html");
//            return;
//        }
	}

    public void sessionDestroyed(HttpSessionEvent se) {
        try {
            // Get the HttpServletResponse object from the session
            HttpServletResponse response = (HttpServletResponse) se.getSession().getAttribute("response");
            
            // Redirect to the desired page
            response.sendRedirect("index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



