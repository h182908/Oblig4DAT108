package utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import database.Users;

public class LoginUtil {
	public static void logIn(HttpServletRequest request, Users user) {
		logOut(request);
		
		HttpSession session = request.getSession(true);
		session = request.getSession(true);
		session.setAttribute("user", user);
		session.setMaxInactiveInterval(8);
	}
	
	public static boolean isLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("username") != null;
	}
	
	public static void logOut(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if(session != null) {
			session.invalidate();
		}
	}
}
