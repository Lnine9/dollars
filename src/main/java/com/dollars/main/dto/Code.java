package com.dollars.main.dto;

public enum Code {

    SUCCESS(200),

    ERROR(13);

    int code;

    Code(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
