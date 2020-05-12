package saleProcess.model;

import org.junit.jupiter.api.Test;
import saleProcess.integration.Discount.DiscountHandler;
import saleProcess.integration.ItemDTO;
import saleProcess.integration.Printer;

import static org.junit.jupiter.api.Assertions.*;

class RegisterTest {

    @Test
    void addPayment() {
        Sale sale= new Sale(new DiscountHandler(), new Printer());
        ItemDTO apple = new ItemDTO(1.12, "apple", "grown in New Zeeland",
                7, 11111);
        int itemId=11111;
        sale.addItemToSale(apple, 1);
        Register reg = new Register();
        double originalBalance=2000;
        Payment payment=new Payment(100,sale);
        reg.addPayment(payment);
        double expectedBalance = Math.round(2000 + (7*1.12));
        double returnedBalance= reg.getBalance();
        assertEquals(expectedBalance, returnedBalance);
        //System.out.println("reg: "+ reg.toString());

    }
}