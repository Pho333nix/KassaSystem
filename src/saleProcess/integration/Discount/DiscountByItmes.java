package saleProcess.integration.Discount;

import java.util.HashMap;

public class DiscountByItmes implements DiscountMatcher{

    @Override
    public DiscountRate checkDiscount(int CustomerID, HashMap availableDiscounts, HashMap itemsToBeSold) {
        return new DiscountRate(1);
    }
}
