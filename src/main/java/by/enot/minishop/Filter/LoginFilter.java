package by.enot.minishop.Filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoUser;
import by.enot.minishop.Entities.User;
import by.enot.minishop.Exception.NotFoundInDbException;

/**
 * Servlet Filter implementation class LoginFilter Check login form field
 */
public class LoginFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		String name = request.getParameter("login");
		String password = request.getParameter("password");
		boolean isValidUser = true;

		if (name == null || name.equals("")) {
			isValidUser = false;
			request.setAttribute("invalidLogin", "Empty login field");
		} else {
			request.setAttribute("login", name);
		}

		if (password == null || password.equals("")) {
			isValidUser = false;
			request.setAttribute("invalidPassword", "Empty password field");
		}
		try {
			User currentUser = new DaoUser().getUser(name);
			if (!currentUser.getPassword().equals(password)) {
				isValidUser = false;
				request.setAttribute("invalidPassword", "Incorrect Password");
			}
		} catch (NotFoundInDbException e1) {
			// log coming soon
			isValidUser = false;
			request.setAttribute("invalidLogin", "Such login doesn't exist");
		}
		// make desigion
		if (isValidUser) {
			try {
				chain.doFilter(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
