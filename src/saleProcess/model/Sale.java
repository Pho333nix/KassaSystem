package saleProcess.model;
import java.time.LocalTime;
import java.util.HashMap;

import saleProcess.integration.Discount.DiscountHandler;
import saleProcess.integration.ItemDTO;
import saleProcess.integration.Printer;
import saleProcess.integration.Discount.DiscountRate;
/**
 * An instance of this class represents a single sale, by a single customer,
 * paid with one payment at a specific moment in time.
 * */
public class Sale {

	private LocalTime saleTime;

	private Payment Payment;

	private Receipt receipt;

	private String storeAdress;

	private HashMap<ItemDTO, Integer> itemsInSale=new HashMap<>();

	private Discount discount;
	private DiscountRate discRate;

	private Payment payment;

	private Printer printer;
	private DiscountHandler discountHandler;
	private double runningTotal=0.0;

	private int nrOfTotalItems=0;
	/**
	 * instantiates a new sale and saves the time of the sale.
	 * A new instance of receipt will also be created for the purpose
	 * of printing proof of sale to provide to the customer
	 * */
	public Sale(DiscountHandler discHdlr, Printer printer) {
		//write test of sale later on when saleTime will be used again
		//in a meaningful way (e.g: when it will be added to receipt)
		saleTime=LocalTime.now();
		discountHandler=discHdlr;
		this.printer=printer;
	}
	/**
	 * This method will add a DTO of the scanned Item
	 * along with its quantity into a Hashmap. It will
	 * check wether the DTO already exists and update
	 * the quantity or add the item accordingly.
	 * @param item is the DTO containing information about the item.
	 * @param nrOfItem is the quantity of the item the cashier specifies while
	 *        in progress of adding it to the sale.
	 * */
	public void addItemToSale(ItemDTO item, int nrOfItem) {
		if(itemsInSale.isEmpty()) {
			System.out.println("add item to sale, empty: "+item.getItemName());
			itemsInSale.put(item, nrOfItem);
			updateRunningTotal(item, nrOfItem);

		}else if(itemIsAlreadyInSale(item))
		{
			updateQuantityOfAnItemInSale(item, nrOfItem);
			updateRunningTotal(item, nrOfItem);
		}else{

			itemsInSale.put(item, nrOfItem);
			updateRunningTotal(item, nrOfItem);
		}
		// nrOfTotalItems +=1;
		//come back and take care of "updateReceipt"
		//System.out.println("add item: " + item.getItemName());
	}

	/**
	 * This method updates the totalPrice (runningTotal)
	 * it is executed everytime an item is added
	 * @param item the itemDTO in question
	 * @param nrOfItem The quantity of that specific item
	 */
	private void updateRunningTotal(ItemDTO item, int nrOfItem){

		runningTotal += item.getPrice() * (nrOfItem * item.getVAT());

	}
	/**
	 * This method checks if item is already in sale (it was scanned
	 * earlier at some point during that specific sale).
	 * @param item is The itemDTO, we will check if is already in the
	 * 			hashmap. The itemDTO contains informaton about the item in question.
	 * @return true or false. depending on wether the ItemDTO exists in
	 * 			hashmap or not.
	 * */
	private boolean itemIsAlreadyInSale(ItemDTO item){
		return itemsInSale.containsKey(item);
	}


	/* This method updates the quantity of a certain item (or itemDTO to be mor
	 * specific) in the sale. This in only called if the item was previously added
	 * to the hashmap of items to be bought.
	 *
	 * */
	private void updateQuantityOfAnItemInSale(ItemDTO item, int nrOfItem){
		itemsInSale.put(item, itemsInSale.get(item) + nrOfItem);
	}

	/**
	 * This method is called when a discount request is made, for now it only
	 * works for one type of discount, the one thats based on customer id only.
	 * The rest will be added later on.
	 * @param customerID
	 * @return a message showing rate and a confirmation of discount
	 */
	public String discountRequest (int customerID) {
		initiateDiscount(customerID);
		return  messageAfterDiscount(discount.theDiscountRateAsAPrimitiveValue);
	}

	private void initiateDiscount(int customerID){
		discount = new Discount(this, customerID,discountHandler);
	}

	private String messageAfterDiscount(double rate){
		if(rate == 1.0){
			return "Discount eligibility: False";
		}else {
			runningTotal =  getRunningTotal()-(getRunningTotal()* rate);
			return  rate + " your new total is now: " + runningTotal;
		}

	}

	public double getRunningTotal(){
		return Math.round(runningTotal);
	}

	/**
	 * This method instantiates a new payment object and retu
	 * @param amount
	 * @return
	 */
	public Payment endSale(double amount) {

		payment = new Payment(amount, this);
		receipt=new Receipt(itemsInSale, saleTime, payment, runningTotal);
		printer.printReceipt(receipt);
		return payment;
	}

	public LocalTime getSaleTime() {
		return saleTime;
	}

	public HashMap<ItemDTO, Integer> getItemsInSale() {

		return itemsInSale;
	}
}
