package com.getir.readingisgood.exception;

/**
 * @author UmutBayram
 */
public enum ExceptionTypeEnum {
    BUSINESS_EXCEPTION("REDISBOOK_000"),
    NOT_FOUND_EXCEPTION("REDISBOOK_001"),
    STOCK_NOT_ENOUGH("REDISBOOK_002"),
    VALIDATION_ERROR("REDISBOOK_003");

    private String code;

    ExceptionTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
