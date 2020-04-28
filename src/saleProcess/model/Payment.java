package saleProcess.model;

public class Payment {
private double payedAmount;
private double change;
private Sale sale;
private double totalPrice;

	public  Payment(double amount, Sale sale) {
		this.payedAmount=amount;
		this.sale=sale;
		calculateTot();
	}

	void calculateTot() {
		this.totalPrice=sale.getRunningTotal();
		this.change= payedAmount - totalPrice;

	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public double getChange() {
		return change;
	}

	public double getPayedAmount() {
		return payedAmount;
	}
}
