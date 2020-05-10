package saleProcess.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saleProcess.integration.ConnectionToDBFailedException;
import saleProcess.integration.ItemDTO;
import saleProcess.integration.ItemNotFoundException;
import saleProcess.integration.SystemCreator;
import saleProcess.model.Payment;

import javax.naming.ContextNotEmptyException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    private Controller contr;

    @BeforeEach
    void setUp() {
        contr = new Controller(new SystemCreator());
    }

    @AfterEach
    void tearDown() {
        contr= null;
    }

    @Test
    void initiateNewSale() {
        ByteArrayOutputStream printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        PrintStream originalPS=System.out;
        System.setOut(inMemSysOut);
        contr.initiateNewSale();

        String printout = printoutBuffer.toString();
        String expectedString="init sale";
        assertTrue(printout.contains(expectedString), "no new sale? :/");

        printoutBuffer =null;
        System.setOut(originalPS);


    }



    @Test
    void scanItem() throws ConnectionToDBFailedException, ItemNotFoundException{
        contr.initiateNewSale();
        int itemID = 22222;
        int nrOfitem = 3;
        ItemDTO item= contr.scanItem(itemID, nrOfitem);
        int retItemID=item.getItemID();
        String expectedMsg ="item salmon 3 x  price excluding VAT is: 125.0 is addedrunningtotal including VAT is now: 469.0";
        assertEquals(itemID,retItemID);
    }

    @Test
    void scanNonExistingItem(){
        ItemDTO item;
        contr.initiateNewSale();
        int wrongItemID = 2;
        int nrOfitem = 3;
        try{
            item= contr.scanItem(wrongItemID, nrOfitem);

            fail("amazing! an item that was not in the database was found, test failed");
        }catch(ItemNotFoundException ne){

            assertTrue(ne.getMessage().contains(wrongItemID+" was not found"));
        }

    }
    @Test
    void scanItemDBDown() throws ItemNotFoundException{
        ItemDTO item;
        contr.initiateNewSale();
        int itemID = 10;
        int nrOfitem = 3;
        try{
            item= contr.scanItem(itemID, nrOfitem);

            fail("amazing! an item was found, even though we could not reach database to check test failed");
        }catch (ConnectionToDBFailedException e) {
            assertTrue(e.getMessage().contains("reach the inventory database"));
        }

    }


    @Test
    void endSaleSession() throws ItemNotFoundException{
        contr.initiateNewSale();
        int itemID = 22222;
        int nrOfitem = 3;
        double runningTotal=Math.round(125*3*1.25);
        contr.scanItem(itemID, nrOfitem);
        assertEquals(runningTotal, contr.endSaleSession());
    }

    @Test
    void discountRequest() throws ItemNotFoundException{
        contr.initiateNewSale();
        int itemID = 22222;
        int nrOfitem = 3;
        double rate = 0.25;
        double runningTotal=Math.round(375*1.25);
        double newPrice = runningTotal-(runningTotal*rate);

        String expectedMsg= "You were eligable for a discount rate of: " + rate
                + " your new total is now: " + newPrice;
        contr.scanItem(itemID, nrOfitem);
        String returnedMSG = contr.discountRequest(1234567891);
        assertEquals(expectedMsg,returnedMSG);
    }

    @Test
    void pay()throws ItemNotFoundException {
        contr.initiateNewSale();
        int itemID = 22222;
        int nrOfitem = 3;
        double expectedChange=Math.round(500-(375 +(375*0.25)));
        contr.scanItem(itemID, nrOfitem);
        double returnedChange= contr.payAndLog(500);
        assertEquals(expectedChange, returnedChange);
    }


/*
*Item salmon = new Item(VAT=25, "salmon", "Norwegian wild salmon",
				price= 125, id=22222, in stock=200);
*/

}