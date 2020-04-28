package saleProcess.model;

import saleProcess.integration.ItemDTO;

import java.util.HashMap;
import java.util.List;

/**
 * An instance of this object will represent a receipt of a sale.
 * The receipt will serve as prof of payment.
 * */
public class Receipt {
	private HashMap<ItemDTO, Integer> itemsInSale=new HashMap<>();
	Receipt(HashMap items){
		this.itemsInSale= items;
	}
}
