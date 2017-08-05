 package by.enot.minishop.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoProduct;
import by.enot.minishop.Entities.Product;

/**
 * Servlet implementation class MainPageController
 * Get all products from database for rendering main page
 */
//@WebServlet("/MainPageController")
public class MainPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			List<Product> list = new DaoProduct().getAllProducts();
			request.setAttribute("Products", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.getRequestDispatcher("/index.jsp").forward(request,  response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
