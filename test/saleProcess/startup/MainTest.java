package saleProcess.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saleProcess.controller.Controller;
import saleProcess.integration.SystemCreator;
import saleProcess.view.View;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    private Main mainInstanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
       mainInstanceToTest = new Main();

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut=System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        mainInstanceToTest =null;

        printoutBuffer =null;
        System.setOut(originalSysOut);

    }

    @Test
    public void testUIHasStarted(){
        String[] args=null;
        mainInstanceToTest.main(args);
        String printout = printoutBuffer.toString();
        String expectedOutput = "begun";
        assertTrue(printout.contains(expectedOutput),
                "UI did not initiate correctly");
    }
}