package by.enot.minishop.Filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Controller.CartSaveLoad;
import by.enot.minishop.Dao.DaoProduct;
import by.enot.minishop.Exception.NotFoundInDbException;

/**
 * Servlet Filter implementation class PurchaseFilter
 * Check if Cart is not emty and purchase form fields.
 */
public class PurchaseFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		Map<String, Integer> cart = CartSaveLoad.cartMapFromCookie(request.getCookies());
		//check cart
		if (cart.isEmpty()) {
			isValid = false;
			request.setAttribute("emptyCart", "Your cart is empty");
		}
		//check fields
		try {
			Integer.parseInt(request.getParameter("clientId"));
			request.setAttribute("clientId", request.getParameter("clientId"));
		} catch (NumberFormatException e) {
			isValid = false;
			request.setAttribute("idNotValid", "Invalid Id number");
		}
		if (request.getParameter("phone") != null && request.getParameter("phone").equals("")) {
			isValid = false;
			request.setAttribute("phoneNotValid", "Empty phone number");
		} else {
			request.setAttribute("phone", request.getParameter("phone"));
		}
		if (request.getParameter("adress") != null && request.getParameter("adress").equals("")) {
			isValid = false;
			request.setAttribute("adressNotValid", "Empty adress");
		} else {
			request.setAttribute("adress", request.getParameter("adress"));
		}
		//check if it's enough product in stock for purchase. If not - set product count in cart to max available
		DaoProduct dao = new DaoProduct();
		for (Iterator<Entry<String, Integer>> it = cart.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			try {
				int count = dao.getProduct(entry.getKey()).getCount();
				if (count < entry.getValue()) {
					isValid = false;
					if (count == 0) {
						it.remove();
					} else {
						cart.put(entry.getKey(), count);
						request.setAttribute("stockmessage", "Not enough products in stock. Try again.");
					}
				}
			} catch (NotFoundInDbException ignore) {
				//log coming soon
			}
		}
		//make desigion
		if (isValid) {
			try {
				chain.doFilter(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			request.setAttribute("Cart", cart);
			Cookie cookie = new Cookie("cart", CartSaveLoad.toStringFromCartMap(cart));
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			try {
				request.getRequestDispatcher("purchase.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
