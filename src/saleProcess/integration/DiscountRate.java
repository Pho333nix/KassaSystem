package saleProcess.integration;

public class DiscountRate {
    private double rateOfDiscount;

    public DiscountRate(double rate){
        rateOfDiscount=rate;
    }

    public double getDiscountRate(){
        return rateOfDiscount;
    }
}
