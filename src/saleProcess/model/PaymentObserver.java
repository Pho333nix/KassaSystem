package saleProcess.model;

/**
 * This is an observer interface that a class in the view will implement to be able to
 * update the view without calls going from this layer lever (model layer) up to the view layer and break the layer pattern.
 */

    public interface PaymentObserver {

        void addPayment(double payment);
    }


