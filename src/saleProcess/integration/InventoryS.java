package saleProcess.integration;

import saleProcess.model.Sale;

/**
 * This class should contact an external inventory system to get information
 * about out items. since we do not have such a system we will create a
 * fictional database representing that external system. that's where our
 * items will be fetched from
 * */
public class InventoryS{

	private ItemDTO itemDTO;

	/**
	 * The Constructor should establish connection to a database somwhere, we
	 * will now create a fake one here.
	 * */
	public InventoryS() {
		//create a fake database to fetch data from. perhabs a symboltable
		// with dummy values?
		fictionalDB();
	}

	public boolean verifyItem(int itemID){
		return true;
	}
	public ItemDTO getIteminfo(int itemID) {
		return null;
	}

	public void updateInventory(Sale sale) {

	}

	private void fictionalDB(){

	}


}
