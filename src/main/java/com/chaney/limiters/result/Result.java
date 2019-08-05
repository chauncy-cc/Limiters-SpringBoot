package com.chaney.limiters.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public Result(CodeMsg cm) {
        if (cm == null) return;
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }
}
