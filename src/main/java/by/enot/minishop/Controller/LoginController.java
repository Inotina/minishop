package by.enot.minishop.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoUser;
import by.enot.minishop.Entities.User;
import by.enot.minishop.Exception.NotFoundInDbException;

/**
 * Servlet implementation class LoginController
 * Create user object for current session
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("login");
		User currrentUser = null;
		try {
			currrentUser = new DaoUser().getUser(userName);
		} catch (NotFoundInDbException e) {
			// log coming soon
		}
		request.getSession().setAttribute("User", currrentUser);
		response.sendRedirect("MainPageController");
	}

}
