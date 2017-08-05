package by.enot.minishop.Filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AdminProductFilter
 * Admin Area filter. Check Update/Add form fields for Products table in DataBase. 
 */
public class AdminProductFilter extends AbstractFilter {

	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		//check what button was pressed
		String command = request.getParameter("bt");
		if (command != null && command.equals("Add")) {
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			String count = request.getParameter("count");
			checkFieldsAndDo(name, price, count, "addname", "addprice", "addcount", "nameNotValid", "priceNotValid", "countNotValid", request, response, chain);
		}else if (command != null && command.equals("Update")) {
			String name = request.getParameter("upname");
			String price = request.getParameter("upprice");
			String count = request.getParameter("upcount");
			request.setAttribute("target", request.getParameter("target"));
			request.setAttribute("upid", request.getParameter("hupid"));
			checkFieldsAndDo(name, price, count, "upname", "upprice", "upcount", "upnameNotValid", "uppriceNotValid", "upcountNotValid", request, response, chain);	
		}else {
			try {
				chain.doFilter(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * check fields and make desigion
	 * if field is ok, set it's value to parametr to prevent user from typing it again if not all field are ok.
	 * @param name, price, count - values from form fields.
	 * outname, outprice, outcount - names of attributes to set for autotyping fields.
	 * errname, errprice, errcount - names of attributes to set for error messages near fields
	 */
	private void checkFieldsAndDo(String name, String price, String count, String outname, String outprice, String outcount, String errname, String errprice, String errcount,
			HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
			boolean isValid = false;
			
			if (name != null && !name.equals("")) {
				isValid = true;
				request.setAttribute(outname, name);
			}else {
				request.setAttribute(errname, "Empty field name");
			}
			
			if (price != null) {
				try {
					int pr = Integer.parseInt(price);
					if (pr < 0) {
						isValid = false;
						request.setAttribute(errprice, "Negative price not allowed");
					}else {
						request.setAttribute(outprice, price);
					}
				}catch (NumberFormatException e) {
					isValid = false;
					request.setAttribute(errprice, "Not a number");
				}
			}else {
				isValid = false;
			}
			
			if (count != null) {
				try {
					int st = Integer.parseInt(count);
					if (st < 0) {
						isValid = false;
						request.setAttribute(errcount, "Negative count not allowed");
					}else {
						request.setAttribute(outcount, count);
					}
				}catch (NumberFormatException e) {
					isValid = false;
					request.setAttribute(errcount, "Not a number");
				}
			}else {
				isValid = false;
			}
			
			//make desigion
			if (isValid) {
				try {
					chain.doFilter(request, response);
				} catch (IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					request.getRequestDispatcher("adminallproducts").forward(request, response);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
				
}
