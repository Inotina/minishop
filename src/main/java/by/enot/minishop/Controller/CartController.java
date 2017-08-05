package by.enot.minishop.Controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BasketController
 * Cart is client side implementation(Cookies) 
 * Control Add/Remove/Clear cart operation
 */
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Integer> cart = CartSaveLoad.cartMapFromCookie(request.getCookies());
		String command = request.getParameter("cart");
		String productName = request.getParameter("name");
		if (command == null) {

		} else if (command.equals("add")) {
			if (!cart.containsKey(productName)) {
				cart.put(productName, 1);
			} else {
				cart.put(productName, cart.get(productName) + 1);
			}
		} else if (command.equals("remove")) {
			if (cart.get(productName) <= 1) {
				cart.remove(productName);
			} else {
				cart.put(productName, cart.get(productName) - 1);
			}

		}
		Cookie cookie = new Cookie("cart", CartSaveLoad.toStringFromCartMap(cart));
		if (command.equals("clear")) {
			cookie.setMaxAge(0);
		} else {
			cookie.setMaxAge(60 * 60 * 24 * 7);
		}
		response.addCookie(cookie);
		response.sendRedirect(request.getHeader("Referer"));
	}

}
