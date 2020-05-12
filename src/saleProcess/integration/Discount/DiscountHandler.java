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
       return checkEligibilityAndReturnRate(customerID, itemsInSale, discountsByID);
    }

    private DiscountRate checkEligibilityAndReturnRate(int customerID, HashMap discountsByID, HashMap itemsInSale){
       //return  (discountsByID.containsKey(customerID) )? (discountsByID.get(customerID)):  normalRate;
        return new DiscountByID().checkDiscount(customerID, discountsByID, itemsInSale);
    }
    private void availableDiscountsPerCustomer(){
        discountsByID.put(1234567891, new DiscountRate(0.25));
    }

    private void availableDiscountBasedOnItem(){
        discountByItem.put(44444, new DiscountRate(0.15));

    }
   /* private void availableDiscountsBasedOnItemQuantity(){
        discountByItemQuantity.put()
    } */

}
