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
	public Receipt(HashMap items, LocalTime timeOfSale, Payment payment, double price){
		this.itemsInSale= items;
		this.timeOfSale=LocalTime.of(timeOfSale.getHour(), timeOfSale.getMinute(), timeOfSale.getSecond());
		this.payment=payment;
		receiptText(price);
	}

	private void receiptText(double price){
		sr.append(" 		FoodNStuff		\n");
		sr.append(" address: someplace		\n");
		sr.append(" time:   "+timeOfSale.toString() +"\n");
		sr.append("---------Receipt----------"+"\n");
		sr.append("\n");
		sr.append(" Item name   price	VAT rate \n");
		itemsInSale.forEach((itemDTO, integer) -> {
			String Name=" "+itemDTO.getItemName();
			String priceWithQuantity=" "+itemDTO.getPrice() + " x " + integer;
			String vat=" "+itemDTO.getVAT();
			String line= String.format("%.15s %5s %5s \n", Name,priceWithQuantity, vat);
			sr.append(line);
		});
		sr.append("---------------------------"+"\n");
		sr.append(" payed amount:\t" + payment.getPayedAmount()+"\n");
		sr.append(" TotalPrice:\t" + payment.getTotalPrice() +"\n");
		sr.append(" change:\t" + payment.getChange()+"\n");
		sr.append("---------------------------"+"\n");

	}


	public StringBuilder getReceiptSR(){
		return sr;
	}


}
