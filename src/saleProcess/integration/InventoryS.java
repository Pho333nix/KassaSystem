package saleProcess.integration;


import java.util.HashMap;

/**
 * This class should contact an external inventory system to get information
 * about out items. since we do not have such a system we will create a
 * fictional database representing that external system. that's where our
 * items will be fetched from. This class implements the singleton pattern, meaning
 * there will only be one instance of this class from the moment the program starts.
 * This is done to stop the creation of endless copies of this class when only one is needed.
 * */
public class InventoryS{
	private static final InventoryS INVENTORY_SINGLETON = 	new InventoryS();
	private ItemDTO itemDTO;
	private HashMap<Integer, Item> itemDB= new HashMap<Integer, Item>();
	private HashMap<ItemDTO, Integer> mapOfBoughtItems = new HashMap<>();
	private final int dbCrasher= 10;

	/**
	 * The Constructor should establish connection to a database somwhere, we
	 * will now create a fake one here.
	 * */
	public InventoryS() {
		//create a fake database to fetch data from. perhabs a symboltable
		// with dummy values?
		fictionalDB();
	}

	public static InventoryS getInventorySingleton() {
		return INVENTORY_SINGLETON;
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

	public ItemDTO getItemInfo(int itemID) throws ItemNotFoundException, ConnectionToDBFailedException {
		if(itemID==dbCrasher){
			throw new ConnectionToDBFailedException("The system failed in it's attempt to " +
					"reach the inventory database");
		} else if(itemExists(itemID)){
			createItemDTO(itemID);
			return itemDTO;
		}else {
			throw new ItemNotFoundException("item: "+itemID+" was not found in database");
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

	/**
	 * This method receives a hasmap of sold items and their quantites.
	 * That information is going to be used here to decrase the
	 * quantity of each one of those items in stock.
	 * @param mapOfBoughtItems
	 */
	public void updateInventory(HashMap mapOfBoughtItems) {

		this.mapOfBoughtItems=mapOfBoughtItems;
		changeDB();
	}

	private void changeDB(){
		mapOfBoughtItems.forEach((itemDTO, quantity) ->
				updateItemQuantityInStock(getCurrentItemQuantityInDB(itemDTO), quantity));

	}
	private Item getCurrentItemQuantityInDB(ItemDTO itemDTO){
		return itemDB.get(itemDTO.getItemID());
	}
	private void updateItemQuantityInStock(Item item, int quantityToReduceWith){
		item.setNewQuantityINStock(item.getQuantityInStock() - quantityToReduceWith);
		System.out.println("stock reduced with: " + quantityToReduceWith);
	}


	private void fictionalDB(){
		Item apple = new Item(1.12, "apple", "grown in New Zealand",
			7, 11111, 200);
		Item salmon = new Item(1.25, "salmon", "Norwegian wild salmon",
				125, 22222,200);
		Item  stapler= new Item(1.15, "stapler", "The best stapler there is",
				56, 33333, 200);
		Item potato = new Item(1.12, "potato", "from local farms near you",
				9, 44444, 200);
		itemDB.put(apple.getItemID(), apple);
		itemDB.put(salmon.getItemID(), salmon);
		itemDB.put(stapler.getItemID(), stapler);
		itemDB.put(potato.getItemID(), potato);
	}


}
