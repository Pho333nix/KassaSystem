package saleProcess.model;

import org.junit.jupiter.api.Test;
import saleProcess.integration.Discount.DiscountHandler;
import saleProcess.integration.ItemDTO;
import saleProcess.integration.Printer;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    Sale  sale= new Sale(new DiscountHandler(), new Printer());

    @Test
    void calculateTot() {
        ItemDTO apple = new ItemDTO(1.12, "apple", "grown in New Zeeland",
                7, 11111);
        int itemId=11111;
        sale.addItemToSale(apple, 7);
        Payment paymentTest= new Payment(300, sale);
        double retunedChange=paymentTest.getChange();
        double expectedChange=245;
        assertEquals(expectedChange, retunedChange);

    }
}