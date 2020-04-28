package saleProcess.integration;

public class ItemDTO {

	private final int VAT;

	private final String itemDescription;

	private final double price;

	private final int itemID;
	private final String itemName;
	/**
	 * This constructor creates a DTO of a specific Item and it contains all
	 * it's information. This will be created by the inventory system which will fetch
	 * all the relevant information about the item. The pupose of an object of
	 * this class is to provide information about the item to various parts
	 * of our system (sale, receipt, printer etc). This will represent a single item.
	 *
	 */
	public ItemDTO (int VAT,String itemName, String itemDecription, double price, int itemID)
	{
		this.VAT=VAT;
		this.itemDescription=itemDecription;
		this.price=price;
		this.itemID=itemID;
		this.itemName=itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public double getPrice() {
		return price;
	}
}
