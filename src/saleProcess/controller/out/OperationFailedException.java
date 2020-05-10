package saleProcess.controller.out;

/**
 * An Exception that is thrown and it is caused by another exception, then this will be thrown.
 * This is mainly how the view will get exception messages.
 */
public class OperationFailedException extends Exception{
    /**
     * This constructor will instantiate a new object of this class
     * and also give information about the cause of the exception
     *
     * @param msg description of the exception itself
     * @param cause is the exception that is the reason this exception arose
     */
    public OperationFailedException(String msg, Exception cause){
        super(msg, cause);
    }
}
