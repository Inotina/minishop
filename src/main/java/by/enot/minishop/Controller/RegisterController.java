package by.enot.minishop.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoUser;
import by.enot.minishop.Exception.DbSaveInfoException;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Create row in Users database table.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoUser userDb = new DaoUser();
		try {
			userDb.setUser(request.getParameter("login"), request.getParameter("email"), request.getParameter("password"), "N");
		} catch (DbSaveInfoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("login").forward(request, response);;
	}

}
