package saleProcess.integration.Discount;

import saleProcess.model.Sale;

import java.util.HashMap;

public interface DiscountMatcher {

    DiscountRate checkDiscount(int CustomerID, HashMap availableDiscountsByID, HashMap availableDiscountsByItems,HashMap itemsToBeSold);
}
