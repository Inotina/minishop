package by.enot.minishop.Filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Entities.User;

/**
 * Servlet Filter implementation class IsAdminFilter
 * Check user permition to access Admin Area.
 */
public class IsAdminFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		User user = (User) request.getSession().getAttribute("User");
		boolean isAdmin = false;
		//User is admin if his status is "Y"(mean "Yes", database column "isAdmin")
		if (user != null && user.getStatus().equals("Y")) {
			isAdmin = true;
		}
		//make desigion
		if (isAdmin) {
			try {
				chain.doFilter(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				response.sendRedirect("../nopermission.html");;;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

   }
