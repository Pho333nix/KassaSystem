package saleProcess.integration;

import saleProcess.model.Receipt;

public class Printer {

	public void printReceipt(Receipt receipt) {
		StringBuilder sr =receipt.getReceiptSR();
		System.out.println(sr.toString());
	}


}
