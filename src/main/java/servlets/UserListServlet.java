package servlets;

import static utils.UrlMappings.LOGOUT_URL;
import static utils.UrlMappings.LOGIN_URL;
import static utils.UrlMappings.USERLIST_URL;

import java.io.IOException;
import java.util.List;

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
		Users user = (Users) request.getSession().getAttribute("user");
		
		List<Users> users = userDAO.getUsers();
		
		if(user != null && users != null) {
			users.sort((Users a, Users b) -> {
				char A = a.getFirst_name().charAt(0);
				char B = b.getFirst_name().charAt(0);
				if(A == B) {
					A = a.getLast_name().charAt(0);
					B = b.getLast_name().charAt(0);
				}
				return A > B ? 1 : -1;
			});
			
			request.setAttribute("userlist", users);
			request.setAttribute("logoutUrl", LOGOUT_URL);
			request.getRequestDispatcher("WEB-INF/jsp/deltagerliste.jsp").forward(request, response);
		}
		else {
			response.sendRedirect(LOGIN_URL);
		}
			
	}
}
