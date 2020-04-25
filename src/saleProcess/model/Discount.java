package saleProcess.model;

public class Discount {
	private Sale sale;
	private int customerID;
	public Discount(Sale sale, int customerID) {
		this.sale=sale;
		this.customerID=customerID;
	}

}
