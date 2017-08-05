package by.enot.minishop.Filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AdminPurchaseFilter
 * Admin Area filter. Check Update form fields for Purchase table in DataBase.
 */
public class AdminPurchaseFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		// check what button was pressed
		
		String command = request.getParameter("bt");
		if (command != null && command.equals("Update")) {
			String clientId = request.getParameter("upclientid");
			String phone = request.getParameter("upphone");
			String adress = request.getParameter("upadress");
			String products = request.getParameter("upproducts");
			request.setAttribute("target", request.getParameter("target"));
			request.setAttribute("uppurchaseid", request.getParameter("huppurchaseid"));
			checkFieldsAndDo(clientId, phone, adress, products, "upclientid", "upphone", "upadress", "upproducts",
					"upclientIdNotValid", "upphoneNotValid", "upadressNotValid", "upproductsNotValid", request,
					response, chain);
		} else {
			try {
				chain.doFilter(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * check fields and make desigion if field is ok set it's value to parametr to
	 * prevent user from typing it again if not all field are ok.
	 * 
	 * @param clientid, phone, adress, products - values from form fields.
	 * outclientid, outphone, outadress, outproducts - names of attributes to set
	 * for autotyping fields. errclientid, errphone, erradress, errproducts - names
	 * of attributes to set for error messages near fields
	 */
	private void checkFieldsAndDo(String clientid, String phone, String adress, String products, String outclientid,
			String outphone, String outadress, String outproducts, String errclientid, String errphone,
			String erradress, String errproducts, HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) {
		boolean isValid = false;
		request.setAttribute("hupdate", request.getParameter("hupdate"));

		if (clientid != null) {
			try {
				int id = Integer.parseInt(clientid);
				if (id > 0) {
					isValid = true;
					request.setAttribute(outclientid, clientid);
				}
			} catch (NumberFormatException e) {
				request.setAttribute(errclientid, "Empty clientid");
			}
		} else {
			request.setAttribute(errclientid, "Empty clientid");
		}

		if (phone != null && !phone.equals("")) {
			request.setAttribute(outphone, phone);
		} else {
			isValid = false;
			request.setAttribute(errphone, "Empty phone");
		}

		if (adress != null && !adress.equals("")) {
			request.setAttribute(outadress, adress);
		} else {
			isValid = false;
			request.setAttribute(erradress, "Empty adress");
		}

		if (products != null && !products.equals("")) {
			request.setAttribute(outproducts, products);
		} else {
			isValid = false;
			request.setAttribute(errproducts, "Empty products");
		}
		
		// make desigion
		if (isValid) {
			try {
				chain.doFilter(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				request.getRequestDispatcher("adminallpurchase").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
