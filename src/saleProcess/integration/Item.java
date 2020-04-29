package saleProcess.integration;

public class Item {
    private final double VAT;
    private final String itemDescription;
    private final double price;
    private final int itemID;
    private final String itemName;
    private int quantityInStock;

    public Item(double VAT, String itemName, String itemDecription, double price, int itemID,
                int quantityInStock) {
        this.VAT = VAT;
        this.itemDescription = itemDecription;
        this.price = price;
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantityInStock=quantityInStock;
    }

    public int getItemID() {
        return itemID;

    }
    public double getVAT(){
        return VAT;
    }

    public double getPrice() {
        return price;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setNewQuantityINStock(int newQuantity){
        this.quantityInStock=newQuantity;
    }
}
