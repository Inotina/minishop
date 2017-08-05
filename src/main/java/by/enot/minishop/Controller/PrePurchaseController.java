package by.enot.minishop.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrePurchaseController
 * Get cart from cookies, convert it  and send it to purchase page.
 */
public class PrePurchaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Integer> cart = CartSaveLoad.cartMapFromCookie(request.getCookies());
		request.setAttribute("Cart", cart);
		request.getRequestDispatcher("purchase.jsp").forward(request, response);
		
	}

	}
