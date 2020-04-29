package saleProcess.controller;

import saleProcess.integration.*;
import saleProcess.model.Payment;
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
	private DiscountHandler discountHandler;
	private Sale sale;
	private ItemDTO itemDTO;
	private Payment payment;


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
		this.log=sysC.getSaleLog();
		this.discountHandler=sysC.getDiscountHdlr();
		pr = new Printer();
		reg = new Register();
	}
	/**
	 * This is what initiates a new sale. whenever a new customer
	 * wants to buy something, this is instantiated and the sale can start.
	 * by definition it proceeds any action that can be taken in the sale process
	 *
	 *
	 */
	public void initiateNewSale(){
		sale= new Sale(discountHandler, pr);
		System.out.println("init sale" + sale.toString());
	}

	/**
	 *This method is called when the cashier scans an item. This method
	 * will first check the inventory to see if such an item even exists
	 * in the database. If that is the case then an ItemDTO is returned
	 * containing  information about that item. That DTO is what we
	 * will add to the sale.
	 * @param itemID is what uniquely identifies an item, that will be used to get
	 * the information needed to create the DTO
	 * @param nrOfItem is the specified quantity of that specific item. it is set
	 * to one by default but the cashier could enter another value before scanning the next item
	 *
	 * @return returnMsg is the message returned to the view, what the cashier sees on
	 * the monitor. it will confirm wether the item was added or give an error message
	 */
	public String scanItem(int itemID, int nrOfItem) {
		String returnMsg;
			itemDTO = inv.getItemInfo(itemID);
			if(itemDTO != null) {
				sale.addItemToSale(itemDTO, nrOfItem);
				 returnMsg ="item "  + itemDTO.getItemName() + " " + nrOfItem + " x " +
						    " price excluding VAT is: " + itemDTO.getPrice()+ " is added"
				 			+ "runningtotal including VAT is now: " + sale.getRunningTotal();
			}else{
				returnMsg="something went wrong, there is no such item in our database.";
		}
			return returnMsg;
	}

	/**
	 * This method marks the end of the "sale session".
	 * No more items will be scanned (although they can if
	 * cusotmer changes their mind). This is done right before
	 * the discount request or the payment itself (which will end the sale)
	 * @return the runningtotal, the totalprice so far. without the discount
	 */
	public double endSaleSession(){
		return sale.getRunningTotal();
	}

	/**
	 * This method is called when a discount request is made, it will either
	 * update
	 * @param customerID
	 * @return
	 */
	public String discountRequest(int customerID) {
		return sale.discountRequest(customerID);
	}

	/**
	 * This is the last system operation.
	 * when a payment is made the payed amount
	 * is sent to sale for the proper calculations
	 * and changes
	 * @param amount
	 * @return change is returned to the view
	 */
	public double payAndLog(double amount) {
		payment= sale.endSale(amount);

		reg.addPayment(payment);
		logTheSale();
		return payment.getChange();
	}

	/**
	 * This method intitiates the update of the register,
	 * salelog, external accounitng, and inventory systems.
	 */
	private void logTheSale(){
		reg.addPayment(payment);
		log.logSale(sale, sale.getItemsInSale());
	}

}
