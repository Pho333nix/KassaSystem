package saleProcess.integration.Discount;

import java.util.HashMap;
/**
 * This class creates a collection of different types of 
 * discounts. when a discount is requested, this is where we come
 * to verify if customer is eligible for the discount
 * */
public class DiscountHandler {


    private DiscountRegister availableDiscounts;
    private DiscountMatcher discountStrategy;
    public DiscountHandler(){
       availableDiscounts = new DiscountRegister();
    }

    /**
     * This method checks if there is a discount available
     * for a ceratin customer at the time of the request/sale.
     * 
     * @param customerID uniquely identifies customer
     * @return the rate after the request. if there is no discount, the rate returned is
     * 1.0
     */
    public DiscountRate discountRequest(int customerID, HashMap itemsInSale){

       return checkEligibilityAndReturnRate(customerID,  itemsInSale);
    }

    private DiscountRate checkEligibilityAndReturnRate(int customerID, HashMap itemsInSale){
       //return  (discountsByID.containsKey(customerID) )? (discountsByID.get(customerID)):  normalRate;
        return discountStrategy.checkDiscount(customerID, availableDiscounts, itemsInSale);

    }


    /**
     * This method gives us the ability to set different types of discount strategies
     * so that we can check for different kinds of dicounts for a given customer and
     * their items and the quantity of those items.
     * @param aDiscountStaregy any given discountStrategy that implements the discountMatcher interface
     */
    public void setDiscountStrategy(DiscountMatcher aDiscountStaregy){

        this.discountStrategy=aDiscountStaregy;
    }


}
