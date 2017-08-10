package by.enot.minishop.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.enot.minishop.Entities.Product;
import by.enot.minishop.Exception.DbSaveInfoException;
import by.enot.minishop.Exception.NoConnectionToDbException;
import by.enot.minishop.Exception.NotFoundInDbException;

/*Dao for manage Products table in DataBase
 * 
 */
public class DaoProduct {
	
	public List<Product> getAllProducts() throws NotFoundInDbException {
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
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new NotFoundInDbException();
		}
		return resultList;
	}
	
	public Product getProduct(int id) throws NotFoundInDbException {
		Product item = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("select * from Products where id = ?")) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				item = new Product(result.getInt("Id"), result.getString("Pname"), result.getInt("Price"),
						result.getInt("Pcount"));
			}
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new NotFoundInDbException();
		}
		return item;
	}
	
	public Product getProduct(String name) throws NotFoundInDbException {
		Product item = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("select * from Products where Pname = ?")) {
			statement.setString(1, name);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				item = new Product(result.getInt("Id"), result.getString("Pname"), result.getInt("Price"),
						result.getInt("Pcount"));
			}
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new NotFoundInDbException();
		}
		return item;
	}

	public void setProduct(String name, int price, int count) throws DbSaveInfoException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon
						.prepareStatement("insert into Products (Pname, Price, Pcount) values( ?, ? , ?)")) {
			statement.setString(1, name);
			statement.setInt(2, price);
			statement.setInt(3, count);
			statement.executeUpdate();
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}

	public void updateProduct(int id, String name, int price, int count) throws DbSaveInfoException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon
						.prepareStatement("update Products set Pname = ?, Price = ?, Pcount = ? where Id = ?")) {
			statement.setString(1, name);
			statement.setInt(2, price);
			statement.setInt(3, count);
			statement.setInt(4, id);
			statement.executeUpdate();
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}

	public void removeProduct(int id) throws DbSaveInfoException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("delete from Products where Id = ?")) {
			statement.setInt(1, id);
			statement.executeUpdate();
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}

	public void removeAll() throws DbSaveInfoException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				Statement statement = currentCon.createStatement()) {
			statement.executeUpdate("delete from Products");
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}
}
