
import java.io.Serializable;

public class DailySales implements Serializable {
	private static final long serialVersionUID = 1L;
	private String productNo;
	private String productName;
	private int number;
	private int money;
	private int id;

	public DailySales(String productNo, String productName, int number, int money) {
		this.productNo = productNo;
		this.productName = productName;
		this.number = number;
		this.money = money;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
