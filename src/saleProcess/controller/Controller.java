package saleProcess.controller;

import saleProcess.integration.*;
import saleProcess.model.Sale;
import saleProcess.model.Register;

/**
 * This is our only controlloer. The purpose of this controller is to
 * control and mitigate calls to model and integreation objects
 *
 */
public class Controller {


	private SystemCreator sysC;

	private  InventoryS  inv;

	private Register reg;

	private SaleLog log;

	private Printer pr;
	private ExternalAccountingS extAcc;
	private Sale sale;


	/**
	 * The controller constructor method below takes a
	 * SystemCreator object in the integration package
	 * @param sysC: an instance of Systemcreator. The controller
	 *        needs it to be able to reach objects in the integration layer
	 *        (salelog, external accounting and external inventory System)
*/
	public  Controller(SystemCreator sysC) {
		this.sysC=sysC;
		this.inv= sysC.getInventory();
		this.extAcc=sysC.getAccounting();
		pr = new Printer();
		reg = new Register();
	}
	/**
	 * This is what initiates a new sale. whenever a new customer
	 * wants to buy something, this is instantiated and the sale can start.
	 * by definition it proceeds any action that can be taken in the sale process
	 * */
	public void initiateNewSale(){
		sale= new Sale();
	}

	public String scanItem(int itemID, int nrOfItem) {
		return null;
	}

	public void discountReq(int CustomerID) {
	}

	public double pay(double amount) {
		return 0;
	}

}
