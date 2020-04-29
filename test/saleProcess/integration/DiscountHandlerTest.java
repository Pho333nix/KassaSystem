package saleProcess.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountHandlerTest {

    @Test
    void discountRequest() {
        double expectedRate= 0.25;
        double returnedRate;
        DiscountHandler discountHandler = new DiscountHandler();
        returnedRate=discountHandler.discountRequest(1234567891).getDiscountRate();
        assertEquals(expectedRate, returnedRate);
    }
}