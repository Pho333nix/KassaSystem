package saleProcess.integration.Discount;

import java.util.HashMap;

/**
 * This will contain all the different types of discounts available. This is a fictional database
 */
 class DiscountRegister {
    private HashMap<Integer, DiscountRate> discountByItemQuantity;
    HashMap<Integer, DiscountRate> discountsByID = new HashMap<>();
    HashMap<Integer, DiscountRate> discountByItem = new HashMap<>();

    DiscountRegister(){
        availableDiscountBasedOnItem();
        availableDiscountsPerCustomer();
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

    public HashMap<Integer, DiscountRate>getDiscountById(){
        return this.discountsByID;
    }

    public HashMap<Integer, DiscountRate> getDiscountByItem() {
        return discountByItem;
    }

}
