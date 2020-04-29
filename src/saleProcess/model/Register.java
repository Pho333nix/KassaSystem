package saleProcess.model;

public class Register {

	private double balance=2000;
	private Payment payment;
	public void addPayment(Payment payment) {
		this.payment=payment;
		addToRegister();
		removeFromRegister();
	}
	private void addToRegister(){
		balance += payment.getPayedAmount();
	}
	private void removeFromRegister(){
		balance-=payment.getChange();
	}

	public double getBalance() {
		return balance;
	}
}
