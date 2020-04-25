package saleProcess.model;

public class Payment {
private double amount;
public double change;
private Sale sale;
	public  Payment(double amount, Sale sale) {
		this.amount=amount;
		this.sale=sale;
		calculateTot();
	}

	void calculateTot() {
		//calculate change
		this.change=0;
	}

}
