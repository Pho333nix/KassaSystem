package saleProcess.integration;

import saleProcess.model.Sale;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * this is the saleLog, it is supposed to keep track of all our sales.
 * I do not know exactly what we are supposed to do here so i will just
 * keep an arrayList of all our sales here.
 */
public class SaleLog {

	private  InventoryS  inv;

	private ExternalAccountingS extAcc;
	private ArrayList<Sale> saleArrayList = new ArrayList<>();

	public SaleLog(ExternalAccountingS extAcc, InventoryS inv){
		this.extAcc=extAcc;
		this.inv=inv;
	}
	/**
	 * This method is called by the controller at the end of a sale.
	 * logSale will also send the information to the inventory and accounting systems.
	 * I use an arraylist here because each sale is uniqely identified by it's
	 * saleTime
	 * @param sale the specific sale instance in question.
	 */
	public void logSale(Sale sale, HashMap itemsInSaleCart) {
		saleArrayList.add(sale);
		inv.updateInventory(itemsInSaleCart);
		extAcc.updateAccount(sale);
		System.out.println(sale.toString());
	}

}
