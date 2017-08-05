package Dao;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import by.enot.minishop.Dao.DaoDbConfigReader;

public class DaoDbConfigReaderTest {

	@Test
	public void test() throws FileNotFoundException, IOException {
		DaoDbConfigReader db = new DaoDbConfigReader();
		for (int i = 0; i < 100; i++) {
//			assertNotNull(db.getDbProps());
		}
	}

}
