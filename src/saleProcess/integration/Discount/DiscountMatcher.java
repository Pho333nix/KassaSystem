package saleProcess.integration.Discount;

import saleProcess.model.Sale;

import java.util.HashMap;

/**
 * The interface that all our discount strategies will implement
 */
public interface DiscountMatcher {
    /**
     * A method that given some parameters checks if a customer is eligible for a discount by
     * their customer ID. If not another strategy is called that checks for another type of discount
     * @param CustomerID is the parameter needed here, check for discount rates specific for this customer
     * @param itemsToBeSold is a parameter another strategy will need
     * @param  availableDiscounts all the available discounts of any type.
     * @return a rate that will be applied on the total price.
     */
    DiscountRate checkDiscount(int CustomerID, DiscountRegister availableDiscounts, HashMap itemsToBeSold);
}
