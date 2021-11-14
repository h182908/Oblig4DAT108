package servlets;

import static utils.UrlMappings.LOGOUT_URL;
import static utils.UrlMappings.LOGIN_URL;
import static utils.UrlMappings.USERLIST_URL;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


@WebServlet(name="userlist", urlPatterns= "/" + USERLIST_URL)
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsersDAO userDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<Users> users = userDAO.getUsers();
		
		if(LoginUtil.isLoggedIn(request) && users != null) {
			users = users.stream()
					.sorted(Comparator.comparing(Users::getFirst_name)
							.thenComparing(Comparator.comparing(Users::getLast_name)))
					.collect(Collectors.toList());

			request.setAttribute("userlist", users);
			request.setAttribute("logoutUrl", LOGOUT_URL);
			request.getRequestDispatcher("WEB-INF/jsp/deltagerliste.jsp").forward(request, response);
		}
		else {
			response.sendRedirect(LOGIN_URL);
		}
			
	}
}
