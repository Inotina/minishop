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
 * Servlet implementation class AdminProductsTableController
 * Admin Area controller. Create List of all products and forward it to render page.
 */
public class AdminProductTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doTable(request, response);
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doTable(request, response);
	
	}

	private void doTable(HttpServletRequest request, HttpServletResponse response) {

		DaoProduct dao = new DaoProduct();
		List<Product> list = null;
		try {
			list = dao.getAllProducts();
		} catch (SQLException | NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("Products", list);
		try {
			request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
