package by.enot.minishop.Filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoUser;
import by.enot.minishop.Entities.User;

/**
 * Servlet Filter implementation class LoginFilter
 * Check login form field
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
		}else {
			request.setAttribute("login", name);
		}
		
		if (password == null || password.equals("")) {
			isValidUser = false;
			request.setAttribute("invalidPassword", "Empty password field");
		}
		User currentUser = null;
		try {
			if (!name.equals("")) {
				currentUser = new DaoUser().getUser(name);
			}
		} catch (SQLException | NamingException e) {
			// Bad exception handling, need own exception like NoSuchUserInDb
			e.printStackTrace();
		}
		if (currentUser == null) {
			isValidUser = false;
			request.setAttribute("invalidLogin", "Such login doesn't exist");
		}else if (!currentUser.getPassword().equals(password)) {
			isValidUser = false;
			request.setAttribute("invalidPassword", "Incorrect Password");
		}
		//make desigion
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
