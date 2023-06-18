

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewProfile() {
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
		
		RegisterStudent register = RegisterDAO.getStudentById(email);		// getStudentById DAO
		
		request.getRequestDispatcher("profileNav.html").include(request,  response);
		out.println("<h1 class=\"text-center mt-[12rem] text-5xl text-[#1AB79D]\">View Profile</h1>");
		out.println("<main class=\"m-[20rem] mt-14 w-1/2 border mx-auto rounded-2xl p-8 text-3xl\">");

		out.println("<p> <b>Student ID: </b>"+"\t"+register.getStudent_id()+"</p>");
		out.println("<p> <b>Name: </b>"+"\t"+register.getName()+"</p>");
		out.println("<p> <b>Email: </b>"+"\t"+register.getEmail()+"</p>");
		out.println("<p> <b>Contact: </b>"+"\t"+register.getcontact()+"</p>");
		out.println("<p> <b>Date of Birth: </b>"+"\t"+register.getDob()+"</p>");
		out.println("<p> <b>Gender: </b>"+"\t"+register.getGender()+"</p>");
		out.println("<p> <b>Nationality: </b>"+"\t"+register.getNationality()+"</p>");
		out.println("<p> <b>Address: </b>"+"\t"+register.getAddress()+"</p>");
		out.println("<a class=\"bg-[#1AB79D] text-white py-2 px-4 rounded w-fit mx-auto \" href= 'EditProfile'>Edit Profile</a>");
		out.println("</main>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
