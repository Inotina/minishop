package by.enot.minishop.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.enot.minishop.Dao.DaoPurchase;
import by.enot.minishop.Entities.Purchase;
import by.enot.minishop.Exception.NotFoundInDbException;

/**
 * Servlet implementation class AdminPurchaseTableController
 * Admin Area controller. Create List of all purchases and forward it to render page.

 */
public class AdminPurchaseTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doTable(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doTable(request, response);
	}
	
	private void doTable(HttpServletRequest request, HttpServletResponse response){
		DaoPurchase dao = new DaoPurchase();
		List<Purchase> list = null;
		try {
			list = dao.getAllPurchase();
		} catch (NotFoundInDbException ignore) {
			// log coming soon
		}
		request.setAttribute("Purchase", list);
		try {
			request.getRequestDispatcher("adminpurchase.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
