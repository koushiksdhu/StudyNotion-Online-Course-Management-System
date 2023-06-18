import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSessionEvent;
public class RegisterDAO {
	public static Connection getConnection() {
		Connection con1 = null;
		try {
			con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/eeducation",
					"root",
					"password");
		}catch(Exception e1) {
			System.out.println(e1.getMessage());
		}
		return con1;
	}
	
	// SIGNUP
	public static int save(RegisterStudent register) {
		int status = 0;
		int lastStudentId = 0;
		try {
			Connection con1 = getConnection();
			PreparedStatement ps2 = con1.prepareStatement("SELECT MAX(student_id) FROM user_details");
			PreparedStatement ps1 = con1.prepareStatement("insert into user_details(student_id, name, email, contact, dob, gender, nationality, address, password) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ResultSet rs = ps2.executeQuery();
			if(rs.next()) {
	            lastStudentId = rs.getInt(1);
	            lastStudentId++;
	        }
			else { 
				lastStudentId = 1001;
			}
			
			System.out.println(lastStudentId+"\n");
			ps1.setInt(1,  lastStudentId);
			ps1.setString(2,  register.getName());
			ps1.setString(3,  register.getEmail());
			ps1.setString(4,  register.getcontact());
			ps1.setString(5,  register.getDob());
			ps1.setString(6,  register.getGender());
			ps1.setString(7,  register.getNationality());
			ps1.setString(8,  register.getAddress());
			ps1.setString(9,  register.getPassword());
			
			status = ps1.executeUpdate();
			con1.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		return status;
	}
	
	// LOGIN
	public static int login(String email, String pass) {
		int status = 0;
		try {
			Connection con = RegisterDAO.getConnection();
			PreparedStatement ps = con.prepareStatement("select password from user_details where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String password = rs.getString("password");
				if(password.equals(pass)) {
					status = 1;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	// VIEW PROFILE
	public static RegisterStudent getStudentById(String email) {
		RegisterStudent register = new RegisterStudent();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from user_details where email = ?");
			ps.setString(1,  email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				register.setStudent_id(rs.getInt(1));
				register.setName(rs.getString(2)); 
				register.setEmail(rs.getString(3));
				register.setcontact(rs.getString(4));
				register.setDob(rs.getString(5));
				register.setGender(rs.getString(6));
				register.setNationality(rs.getString(7));
				register.setAddress(rs.getString(8));
				register.setPassword(rs.getString(9));
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return register;
	}
	
	// UPDATE PROFILE
	public static int update(RegisterStudent register){
		int status = 0;
		try {
			Connection con = RegisterDAO.getConnection();
			PreparedStatement ps = con.prepareStatement("update user_details set name = ?, email = ?, contact = ?, dob = ?, gender = ?, nationality = ?, address = ? where student_id = ?");
			ps.setString(1,  register.getName());
			ps.setString(2,  register.getEmail());
			ps.setString(3,  register.getcontact());
			ps.setString(4,  register.getDob());
			ps.setString(5,  register.getGender());
			ps.setString(6,  register.getNationality());
			ps.setString(7,  register.getAddress());
			ps.setInt(8,  register.getStudent_id());
			
			status = ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	// UPDATE PASSWORD
	public static int updatePassword(String email, String oldPassword, String newPassword ){				// UpdatePassword DAO
		int status = 0;
		try {
			Connection con = RegisterDAO.getConnection();
			PreparedStatement ps = con.prepareStatement("update user_details set password = ? where email = ? AND password = ?");
			ps.setString(1,  newPassword);
			ps.setString(2,  email);
			ps.setString(3,  oldPassword);
			status = ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	public static List<RegisterStudent> getAllStudent() {
		List<RegisterStudent> list = new ArrayList<>();
		
		try {
			Connection con1 = RegisterDAO.getConnection();
			PreparedStatement ps1 = con1.prepareStatement("select * from user_details");
			ResultSet rs = ps1.executeQuery();
			while(rs.next()) {
				RegisterStudent register = new RegisterStudent();
				register.setStudent_id(rs.getInt(1));
				register.setName(rs.getString(2));
				register.setEmail(rs.getString(3));
				register.setcontact(rs.getString(4));
				register.setDob(rs.getString(5));
				register.setGender(rs.getString(6));
				register.setNationality(rs.getString(7));
				register.setAddress(rs.getString(8));
				register.setPassword(rs.getString(9));
				list.add(register);
			}
			con1.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static int delete(RegisterStudent register){
		int status = 0;
		try {
			Connection con = RegisterDAO.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from user_details where student_id = ?");
			ps.setInt(1,  register.getStudent_id());
			
			status = ps.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
		
	
//	 public void sessionDestroyed(HttpSessionEvent event) {
//	        HttpServletResponse response = (HttpServletResponse) event.getSession().getAttribute("response");
//	        try {
//	            response.sendRedirect("index.html");
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	 }
}

