package saleProcess.model;

public class Payment {
private double payedAmount;
private double change;
private Sale sale;
private double totalPrice;

	public Payment(double amount, Sale sale) {
		this.payedAmount=amount;
		this.sale=sale;
		this.totalPrice=sale.getRunningTotal();
		calculateChange();
	}

	private void calculateChange(){
		 if(payedAmount > totalPrice){

			this.change= payedAmount - totalPrice;
		}else{
			this.change=0;
		}

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
