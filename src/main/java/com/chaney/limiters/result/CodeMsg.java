package com.chaney.limiters.result;

import lombok.Data;

@Data
public class CodeMsg {
    private int code;
    private String msg;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg UNKNOWN_LIMITER = new CodeMsg(500001, "unknown limiter!");

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
