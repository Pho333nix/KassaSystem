package saleProcess.startup;

import saleProcess.view.View;
import saleProcess.controller.Controller;
import saleProcess.integration.SystemCreator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Main {

/**
 * This class contains the main method which is used to start the entire application
 *@param args : The application does not take any command line parameters
 */
	public static void main(String[] args) {

	SystemCreator sysC = new SystemCreator();
	Controller contr = new Controller(sysC);
		System.out.println(contr.toString());
	View view = new View(contr);
	view.runASaleSimulation();

	 
	}

}
