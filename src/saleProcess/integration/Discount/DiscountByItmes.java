package saleProcess.integration.Discount;

import java.util.HashMap;

/**
 * This should have logic that checks ones items and return a rate depending on that item list
 * It is a dummy class and it returns a rate of one which means there is no discount no mattter the items.
 */
 public class DiscountByItmes implements DiscountMatcher{

    /**
     * This method returns a discount rate. This is a dummy method so it will always return
     * a one.
     * @param CustomerID the customer id of the person doing the discount request
     * @param availableDiscountsByID all the customers that are eligible a discount based on their customerID
     * @param availableDiscountsByItems all the available discounrates based on items
     * @param itemsToBeSold the list of item the customer is buying
     * @return a rate that will be applied on the total price.
     */
     @Override
     public DiscountRate checkDiscount(int CustomerID, HashMap availableDiscountsByID, HashMap availableDiscountsByItems, HashMap itemsToBeSold) {
         return new DiscountRate(1);
     }
 }
