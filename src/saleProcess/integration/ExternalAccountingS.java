package saleProcess.integration;

import saleProcess.model.Sale;

import java.util.ArrayList;

public class ExternalAccountingS {

	ArrayList<Sale> saleArrayList = new ArrayList<>();

	public  ExternalAccountingS() {

	}

	void updateAccount(Sale sale) {
		saleArrayList.add(sale);
	}

}
