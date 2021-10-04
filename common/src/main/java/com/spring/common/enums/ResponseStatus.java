package com.spring.common.enums;

public enum ResponseStatus {
    SUCCESS(200),
    FAIL(400);

    int code;
    ResponseStatus(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
