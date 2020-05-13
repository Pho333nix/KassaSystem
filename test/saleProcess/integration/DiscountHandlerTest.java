package saleProcess.integration;

import org.junit.jupiter.api.Test;
import saleProcess.integration.Discount.DiscountByID;
import saleProcess.integration.Discount.DiscountHandler;
import saleProcess.integration.Discount.DiscountMatcher;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DiscountHandlerTest {

    @Test
    void discountRequest() {
        double expectedRate= 0.25;
        double returnedRate;
        DiscountMatcher discountById = new DiscountByID();
        HashMap<ItemDTO, Integer> dummyItemsInSale=new HashMap<>();
        DiscountHandler discountHandler = new DiscountHandler();
        discountHandler.setDiscountStrategy(discountById);
        returnedRate=discountHandler.discountRequest(1234567891,dummyItemsInSale ).getDiscountRate();
        assertEquals(expectedRate, returnedRate);
    }


}