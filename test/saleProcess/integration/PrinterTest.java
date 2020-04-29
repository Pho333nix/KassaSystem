package saleProcess.integration;

import org.junit.jupiter.api.Test;
import saleProcess.model.Payment;
import saleProcess.model.Receipt;
import saleProcess.model.Sale;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    Sale sale = new Sale(new DiscountHandler(), new Printer());
    ItemDTO apple = new ItemDTO(1.12, "apple", "grown in New Zeeland",
            7, 11111);
    ItemDTO salmon = new ItemDTO(1.25, "salmon", "Norwegian wild salmon",
            125, 22222);
    ItemDTO  stapler= new ItemDTO(1.15, "stapler", "The best stapler there is",
            56, 33333);
    ItemDTO potato = new ItemDTO(1.12, "potato", "from local farms near you",
            9, 44444);
    int itemId=11111;
    Receipt receipt;
    @Test
    void printReceipt() {

        sale.addItemToSale(apple, 7);
         sale.addItemToSale(apple, 3);
         sale.addItemToSale(potato, 1);
        sale.addItemToSale(salmon, 1);
        sale.addItemToSale(stapler, 3);
         Payment payment= sale.endSale(500);
       // sale.endSale(100);


        receipt = new Receipt(sale.getItemsInSale(), sale.getSaleTime(), payment, sale.getRunningTotal());
        Printer pr = new Printer();
        pr.printReceipt(receipt);
        System.out.println(receipt.toString());
    }
}