package saleProcess.integration.Discount;

import java.util.HashMap;
/**
 * This class creates a collection of different types of 
 * discounts. when a discount is requested, this is where we come
 * to verify if customer is eligable for the discount
 * */
public class DiscountHandler {
    private HashMap<Integer, DiscountRate> discountsByID = new HashMap<>();
    private HashMap<Integer, DiscountRate> discountByItem = new HashMap<>();
    private HashMap<Integer, DiscountRate> discountByItemQuantity;
    public DiscountRate normalRate= new DiscountRate(1.0);
    private DiscountMatcher discountStrategy;
    public DiscountHandler(){
        availableDiscountsPerCustomer();
        availableDiscountBasedOnItem();
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
      /*  if(discountsByID.containsKey(customerID)){
            return discountsByID.get(customerID);
        }
        return normalRate;
    */
       return checkEligibilityAndReturnRate(customerID,  itemsInSale);
    }

    private DiscountRate checkEligibilityAndReturnRate(int customerID, HashMap itemsInSale){
       //return  (discountsByID.containsKey(customerID) )? (discountsByID.get(customerID)):  normalRate;
        return discountStrategy.checkDiscount(customerID, discountsByID, discountByItem, itemsInSale);

    }
    private void availableDiscountsPerCustomer(){
        discountsByID.put(1234567891, new DiscountRate(0.25));
    }

    private void availableDiscountBasedOnItem(){
        discountByItem.put(44444, new DiscountRate(0.15));

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
   /* private void availableDiscountsBasedOnItemQuantity(){
        discountByItemQuantity.put()
    } */

}
