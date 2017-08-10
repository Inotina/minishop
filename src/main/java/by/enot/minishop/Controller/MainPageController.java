 package by.enot.minishop.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoProduct;
import by.enot.minishop.Entities.Product;
import by.enot.minishop.Exception.NotFoundInDbException;

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
		} catch (NotFoundInDbException e) {
			//log coming soon
		}
		try {
			request.getRequestDispatcher("/index.jsp").forward(request,  response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
