package saleProcess.view;

import saleProcess.controller.Controller;
/**
 * This class will serve as a palceholder for the real User Interface.
 * The purpose of this class is to simulate user-system interaction scenario.
 *
 * */
public class View {

	private Controller contr;
	/**
	 * instantiates a view that will use the given controller to
	 * call methods in various packages below it (mainly the model and integration layers)
	 * @param contr the given controller that serves as facilitator of method calls
	 *
	 * */
	public View(Controller contr) {
		this.contr = contr;
	}
	/**
	 * This will initiate the simulation of a sale process. That is done by
	 * calling the various system operations in the controller.
	 * */
	public void runASaleSimulation(){
		contr.initiateNewSale();
		System.out.println("A sale has now begun");
		contr.scanItem(123, 4);
		System.out.println("item 123 has been scanned, cashier typed 4 " +
				"(for 4 of item 123)");
		contr.discountReq(1234567891);
		System.out.println("customer made a discount request");
		contr.pay(300);
		System.out.println("customer is done and just payed 300:-");
	}

}
