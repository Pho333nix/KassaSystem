package saleProcess.integration;

import saleProcess.integration.Discount.DiscountHandler;

public class SystemCreator {

	private  InventoryS  inv;

	private ExternalAccountingS extAcc;

	private SaleLog log;
	private DiscountHandler discountHdlr;

	public  SystemCreator() {
		inv = InventoryS.getInventorySingleton();
		extAcc = new ExternalAccountingS();
		log = new SaleLog(extAcc, inv);
		discountHdlr = new DiscountHandler();
	}

	public InventoryS getInventory() {
		return inv;
	}
	public DiscountHandler getDiscountHdlr(){
		return discountHdlr;
	}

	public ExternalAccountingS getAccounting() {
		return extAcc;
	}

	public SaleLog getSaleLog() {
		return log;
	}

}
