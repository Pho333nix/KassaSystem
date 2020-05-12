package saleProcess.integration.Discount;


import java.util.HashMap;

class  DiscountByID implements DiscountMatcher {
    public DiscountRate normalRate= new DiscountRate(1.0);
    public DiscountRate returnRate;

    /**
     * This constructor is written explicitly to make sure that the constructor is package-private
     */
    DiscountByID(){}

    @Override
    public DiscountRate checkDiscount(int CustomerID, HashMap itemsToBeSold, HashMap availableDiscounts) {

        return checkIfIdExistsAndReturnRate(CustomerID, itemsToBeSold ,availableDiscounts);
    }

    private DiscountRate checkIfIdExistsAndReturnRate(int customerID, HashMap itemsToBeSold, HashMap availableDiscountsyID){
        //return (availableDiscountsyID.containsKey(customerID) )? (availableDiscountsyID.get(customerID)):  normalRate;
        if(availableDiscountsyID.containsKey(customerID)){
            returnRate= (DiscountRate) availableDiscountsyID.get(customerID);
        }else{
            returnRate = new DiscountByItmes().checkDiscount(customerID, itemsToBeSold, availableDiscountsyID );
        }
        return returnRate;
    }


}
