package saleProcess.model;

import saleProcess.integration.DiscountHandler;
import saleProcess.integration.DiscountRate;

/**
 * This class is instantiated when a new discount is made.
 *The call to the discounthandler in integration is made here.
 */
public class Discount {
	private Sale sale;
	private int customerID;
	private DiscountHandler discountHandler;
	private DiscountRate discountRate;
	double theDiscountRateAsAPrimitiveValue;

	public Discount(Sale sale, int customerID, DiscountHandler dischdlr) {
		this.sale=sale;
		this.customerID=customerID;
		this.discountHandler=dischdlr;
		discountRequest();
	}

	private void discountRequest(){

		this.discountRate=discountHandler.discountRequest(customerID);
		this.theDiscountRateAsAPrimitiveValue=discountRate.getDiscountRate();
	}



}
