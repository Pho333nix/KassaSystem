package saleProcess.integration;

public class SystemCreator {

	private  InventoryS  inv;

	private ExternalAccountingS extAcc;

	private SaleLog log;

	public  SystemCreator() {
		inv = new InventoryS();
		extAcc = new ExternalAccountingS();
		log = new SaleLog();
	}

	public InventoryS getInventory() {
		return inv;
	}

	public ExternalAccountingS getAccounting() {
		return extAcc;
	}

	public SaleLog getSaleLog() {
		return log;
	}

}
