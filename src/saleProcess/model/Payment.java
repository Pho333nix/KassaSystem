package saleProcess.model;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represents a payment and all the relevant data associated with it.
 */
public class Payment {
private double payedAmount;
private double change;
private Sale sale;
private double totalPrice;
private List<PaymentObserver> paymentPaymentObservers;

	public Payment(double amount, Sale sale) {
		this.payedAmount=amount;
		this.sale=sale;
		this.totalPrice=sale.getRunningTotal();
		calculateChange();
		paymentPaymentObservers = new ArrayList<>();
	}

	private void calculateChange(){
		 if(payedAmount > totalPrice){

			this.change= payedAmount - totalPrice;
		}else{
			this.change=0;
		}

	}

	/**
	 * this method adds observers from controller to this class.
	 * @param paymentObs list of observers that will observe this class.
	 */
	public void addPaymentObservers(List<PaymentObserver> paymentObs){
		paymentPaymentObservers.addAll(paymentObs);
		notifyObservers();
	}
	private void notifyObservers(){
		paymentPaymentObservers.forEach((obs) ->{
			obs.addPayment(payedAmount - change);
		});
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
