package saleProcess.model;

import saleProcess.integration.ItemDTO;

import java.util.List;

/**
 * An instance of this object will represent a receipt of a sale.
 * The receipt will serve as prof of payment.
 * */
public class Receipt {
	List<ItemAndQuantity> items;
	Receipt(List<ItemAndQuantity> items) {
		this.items= items;
	}
}
