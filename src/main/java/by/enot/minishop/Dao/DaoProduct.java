package by.enot.minishop.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import by.enot.minishop.Entities.Product;

/*Dao for manage Products table in DataBase
 * 
 */
public class DaoProduct {
	
	public List<Product> getAllProducts() throws SQLException, NamingException {
		List<Product> resultList = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				Statement allStatement = currentCon.createStatement();
				ResultSet result = allStatement.executeQuery("select * from Products")) {
			resultList = new ArrayList<>();
			while (result.next()) {
				try {
					resultList.add(new Product(result.getInt("Id"), result.getString("Pname"), result.getInt("Price"),
							result.getInt("Pcount")));
				} catch (SQLException e) {
					// logging if current cortege is lost but doesn't break while cycle
					System.out.println("Problem with current cortege convert in Product");
				}
			}
		}
		return resultList;
	}
	
	public Product getProduct(int id) throws SQLException, NamingException {
		Product item = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("select * from Products where id = ?")) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				item = new Product(result.getInt("Id"), result.getString("Pname"), result.getInt("Price"),
						result.getInt("Pcount"));
			}
		}
		return item;
	}
	
	public Product getProduct(String name) throws SQLException, NamingException {
		Product item = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("select * from Products where Pname = ?")) {
			statement.setString(1, name);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				item = new Product(result.getInt("Id"), result.getString("Pname"), result.getInt("Price"),
						result.getInt("Pcount"));
			}
		}
		return item;
	}

	public void setProduct(String name, int price, int count) throws SQLException, NamingException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon
						.prepareStatement("insert into Products (Pname, Price, Pcount) values( ?, ? , ?)")) {
			statement.setString(1, name);
			statement.setInt(2, price);
			statement.setInt(3, count);
			statement.executeUpdate();
		}
	}

	public void updateProduct(int id, String name, int price, int count) throws SQLException, NamingException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon
						.prepareStatement("update Products set Pname = ?, Price = ?, Pcount = ? where Id = ?")) {
			statement.setString(1, name);
			statement.setInt(2, price);
			statement.setInt(3, count);
			statement.setInt(4, id);
			statement.executeUpdate();
		}
	}

	public void removeProduct(int id) throws SQLException, NamingException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("delete from Products where Id = ?")) {
			statement.setInt(1, id);
			statement.executeUpdate();
		}
	}

	public void removeAll() throws SQLException, NamingException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				Statement statement = currentCon.createStatement()) {
			statement.executeUpdate("delete from Products");
		}
	}
}
