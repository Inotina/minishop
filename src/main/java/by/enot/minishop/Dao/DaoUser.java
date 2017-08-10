package by.enot.minishop.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.enot.minishop.Entities.User;
import by.enot.minishop.Exception.DbSaveInfoException;
import by.enot.minishop.Exception.NoConnectionToDbException;
import by.enot.minishop.Exception.NotFoundInDbException;

/*Dao for manage user table in DataBase
 * 
 */

public class DaoUser {
	
	public List<User> getAllUsers() throws NotFoundInDbException {
		List<User> resultList = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				Statement allStatement = currentCon.createStatement();
				ResultSet result = allStatement.executeQuery("select * from Users")) {
			resultList = new ArrayList<>();
			while (result.next()) {
				try {
					resultList.add(new User(result.getInt("UserId"), result.getString("UserName"), result.getString("UserEmail"), result.getString("UserPassword"), result.getString("IsAdmin")));
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
	
	public User getUser(String name) throws NotFoundInDbException {
		User item = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("select * from Users where UserName = ?")) {
			statement.setString(1, name);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				item = new User(result.getInt("UserId"), result.getString("UserName"), result.getString("UserEmail"), result.getString("UserPassword"), result.getString("IsAdmin"));
			}
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new NotFoundInDbException();
		}
		return item;
	}
	
	public User getEmail(String email) throws NotFoundInDbException {
		User item = null;
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("select * from Users where UserEmail = ?")) {
			statement.setString(1, email);
			try (ResultSet result = statement.executeQuery()) {
				result.next();
				item = new User(result.getInt("UserId"), result.getString("UserName"), result.getString("UserEmail"), result.getString("UserPassword"), result.getString("IsAdmin"));
			}
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new NotFoundInDbException();
		}
		return item;
	}

	public void setUser(String name, String email, String password, String isAdmin) throws DbSaveInfoException{
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon
						.prepareStatement("insert into Users (UserName, UserEmail, UserPassword, IsAdmin) values( ?, ? , ?, ?)")) {
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setString(3, password);
			statement.setString(4, isAdmin);
			statement.executeUpdate();
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}

	public void updateUser(int id, String name, String email, String password, String isAdmin) throws DbSaveInfoException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon
						.prepareStatement("update Users set UserName = ?, UserEmail = ?, UserPassword = ?, isAdmin = ? where UserId = ?")) {
			statement.setString(1, name);
			statement.setString(2, email);
			statement.setString(3, password);
			statement.setString(4, isAdmin);
			statement.setInt(5, id);
			statement.executeUpdate();
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}

	public void removeUser(int id) throws DbSaveInfoException {
		try (Connection currentCon = DaoDbConnector.getConnection();
				PreparedStatement statement = currentCon.prepareStatement("delete from Users where UserId = ?")) {
			statement.setInt(1, id);
			statement.executeUpdate();
		}catch ( SQLException | NoConnectionToDbException e) {
			//log coming soon
			throw new DbSaveInfoException();
		}
	}
}
