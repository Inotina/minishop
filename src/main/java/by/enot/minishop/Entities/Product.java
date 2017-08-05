package by.enot.minishop.Entities;

/*
 * Product entity. Products database table.
 * Class field : DataBase column
 * id : Id
 * name : Pname
 * price : Price
 * count : Pcount
 */

public class Product {
	private int id, price, count;
	private String name;
	
	public Product(int id, String name, int price, int count) {
		super();
		this.id = id;
		this.price = price;
		this.count = count;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", price=" + price + ", count=" + count + ", name=" + name + "]";
	}

	
	
}
