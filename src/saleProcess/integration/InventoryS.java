package saleProcess.integration;

import saleProcess.model.Sale;

import java.util.HashMap;

/**
 * This class should contact an external inventory system to get information
 * about out items. since we do not have such a system we will create a
 * fictional database representing that external system. that's where our
 * items will be fetched from
 * */
public class InventoryS{

	private ItemDTO itemDTO;
	private HashMap<Integer, Item> itemDB= new HashMap<Integer, Item>();

	/**
	 * The Constructor should establish connection to a database somwhere, we
	 * will now create a fake one here.
	 * */
	public InventoryS() {
		//create a fake database to fetch data from. perhabs a symboltable
		// with dummy values?
		fictionalDB();
	}

	/**
	 * This method confirms the existence of an item in our database
	 * @param itemID an id that uniquely identifies item in our database.
	 * @return boolean true or false, item exists or not.
	 */
	private boolean itemExists(int itemID){
		return itemDB.containsKey(itemID);
	}
	/**
	 * This method is called from the controller
	 * it will check whether an item exists in our
	 * database or not with the use of a private method itemExists.
	 * If the item Exists a DTO containing it's information will
	 * be created and returned.
	 * @param itemID an id that uniquely identifies item in our database.
	 * @return will retun either a DTO of the item or null if it does not
	 * exist
	 * */
	public ItemDTO getItemInfo(int itemID) {
		if(itemExists(itemID)){
			createItemDTO(itemID);
			return itemDTO;
		}else {
			return null;
		}
	}

	/**
	 * creates an ItemDTO of an item from the database
	 * @param itemID an id that uniquely identifies item in our database.
	 */
	private void createItemDTO(int itemID){
		Item item = itemDB.get(itemID);
		itemDTO= new ItemDTO(item.getVAT(), item.getItemName(), item.getItemDescription(), item.getPrice(),
				item.getItemID());
	}
	public void updateInventory(Sale sale) {

	}

	private void fictionalDB(){
		Item apple = new Item(12, "apple", "grown in New Zeeland",
			7, 11111);
		Item salmon = new Item(25, "salmon", "Norwegian wild salmon",
				125, 22222);
		Item  stapler= new Item(15, "stapler", "The best stapler there is",
				56, 33333);
		Item potato = new Item(12, "potatoe", "from local farms near you",
				9, 44444);
		itemDB.put(new Integer(apple.getItemID()), apple);
		itemDB.put(new Integer(salmon.getItemID()), salmon);
		itemDB.put(new Integer(stapler.getItemID()), stapler);
		itemDB.put(new Integer(potato.getItemID()), potato);
	}


}
