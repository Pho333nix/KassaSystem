package saleProcess.view;

import saleProcess.controller.Controller;
import saleProcess.integration.ConnectionToDBFailedException;
import saleProcess.integration.ItemDTO;
import saleProcess.integration.ItemNotFoundException;
import util.LogHandler;

/**
 * This class will serve as a palceholder for the real User Interface.
 * The purpose of this class is to simulate user-system interaction scenario.
 *
 * */
public class View {
	private String responseMsg;
	private Controller contr;
	private LogHandler logHdlr = new LogHandler().getLogHandler();
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
		double runningTotal;
		contr.initiateNewSale();
		int itemID=11111;
		System.out.println("A sale has now begun");
		try {
			ItemDTO item = contr.scanItem(itemID, 4);

			System.out.println(item.getItemName() + " was added to sale");
			System.out.println(contr.scanItem(itemID, 1).getItemName() + " was added to sale");
		}catch (ItemNotFoundException ne){
			System.out.println(ne);
			logException(ne);
		}catch (ConnectionToDBFailedException dbEx){
			System.out.println("the item was not registered because of network connectivity problems, either on your end or the" +
					"databases. check your network and please try again.");
			logException(dbEx);
		}

		/*responseMsg= contr.scanItem(11111, 1); //done
		System.out.println(responseMsg); //done
		runningTotal= contr.endSaleSession();
		System.out.println("The running total is: " + runningTotal);
		responseMsg= contr.discountRequest(1234567891);
		System.out.println("customer made a discount request " + responseMsg );
		double change=contr.payAndLog(300);
		System.out.println("customer is done and just payed 300:- and your change is: "+change);

		 */
	}
	private void logException(Exception e){
		logHdlr.logException(e);
	}

}
