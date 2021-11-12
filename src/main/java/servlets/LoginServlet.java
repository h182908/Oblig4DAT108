package servlets;

import static utils.UrlMappings.LOGIN_URL;
import static utils.UrlMappings.USERLIST_URL;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Users;
import database.UsersDAO;
import utils.LoginUtil;
import utils.Password;
import utils.PasswordUtil;


@WebServlet(name="login", urlPatterns= "/" + LOGIN_URL)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsersDAO userDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("WEB-INF/jsp/logginn.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("mobil");
		String password = request.getParameter("passord");
		
		if(username != null && password != null) {
			Users user = userDAO.getUser(username);
			
			if(user != null) {
				Password pass = user.getPassword();
				if(pass != null && PasswordUtil.validateWithSalt(password, pass.getSalt(), pass.getHash())) {
					LoginUtil.logIn(request, user);
					
					response.sendRedirect(USERLIST_URL);
				}
				else {
					request.getSession().setAttribute("error", "Ugyldig bruker/passord");
					response.sendRedirect(LOGIN_URL);
				}
			}
			else {
				request.getSession().setAttribute("error", "Ugyldig bruker/passord");
				response.sendRedirect(LOGIN_URL);
			}
		}
	}

}
