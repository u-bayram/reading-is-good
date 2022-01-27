package com.getir.readingisgood.exception;

/**
 * @author UmutBayram
 */
public class StockNotEnoughException extends BusinessException {
    public StockNotEnoughException(String message, String errorCode) {
        super(message, errorCode);
    }
}
