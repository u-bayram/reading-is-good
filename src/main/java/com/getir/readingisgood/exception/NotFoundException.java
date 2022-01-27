package com.getir.readingisgood.exception;

/**
 * @author UmutBayram
 */
public class NotFoundException extends BusinessException {

    public NotFoundException(String message, String errorCode) {
        super(message, errorCode);
    }

}
