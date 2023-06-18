import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.io.IOException;
import java.io.PrintWriter;

public class Register extends HttpServlet implements HttpSessionListener{
	private static final long serialVersionUID = 1L;
   
    public Register() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
//		String student_id = request.getParameter("student_id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String nationality = request.getParameter("nationality");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		
		RegisterStudent register = new RegisterStudent();
//		register.setStudent_id(student_id);
		register.setName(name);
		register.setEmail(email);
		register.setcontact(contact);
		register.setDob(dob);
		register.setGender(gender);
		register.setNationality(nationality);
		register.setAddress(address);
		register.setPassword(password);
		
		
		int status = RegisterDAO.save(register);					// Save DAO
		if(status > 0) {
			request.getRequestDispatcher("login.html").include(request,  response);
			out.println("<p style=\"text-align:center; color:green; font-size:20px;\">Registration Successful! Please Login to Continue</p>");
		}else {
//			out.println("Sorry! Unable to SignUp.");
			out.println("<p style=\"text-align:center; color:red; font-size:20px;\">Sorry! Unable to SignUp</p>");
		}
		out.close();
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
