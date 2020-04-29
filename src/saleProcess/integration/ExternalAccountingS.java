package saleProcess.integration;

import saleProcess.model.Sale;

import java.util.ArrayList;

public class ExternalAccountingS {

	private ArrayList<Sale> saleArrayList = new ArrayList<>();

	public  ExternalAccountingS() {

	}

	public void updateAccount(Sale sale) {
		saleArrayList.add(sale);
		System.out.println(sale.toString());
	}

}
