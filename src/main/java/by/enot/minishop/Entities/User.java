package by.enot.minishop.Entities;

/*
 * User entity. Users database table.
 * Class field : DataBase column
 * id : UserId
 * name : Username
 * password : UserPassword
 * email : UserEmail
 * status : IsAdmin
 */

public class User {
	private int id;
	private String name, email, password, status;
	
	public User(int id, String name, String email, String password, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
