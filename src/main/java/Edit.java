
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Edit() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String dob = request.getParameter("dob");
		String gender = request.getParameter("gender");
		String nationality = request.getParameter("nationality");
		String address = request.getParameter("address");
		
		
		RegisterStudent register = new RegisterStudent();
		register.setStudent_id(Integer.parseInt(id));
		register.setName(name);
		register.setEmail(email);
		register.setcontact(contact);
		register.setDob(dob);
		register.setGender(gender);
		register.setNationality(nationality);
		register.setAddress(address);
		
		
		
		int status = RegisterDAO.update(register);				// Update DAO
		if(status > 0) {
			
			response.sendRedirect("ViewProfile");
		}else {
			request.getRequestDispatcher("EditProfile").include(request,  response);
			out.println("<p style=\"text-align:center; color:red; font-size:20px;\">Sorry! Unable to update record.</p>");
		}
		out.close();
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		doGet(request, response);
//	}

}
