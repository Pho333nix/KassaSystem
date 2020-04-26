package saleProcess.model;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import saleProcess.integration.ItemDTO;
import saleProcess.integration.Printer;
/**
 * An instance of this class represents a single sale, by a single customer,
 * paid with one payment at a specific moment in time.
 * */
public class Sale {

	private LocalTime saleTime;

	private Payment Payment;

	private Receipt receipt;

	private String storeAdress;

	private List<ItemAndQuantity> items=new ArrayList<>();

	private Discount discountPr;

	private Payment payment;

	private Printer printer;

	private int nrOfTotalItems=0;
	/**
	 * instantiates a new sale and saves the time of the sale.
	 * A new instance of receipt will also be created for the purpose
	 * of printing proof of sale to provide to the customer
	 * */
	public Sale() {
		//write test of sale later on when saleTime will be used again
		//in a meaningful way (e.g: when it will be added to receipt)
		saleTime=LocalTime.now();
		receipt=new Receipt(items);
	}

	public void addItem(ItemDTO item, int nrOfItem) {
		nrOfTotalItems +=1;
		items.add(new ItemAndQuantity(item, nrOfItem));
	}

	public void discountReq(int customerID) {
		discountPr= new Discount(this, customerID);

	}

	public Payment endSale(double amount) {
		return payment = new Payment(amount, this);
	}

}
