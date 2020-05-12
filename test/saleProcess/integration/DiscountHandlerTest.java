package saleProcess.integration;

import org.junit.jupiter.api.Test;
import saleProcess.integration.Discount.DiscountHandler;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DiscountHandlerTest {

    @Test
    void discountRequest() {
        double expectedRate= 0.25;
        double returnedRate;
        HashMap<ItemDTO, Integer> dummyItemsInSale=new HashMap<>();
        DiscountHandler discountHandler = new DiscountHandler();
        returnedRate=discountHandler.discountRequest(1234567891,dummyItemsInSale ).getDiscountRate();
        assertEquals(expectedRate, returnedRate);
    }


}