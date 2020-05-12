package saleProcess.integration.Discount;


import java.util.HashMap;

/**
 * This class is one of the strategies available to us for checking
 * if a customer is eligible for a discount based on their id. If so
 * a new rate is returned otherwise another strategy is executed. at the end if there was no discount a
 * rate of 1 is returned.
 */
public class  DiscountByID implements DiscountMatcher {
    //public DiscountRate normalRate= new DiscountRate(1.0);
    public DiscountRate returnRate;

    /**
     * A method that given some parameters checks if a customer is eligible for a discount by
     * their customer ID. If not another strategy is called that checks for another type of discount
     * @param CustomerID is the parameter needed here, check for discount rates specific for this customer
     * @param itemsToBeSold is a parameter another strategy will need
     * @param  availableDiscountsByID all the available discounrates based on items
     * @param availableDiscountsByItems all the available discounrates based on items
     * @return a rate that will be applied on the total price.
     */
    @Override
    public DiscountRate checkDiscount(int CustomerID, HashMap availableDiscountsByID, HashMap availableDiscountsByItems, HashMap itemsToBeSold) {

        return checkIfIdExistsAndReturnRate(CustomerID,   availableDiscountsByID, availableDiscountsByItems, itemsToBeSold);
    }


    private DiscountRate checkIfIdExistsAndReturnRate(int customerID, HashMap availableDiscountsyID,HashMap availableDiscountsByItems, HashMap itemsToBeSold){
        //return (availableDiscountsyID.containsKey(customerID) )? (availableDiscountsyID.get(customerID)):  normalRate;
        if(availableDiscountsyID.containsKey(customerID)){
            returnRate= (DiscountRate) availableDiscountsyID.get(customerID);
        }else{
            returnRate = new DiscountByItmes().checkDiscount(customerID, availableDiscountsyID, availableDiscountsByItems,itemsToBeSold );
        }
        return returnRate;
    }


}
