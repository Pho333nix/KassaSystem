package saleProcess.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saleProcess.controller.Controller;
import saleProcess.model.Sale;
import saleProcess.view.View;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class InventorySTest {
    private InventoryS invS;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalInvS;

    @BeforeEach
    void setUp() {

        invS = new InventoryS();

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalInvS=System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        invS=null;

        printoutBuffer =null;
        System.setOut(originalInvS);

    }
    @Test
    void getExistingItemInfoTest() throws ItemNotFoundException, ConnectionToDBFailedException {
        InventoryS inv= new InventoryS();
        int item1=11111;
        ItemDTO itemDTO = inv.getItemInfo(item1);
        assertTrue(item1==itemDTO.getItemID());
    }

    @Test
    void getNonExistingItemInfoTest(){
        InventoryS inv= new InventoryS();
        int wronngItem1=1;
        ItemDTO itemDTO;
        try {
             itemDTO = inv.getItemInfo(wronngItem1);
            fail("amazing! an item that was not in the database was found, test failed");
        }catch(ItemNotFoundException ne){

            assertTrue(ne.getMessage().contains(wronngItem1+" was not found"));
        }

    }
    @Test
    void getItemDBIsDownTest() throws ItemNotFoundException{
        InventoryS inv= new InventoryS();
        int databaseCrasher=10;
        ItemDTO itemDTO;
        try {
            itemDTO = inv.getItemInfo(databaseCrasher);
            fail("found an item even though we could not reach db to fetch it,  test failed");
        }catch(ConnectionToDBFailedException ne){

            assertTrue(ne.getMessage().contains("reach the inventory"));
        }

    }


    @Test
  void updateInventory() {
        HashMap<ItemDTO, Integer> pretendBuy = new HashMap<>();
      ItemDTO potato = new ItemDTO(1.12, "potato", "from local farms near you",
              9, 44444);
        pretendBuy.put(potato, 3);
        invS.updateInventory(pretendBuy);
        String printout = printoutBuffer.toString();
        String expectedString="stock reduced with: 3";
        assertTrue(printout.contains(expectedString), "the database is acting up...");

    }
}