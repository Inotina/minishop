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
 * Check register form fields.
 */
public class RegisterFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		String name = request.getParameter("login");
		String password = request.getParameter("password");
		String passwordTwo = request.getParameter("passwordtwo");
		String email = request.getParameter("email");
		boolean isValidUser = true;
		//check empty fields
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
		if (email == null || email.equals("")) {
			isValidUser = false;
			request.setAttribute("invalidEmail", "Empty email field");
		}else {
			request.setAttribute("email", email);
		}
		//check if login is in use
		User loginUser = null;
		try {
			loginUser = new DaoUser().getUser(name);
		} catch (SQLException | NamingException e) {
			// bad exception handling. need own exception something like NOSuchUserInBaseException.
			e.printStackTrace();
		}
		if (loginUser != null) {
			isValidUser = false;
			request.setAttribute("invalidLogin", "Such login is in use");
		}
		//check if email is in use
		User emailUser = null;
		try {
			emailUser = new DaoUser().getUser(name);
		} catch (SQLException | NamingException e) {
			// bad exception handling. need own exception something like NOSuchUserInBaseException.
			e.printStackTrace();
		}
		if (emailUser != null && emailUser.getEmail().equals(email)) {
			isValidUser = false;
			request.setAttribute("invalidEmail", "Email is in use");
		}
		//check password length and equality
		if (password.length() < 8) {
			isValidUser = false;
			request.setAttribute("invalidPassword", "Password is too short");
		}else if (!password.equals(passwordTwo)) {
			isValidUser = false;
			request.setAttribute("invalidPasswordTwo", "Password doesn't match");
		}
		//make desigion
		if (isValidUser) {
			try {
				chain.doFilter(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				request.getRequestDispatcher("register.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}
 

}
