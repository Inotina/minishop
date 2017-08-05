package by.enot.minishop.Controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.NamingException;
import javax.servlet.http.Cookie;

import by.enot.minishop.Dao.DaoProduct;

/*Cart converter. Server cart is HasMap, clint cart is string.
 * 
 */
public class CartSaveLoad {
	
	//convert cookie to hashMap
	public static HashMap<String, Integer> cartMapFromCookie(Cookie[] cookies) {
		if (cookies.length <= 1)
			return new HashMap<String, Integer>();
		String str = null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("cart")) {
				str = cookies[i].getValue();
				break;
			}
		}
		if (str == null)
			return new HashMap<String, Integer>();
		//parse string
		List<String> parseList = Arrays.asList(str.split("&"));
		HashMap<String, Integer> cartUnsafe = new HashMap<>();
		for (String cur : parseList) {
			String[] temp = cur.split(":");
			if (temp.length < 2)
				break;
			cartUnsafe.put(temp[0], Integer.parseInt(temp[1]));
		}
		HashMap<String, Integer> result = new HashMap<>();
		//check if all product in cart are in database, remove product from cart if not.
		DaoProduct dao = new DaoProduct();
		for (Entry<String, Integer> entry : cartUnsafe.entrySet()) {
			try {
				dao.getProduct(entry.getKey());
				result.put(entry.getKey(), entry.getValue());
			} catch (SQLException | NamingException ignore) {
				// TODO Auto-generated catch block
				// need to log and ignore
			}

		}
		return result;
	}
	//convert HasMap to String
	public static String toStringFromCartMap(Map<String, Integer> map) {
		StringBuilder result = new StringBuilder();
		for (Entry<String, Integer> entry : map.entrySet()) {
			result.append(entry.getKey());
			result.append(":");
			result.append(entry.getValue().toString());
			result.append("&");
		}
		return result.toString();
	}
}
