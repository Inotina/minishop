package Dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.Test;

import by.enot.minishop.Dao.DaoProduct;
import by.enot.minishop.Entities.Product;

public class DaoProductTest {

	private DaoProduct dao = new DaoProduct();

	@Test
	public void testGetAll() throws SQLException, NamingException {
		List<Product> list = dao.getAllProducts();
		assertNotNull(list);
	}
	@Test
	public void testGetOneById() throws SQLException, NamingException {
		Product pr = dao.getProduct(7);
		assertNotNull(pr);
	}
	@Test
	public void testGetOneByName() throws SQLException, NamingException {
		assertNotNull(dao.getProduct("test1"));
	}
	@Test
	public void testSet() throws SQLException, NamingException {
		dao.setProduct("test", 1, 1);
		assertEquals("test", dao.getProduct("test").getName());
		dao.removeProduct(dao.getProduct("test").getId());

	}

}
