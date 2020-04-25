package saleProcess.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import saleProcess.controller.Controller;
import saleProcess.integration.SystemCreator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {
    private View viewInstanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        SystemCreator sysC= new SystemCreator();
        Controller contr= new Controller(sysC);
        viewInstanceToTest = new View(contr);

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut=System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        viewInstanceToTest=null;

        printoutBuffer =null;
        System.setOut(originalSysOut);

    }

    @Test
    void testRunASaleSimulation() {
         viewInstanceToTest.runASaleSimulation();
         String printout = printoutBuffer.toString();
         String expectedOutput = "begun";
         assertTrue(printout.contains(expectedOutput),
                 "UI did not initiate correctly");

    }
}