package saleProcess.integration.Discount;

import java.util.HashMap;

/**
 * This should have logic that checks ones items and return a rate depending on that item list
 * It is a dummy class and it returns a rate of one which means there is no discount no matter the items.
 */
 public class DiscountByItmes implements DiscountMatcher{

    /**
     * A method that given some parameters checks if a customer is eligible for a discount by
     * their customer ID. If not another strategy is called that checks for another type of discount
     * @param CustomerID is the parameter needed here, check for discount rates specific for this customer
     * @param itemsToBeSold is a parameter another strategy will need
     * @param  availableDiscounts all the available discounts of any type.
     * @return a rate that will be applied on the total price.
     */
     @Override
     public DiscountRate checkDiscount(int CustomerID, DiscountRegister availableDiscounts, HashMap itemsToBeSold) {
         return new DiscountRate(1);
     }
 }
