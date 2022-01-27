package com.getir.readingisgood.exception;

public enum ExceptionTypeEnum {
    BUSINESS_EXCEPTION("REDISBOOK_000"),
    NOT_FOUND_EXCEPTION("REDISBOOK_001"),
    STOCK_NOT_ENOUGH("REDISBOOK_002");

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
