package by.enot.minishop.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoPurchase;
import by.enot.minishop.Entities.Purchase;
import by.enot.minishop.Exception.DbSaveInfoException;
import by.enot.minishop.Exception.NotFoundInDbException;

/**
 * Servlet implementation class AdminPurchaseManagerController
 * Admin Area controller. Used for CRUD operations on Purchase database table.
 */
public class AdminPurchaseManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//fill update\delete form. Select operation
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DaoPurchase dao = new DaoPurchase();
		String purchaseId = request.getParameter("row");
		if (purchaseId != null) {
			int id = Integer.parseInt(purchaseId);
			Purchase target = null;
			try {
				target = dao.getPurchase(id);
			} catch (NotFoundInDbException e) {
				// log coming soon
				request.setAttribute("noSuchPurchase", "No such purchase in database");

			}
			request.setAttribute("target", "Y");
			request.setAttribute("uppurchaseid", target.getPurchaseId());
			request.setAttribute("upclientid", target.getUserId());
			request.setAttribute("upphone", target.getPhone());
			request.setAttribute("upadress", target.getAdress());
			request.setAttribute("upproducts", target.getProducts());
			request.setAttribute("hupdate", target.getDate());
		}
		request.getRequestDispatcher("adminallpurchase").forward(request, response);
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      Add/Update/Delete row from database
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DaoPurchase dao = new DaoPurchase();

		String command = request.getParameter("bt");
		if (command != null && command.equals("Update")) {
			try {
				dao.updatePurchase(Integer.parseInt(request.getParameter("huppurchaseid")), Integer.parseInt(request.getParameter("upclientid")),
						request.getParameter("upphone"), request.getParameter("upadress"), request.getParameter("upproducts"));
				request.setAttribute("updatedmessage", "Purchase successfully updated");
			} catch (DbSaveInfoException e) {
				// log coming soon
				request.setAttribute("errPurchaseMsg", "Purchase wasn't updated. Try again.");
			}
		} else if (command != null && command.equals("Delete")) {
			try {
				dao.removePurchase(Integer.parseInt(request.getParameter("huppurchaseid")));
				request.setAttribute("deletedmessage", "Purchase successfully deleted");
			} catch (DbSaveInfoException e) {
				// log coming soon
				request.setAttribute("errPurchaseMsg", "Purchase wasn't deleted. Try again.");
			}
		}

		request.getRequestDispatcher("adminallpurchase").forward(request, response);
	}

}
