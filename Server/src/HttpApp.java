import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

public class HttpApp extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String header = request.getHeader("authentication");
			if (header.equals("user register") == true) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				ResultSet rs = UserManagement.UserFind(username);
				if (rs.next() == true) {
					response.getWriter().write("The username has already existed");
				}
				else {
					UserManagement.UserRegister(username, password);
					File new_dir = new File("/home/SurveyCorps/" + username);
					if (new_dir.mkdirs() == true) {
						response.getWriter().write("registration succeeded");
					}
					else {
						response.getWriter().write("registration failed");
					}
				}
				
				rs.close();
			}
			else if (header.equals("user login") == true) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				ResultSet rs = UserManagement.UserFind(username);
				if (rs.next() == true) {
					if (MD5Util.md5encrypt(password).equals(rs.getString("password")) == true) {
						response.getWriter().write("login succeeded");
					}
					else {
						response.getWriter().write("Wrong username or password");
					}
				}
				else {
					response.getWriter().write("Wrong username or password");
				}
				
				rs.close();
			}
			else if (header.equals("get user class") == true) {
				String username = request.getParameter("username");
				File file = new File("/home/SurveyCorps/" + username + "/userclass.json");
				FileReader file_reader = new FileReader(file);
				BufferedReader reader = new BufferedReader(file_reader);
				StringBuilder str_builder = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
				    str_builder.append(line + '\n');
				}
				reader.close();
				String userclass = str_builder.toString();
				if (userclass.equals("") == true) {
					response.getWriter().write("No user class found");
				}
				else {
					response.getWriter().write(userclass);
				}
			}
			else if (header.equals("save user class") == true) {
				String username = request.getParameter("username");
				String userclass = request.getParameter("content");
				FileWriter writer = new FileWriter("/home/SurveyCorps/" + username + "/userclass.json");
	            writer.write(userclass);
	            writer.flush();
	            writer.close();
	            response.getWriter().write("saving succeeded");
			}
			else if (header.equals("publish survey") == true) {
				String username = request.getParameter("username");
				ResultSet rs = SurveyManagement.PublishedID();
				int sid = -1;
				while (rs.next() == true) {
					sid = rs.getInt(1);
				}
				
				int newid = sid + 1;
				SurveyManagement.SurveyPublish(username, Integer.toString(newid));
				response.getWriter().write(Integer.toString(newid));
			}
			else if (header.equals("delete survey") == true) {
				String sid = request.getParameter("surveyid");
				SurveyManagement.SurveyDelete(sid);
				response.getWriter().write("deleting succeeded");
			}
			else if (header.equals("engage survey") == true) {
				String username = request.getParameter("username");
				String sid = request.getParameter("surveyid");
				SurveyManagement.SurveyEngage(username, sid);
				response.getWriter().write("engaging succeeded");
			}
			else if (header.equals("search survey") == true) {
				String sid = request.getParameter("surveyid");
				ResultSet rs = SurveyManagement.SurveySearch(sid);
				if (rs.next() == true) {
					String username = rs.getString(2);
					File file = new File("/home/SurveyCorps/" + username + "/userclass.json");
					FileReader file_reader = new FileReader(file);
					BufferedReader reader = new BufferedReader(file_reader);
					StringBuilder str_builder = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
					    str_builder.append(line + '\n');
					}
					reader.close();
					String userclass = str_builder.toString();
					if (userclass.equals("") == true) {
						response.getWriter().write("No user class found");
					}
					else {
						response.getWriter().write(userclass);
					}
				}
				else {
					response.getWriter().write("no such survey");
				}
			}
			else {
				response.getWriter().write("bad post");
			}
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write("Server received your \'GET\' request.");
	}
}
