package saleProcess.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saleProcess.integration.Discount.DiscountHandler;
import saleProcess.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SaleLogTest {
    private SaleLog log;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalLog;

    @BeforeEach
    void setUp() {


    }


    @Test
    void logSale() {
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalLog=System.out;
        System.setOut(inMemSysOut);

        Sale sale= new Sale(new DiscountHandler(), new Printer());
        HashMap<ItemDTO, Integer> pretendBuy = new HashMap<>();
        ItemDTO potato = new ItemDTO(1.12, "potato", "from local farms near you",
                9, 44444);
        pretendBuy.put(potato, 3);

        log = new SaleLog( new ExternalAccountingS(), new InventoryS());
        log.logSale(sale, pretendBuy);
        String printout = printoutBuffer.toString();
        String expectedString=sale.toString();
        assertTrue(printout.contains(expectedString), "sale-Log  is acting up...");



        printoutBuffer =null;
        System.setOut(originalLog);
    }
}