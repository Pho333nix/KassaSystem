package saleProcess.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saleProcess.integration.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {
    Sale sale;
    ItemDTO apple = new ItemDTO(1.12, "apple", "grown in New Zeeland",
            7, 11111);
    int itemId=11111;

    @BeforeEach
    void setUp() {
         sale= new Sale(new DiscountHandler(), new Printer());
    }

    @AfterEach
    void tearDown() { sale=null;}


        @Test
    void addItemToSale() {
      //  InventoryS inv;
            int expectedQuantity = 10;
        sale.addItemToSale(apple, 7);
        sale.addItemToSale(apple, 3);
        HashMap<ItemDTO, Integer> cart= sale.getItemsInSale();
        int actualQuant = cart.get(apple);
        assertEquals(expectedQuantity, actualQuant);

    }

 // @Test
 //   void discountRequest() {
 //       int customerID = 1234567891;
 //     sale.addItemToSale(apple, 7);
 //     sale.addItemToSale(apple, 3);
 //     String discountMsg = sale.discountRequest(customerID);
 //    assertEquals("You were eligable for a discount rate of: 0.25 your new total is now: 59.0", discountMsg);

 //   }

    @Test
    void getRunningTotal() {
        sale.addItemToSale(apple, 7);
        sale.addItemToSale(apple, 3);
        double expectedTotal=Math.round(7*10*1.12);
        double actualTotal=sale.getRunningTotal();
        System.out.println("total: " + actualTotal );
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    void endSale() {
        sale.addItemToSale(apple, 7);
        sale.addItemToSale(apple, 3);
        Payment payment= sale.endSale(300);
        double expectedChange=Math.round(300 - (70*1.12));
        double actualChange=payment.getChange();
        assertEquals(expectedChange, actualChange);


    }

   @Test
    void getSaleTime() {
       LocalTime expectedTime = LocalTime.now();
       LocalTime actualTime=sale.getSaleTime();
       int isZero=LocalTime.of(expectedTime.getHour(), expectedTime.getMinute()).
               compareTo(LocalTime.of(actualTime.getHour(), actualTime.getMinute()));
       assertEquals(0, isZero);
    }

   @Test
    void getItemsInSale() {
       sale.addItemToSale(apple, 7);
       sale.addItemToSale(apple, 3);
       HashMap<ItemDTO, Integer> cart= sale.getItemsInSale();

       cart.forEach((k,v)->{
           assertEquals(k.getItemName(), "apple");
      });

    }
}