package saleProcess.model;

import saleProcess.integration.ItemDTO;

import java.time.LocalTime;
import java.util.HashMap;

/**
 * An instance of this object will represent a receipt of a sale.
 * The receipt will serve as proof of payment.
 * */
public class Receipt {
	private HashMap<ItemDTO, Integer> itemsInSale=new HashMap<>();
	private LocalTime timeOfSale;
	private Payment payment;
	private StringBuilder sr = new StringBuilder();
	Receipt(HashMap items, LocalTime timeOfSale, Payment payment, double price){
		this.itemsInSale= items;
		this.timeOfSale=timeOfSale;
		this.payment=payment;
		receiptText(price);
	}

	private void receiptText(double price){
		sr.append(" 		FoodNStuff		");
		sr.append(" address: someplace		");
		sr.append(" time:   "+timeOfSale.toString());
		sr.append("---------Receipt----------");
		sr.append("\n");
		itemsInSale.forEach((itemDTO, integer) -> {
			sr.append(" " +itemDTO.getItemName() + "\t" + itemDTO.getPrice() + "x" + integer);
			sr.append("\n");
		});
		sr.append("payed amount:\t" + payment.getPayedAmount());
		sr.append(" TotalPrice:\t" + payment.getTotalPrice() );
		sr.append(" change:\t" + payment.getChange());
		sr.append("---------------------------");

	}

	public String toString(){
		return sr.toString();
	}


}
