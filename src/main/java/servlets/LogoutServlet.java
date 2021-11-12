package servlets;

import static utils.UrlMappings.LOGOUT_URL;
import static utils.UrlMappings.LOGIN_URL;

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


@WebServlet(name="logout", urlPatterns= "/" + LOGOUT_URL)
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsersDAO userDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(false);
		request.setAttribute("loginUrl", LOGIN_URL);
		request.getRequestDispatcher("WEB-INF/jsp/ferdig.jsp").forward(request, response);
	}
}
