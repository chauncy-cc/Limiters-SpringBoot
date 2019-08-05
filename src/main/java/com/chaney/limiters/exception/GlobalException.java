package com.chaney.limiters.exception;

import com.chaney.limiters.result.CodeMsg;

public class GlobalException extends RuntimeException {

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCodeMsg () {
        return cm;
    }

    public void setCm (CodeMsg cm) {
        this.cm = cm;
    }

}
