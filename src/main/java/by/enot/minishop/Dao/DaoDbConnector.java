package by.enot.minishop.Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import by.enot.minishop.Exception.NoConnectionToDbException;

/*Connect to Database
 * 
 */

public final class DaoDbConnector {
	
	private DaoDbConnector(){}

	public static Connection getConnection() throws NoConnectionToDbException {
		//standart Driver Manager
		/*DaoDbConfigReader propsReader = new DaoDbConfigReader();
		Properties props = propsReader.getDbProps();
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String url = props.getProperty("dburl");
		try {
			Class.forName("com.example.jdbc.Driver");
		} catch (ClassNotFoundException ignore) {
			// TODO Auto-generated catch block
		}
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
*/		
		//Using tomcat pool
		Connection conn = null;
		try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/minishop");
		conn = ds.getConnection();
		} catch (NamingException|SQLException e) {
			// log coming soon
			throw new NoConnectionToDbException(); 
		}

		return conn;
	}
	
}
