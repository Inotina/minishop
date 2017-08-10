package by.enot.minishop.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.enot.minishop.Entities.Purchase;
import by.enot.minishop.Exception.DbSaveInfoException;
import by.enot.minishop.Exception.NoConnectionToDbException;
import by.enot.minishop.Exception.NotFoundInDbException;

/*Dao for manage Purchase table in DataBase
 * 
 */
public class DaoPurchase {
	
	public List<Purchase> getAllPurchase() throws NotFoundInDbException {
		List<Purchase> resultList = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				Statement allStatement = currentCon.createStatement();
				ResultSet result = allStatement.executeQuery("select * from Purchase")) {
			resultList = new ArrayList<>();
			while (result.next()) {
				try {
					resultList.add(new Purchase(result.getInt("PurchaseId"), result.getInt("ClientId"), result.getString("Phone"), result.getString("Adress"), result.getString("Date"), result.getString("Products")));
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
	
	public Purchase getPurchase(int purchaseId) throws NotFoundInDbException {
		Purchase item = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("select * from Purchase where PurchaseId = ?")) {
			statement.setInt(1, purchaseId);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				item = new Purchase(result.getInt("PurchaseId"), result.getInt("ClientId"), result.getString("Phone"), result.getString("Adress"), result.getString("Date"), result.getString("Products"));
			}
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new NotFoundInDbException();
		}
		return item;
	}

	public void setPurchase(int clientId, String phone, String adress, Date date, String products) throws DbSaveInfoException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon
						.prepareStatement("insert into Purchase (ClientId, Phone, Adress, Date, Products) values( ?, ? , ?, ?, ?)")) {
			statement.setInt(1, clientId);
			statement.setString(2, phone);
			statement.setString(3, adress);
			statement.setString(4, new Date().toString());
			statement.setString(5, products);
			statement.executeUpdate();
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}

	public void updatePurchase(int purchaseId, int clientId, String phone, String adress, String products) throws DbSaveInfoException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon
						.prepareStatement("update Purchase set ClientId = ?, Phone = ?, Adress = ?, Products = ? where PurchaseId = ?")) {
			statement.setInt(1, clientId);
			statement.setString(2, phone);
			statement.setString(3, adress);
			statement.setString(4, products);
			statement.setInt(5, purchaseId);
			statement.executeUpdate();
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}

	public void removePurchase(int purchaseId) throws DbSaveInfoException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("delete from Purchase where PurchaseId = ?")) {
			statement.setInt(1, purchaseId);
			statement.executeUpdate();
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}
}
