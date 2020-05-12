package saleProcess.view;

import saleProcess.model.PaymentObserver;

/**
 * This is a class in the view, it will add up all the payments made and
 * display them in the view. This is an implementation of the observer pattern.
 */
public class TotalRevenueView implements PaymentObserver {

    private double totalIncome;

    /**
     * This method increments the totalIncome variable of this class by the payment amount
     *
     * @param payment the amount paid ending a purchase
     */
    @Override
    public void addPayment(double payment) {
        totalIncome += payment;
        System.out.println("Today's Revenue so far: " +totalIncome);
    }
}
