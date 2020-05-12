package saleProcess.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saleProcess.integration.Discount.DiscountHandler;
import saleProcess.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ExternalAccountingSTest {
    private ExternalAccountingS extAcc;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalAcc;

    @BeforeEach
    void setUp() {

        extAcc = new ExternalAccountingS();
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalAcc=System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        extAcc=null;

        printoutBuffer =null;
        System.setOut(originalAcc);

    }
    @Test
    void updateAccount() {
        Sale sale= new Sale(new DiscountHandler(), new Printer());

        ItemDTO potato = new ItemDTO(1.12, "potato", "from local farms near you",
                9, 44444);

        sale.addItemToSale(potato, 2);
        extAcc.updateAccount(sale);
        String printout = printoutBuffer.toString();
        String expectedString=sale.toString();
        assertTrue(printout.contains(expectedString), "the databse is acting up...");
    }
}