package by.enot.minishop.Dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*Connect to Database
 * 
 */

public final class DaoDbConnector {
	
	private DaoDbConnector(){}

	public static Connection getConnection() throws SQLException, NamingException {
		//standart Driver Manager
//		DaoDbConfigReader propsReader = new DaoDbConfigReader();
//		Properties props = propsReader.getDbProps();
//		String user = props.getProperty("user");
//		String password = props.getProperty("password");
//		String url = props.getProperty("dburl");
//		try {
//			Class.forName("com.example.jdbc.Driver");
//		} catch (ClassNotFoundException ignore) {
//			// TODO Auto-generated catch block
//		}
//		Connection connection = DriverManager.getConnection(url, user, password);
//		return connection;
		
		//Using tomcat pool
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/minishop");
		Connection conn = ds.getConnection();

		return conn;
	}
	
}
