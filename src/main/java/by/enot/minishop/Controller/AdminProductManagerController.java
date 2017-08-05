package by.enot.minishop.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoProduct;
import by.enot.minishop.Entities.Product;

/**
 * Servlet implementation class AdminProductManagerController
 * Admin Area controller. Used for CRUD operations on Products database table.
 */
public class AdminProductManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//fill update/delete form. Select operation
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DaoProduct dao = new DaoProduct();
		String productId = request.getParameter("row");
		if (productId != null) {
			int id = Integer.parseInt(productId);
			Product target = null;
			try {
				target = dao.getProduct(id);
			} catch (SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("target", "Y");
			request.setAttribute("upid", target.getId());
			request.setAttribute("upname", target.getName());
			request.setAttribute("upprice", target.getPrice());
			request.setAttribute("upcount", target.getCount());
		}
		request.getRequestDispatcher("adminallproducts").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      Add/Update/Delete row from database
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DaoProduct dao = new DaoProduct();

		String command = request.getParameter("bt");
		if (command != null && command.equals("Add")) {
			try {
				dao.setProduct(request.getParameter("name"), Integer.parseInt(request.getParameter("price")),
						Integer.parseInt(request.getParameter("count")));
				request.setAttribute("addedmessage", "Product successfully added");
			} catch (NumberFormatException | SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command != null && command.equals("Update")) {
			try {
				dao.updateProduct(Integer.parseInt(request.getParameter("hupid")), request.getParameter("upname"),
						Integer.parseInt(request.getParameter("upprice")),
						Integer.parseInt(request.getParameter("upcount")));
				request.setAttribute("updatedmessage", "Product successfully updated");
			} catch (NumberFormatException | SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (command != null && command.equals("Delete")) {
			try {
				dao.removeProduct(Integer.parseInt(request.getParameter("hupid")));
				request.setAttribute("deletedmessage", "Product successfully deleted");
			} catch (NumberFormatException | SQLException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher("adminallproducts").forward(request, response);
	}

}
