package com.getir.readingisgood.exception;

/**
 * @author UmutBayram
 */
public class BusinessException extends RuntimeException {
    private String message;
    private String errorCode;

    public BusinessException(String message) {
        super(message);
        this.errorCode = ExceptionTypeEnum.BUSINESS_EXCEPTION.getCode();
        this.message = message;
    }

    public BusinessException(String errorCode, String message) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }


    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
