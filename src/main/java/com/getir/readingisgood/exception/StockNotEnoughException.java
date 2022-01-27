package com.getir.readingisgood.exception;

public class StockNotEnoughException extends BusinessException {
    public StockNotEnoughException(String message, String errorCode) {
        super(message, errorCode);
    }
}
