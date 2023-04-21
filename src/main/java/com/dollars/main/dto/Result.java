package com.dollars.main.dto;

import lombok.Data;

@Data
public class Result<T> {

    private final int code;

    private final String msg;

    private final T data;

    public Result(Code code, String msg, T data) {
        this.code = code.getCode();
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(String msg, T data){
        return new Result<>(Code.SUCCESS, msg, data);
    }

    public static Result<Object> success(String msg){
        return new Result<>(Code.SUCCESS, msg, null);
    }

    public static Result<Object> success(){
        return new Result<>(Code.SUCCESS, "", null);
    }

    public static <T> Result<T> error(String msg, T data){
        return new Result<>(Code.ERROR, msg, data);
    }

    public static Result<Object> error(String msg){
        return new Result<>(Code.ERROR, msg, null);
    }

    public static Result<Object> error(){
        return new Result<>(Code.ERROR, "", null);
    }
}
