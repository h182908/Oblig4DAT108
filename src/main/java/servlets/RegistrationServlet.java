package servlets;

import static utils.UrlMappings.REGISTRATION_URL;
import static utils.UrlMappings.CONFIRMATION_URL;
import utils.Schema;
import utils.LoginUtil;
import utils.Password;
import utils.PasswordUtil;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Users;
import database.UsersDAO;

@WebServlet(name = "reg", urlPatterns = "/" + REGISTRATION_URL)
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsersDAO userDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/paameldingsskjema.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Schema schema = new Schema(request);
		
		if (schema.isValidated()) {			
			Password password = Password.createPassword(schema.password);
			Users user = new Users(schema.first_name, schema.last_name, schema.phone_number, password, schema.gender);
			
			if(userDAO.saveNewUser(user)) {
				LoginUtil.logIn(request, user);
				
				request.getSession().removeAttribute("schema");
				
				response.sendRedirect(CONFIRMATION_URL);
			}
			else {
				response.sendRedirect(REGISTRATION_URL);
			}
			
		} else {
			request.getSession().setAttribute("schema", schema);
			response.sendRedirect(REGISTRATION_URL);
		}
	}
}
