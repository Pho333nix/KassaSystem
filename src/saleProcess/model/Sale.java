package saleProcess.model;
import java.time.LocalTime;
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

	private ItemDTO items[];

	private Discount discount;

	private Payment payment;

	private Printer printer;
	/**
	 * instantiates a new sale and saves the time of the sale.
	 * A new instance of receipt will also be created for the purpose
	 * of printing proof of sale to provide to the customer
	 * */
	public Sale() {
		//write test of sale later on when saleTime will be used again
		//in a meaningful way (e.g: when it will be added to receipt)
		saleTime=LocalTime.now();
		receipt=new Receipt();
	}

	public void addItem(ItemDTO item) {

	}

	public void DiscountReq(int customerID) {

	}

	public double endSale(double amount) {
		return 0;
	}

}
