package by.enot.minishop.Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*Read DataBase config from file 
 * 
 */

public class DaoDbConfigReader {
	private Properties dbProps;
	private final String dbConfigPath = "dbconf/db.properties";
	
	protected Properties getDbProps() throws FileNotFoundException, IOException {
		if (dbProps != null) return dbProps;
		else {
			try(InputStream input = getClass().getResourceAsStream(dbConfigPath)){
				dbProps = new Properties();
				dbProps.load(input);
			}
			return dbProps;
		}
				
	}
	
}
