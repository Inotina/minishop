package by.enot.minishop.Entities;

/*
 * Purchase entity. Purchase database table.
 * Class field : DataBase column
 * purchaseId : PurchaseId
 * phone : Phone
 * adress : Adress
 * date : Date
 * userId : ClientId
 * products : Products
 */

public class Purchase {
	private int purchaseId, userId;
	private String phone, adress, date, products;
	
	public Purchase(int purchaseId, int userId, String phone, String adress, String date, String products) {
		super();
		this.purchaseId = purchaseId;
		this.userId = userId;
		this.phone = phone;
		this.adress = adress;
		this.date = date;
		this.products = products;
	}



	public int getPurchaseId() {
		return purchaseId;
	}



	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getAdress() {
		return adress;
	}



	public void setAdress(String adress) {
		this.adress = adress;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getProducts() {
		return products;
	}



	public void setProducts(String products) {
		this.products = products;
	}



	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", userId=" + userId + ", phone=" + phone + ", adress=" + adress
				+ ", date=" + date + "]";
	}
	
	
}
