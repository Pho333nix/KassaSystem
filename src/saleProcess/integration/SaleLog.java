package saleProcess.integration;

import saleProcess.model.Sale;

public class SaleLog {

	private  InventoryS  inv;

	private ExternalAccountingS extAcc;

	public void logSale(Sale sale) {
		inv.updateInventory(sale);
		extAcc.updateAccount(sale);
	}

	public  SaleLog() {

	}

}
