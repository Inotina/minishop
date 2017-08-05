package by.enot.minishop.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoProduct;
import by.enot.minishop.Dao.DaoPurchase;
import by.enot.minishop.Entities.Product;

/**
 * Servlet implementation class PurchaseController
 */
public class PurchaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *Create purchase row in database based on current cart
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DaoPurchase purchase = new DaoPurchase();
		Map<String, Integer> cart = CartSaveLoad.cartMapFromCookie(request.getCookies());
		String products = CartSaveLoad.toStringFromCartMap(cart);
		try {
			purchase.setPurchase(Integer.parseInt(request.getParameter("clientId")), request.getParameter("phone"), request.getParameter("adress"), new Date(), products);
		} catch (NumberFormatException | SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//update "count" column in database for each product in cart 
		DaoProduct daoP = new DaoProduct();
		for (Entry<String, Integer> entry : cart.entrySet()) {
			try {
				Product curr = daoP.getProduct(entry.getKey());
				daoP.updateProduct(curr.getId(), curr.getName(), curr.getPrice(), curr.getCount() - entry.getValue());
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//clear cart
		Cookie cookie = new Cookie("cart", products);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		response.sendRedirect("ThanksForPurchase.html");
	}

}
