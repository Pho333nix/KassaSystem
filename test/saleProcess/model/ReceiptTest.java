package saleProcess.model;

import org.junit.jupiter.api.Test;
import saleProcess.integration.DiscountHandler;
import saleProcess.integration.ItemDTO;
import saleProcess.integration.Printer;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptTest {
Sale sale = new Sale(new DiscountHandler(), new Printer());
    ItemDTO apple = new ItemDTO(1.12, "apple", "grown in New Zeeland",
            7, 11111);
    int itemId=11111;


    @Test
    void testToString() {
        // int expectedQuantity = 10;
        sale.addItemToSale(apple, 7);
        sale.addItemToSale(apple, 3);
        Payment payment = sale.endSale(300);
        sale.endSale(1000);
        Receipt receipt = new Receipt(sale.getItemsInSale(), sale.getSaleTime(), payment, sale.getRunningTotal());
        System.out.println(receipt.toString());
        assertTrue(receipt.toString().contains("address"),
                "toString is not working :/");
    }
}