package servlets;

import static utils.UrlMappings.CONFIRMATION_URL;
import static utils.UrlMappings.LOGIN_URL;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.LoginUtil;

@WebServlet(name = "conf", urlPatterns = "/" + CONFIRMATION_URL)
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(LoginUtil.isLoggedIn(request))
			request.getRequestDispatcher("WEB-INF/jsp/paameldingsbekreftelse.jsp").forward(request, response);
		else
			response.sendRedirect(LOGIN_URL);
	}
}