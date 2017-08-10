package by.enot.minishop.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoProduct;
import by.enot.minishop.Entities.Product;
import by.enot.minishop.Exception.NotFoundInDbException;

/**
 * Servlet implementation class ProductController
 */
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Get cart and selected product information and send it to product page for render
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product currentProduct = null;
		try {
			currentProduct = new DaoProduct().getProduct(id);
		} catch (NotFoundInDbException e) {
			// log coming soon
			response.sendRedirect("MainPageController");
			return;
		}
		request.setAttribute("Product", currentProduct);
		request.setAttribute("Cart", CartSaveLoad.cartMapFromCookie(request.getCookies()));
		request.getRequestDispatcher("product.jsp").forward(request, response);
	}

}
