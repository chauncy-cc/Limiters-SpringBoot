package com.chaney.limiters.limiters;

public class CountWindowLimiter extends Limiter {



    public CountWindowLimiter(int qps) {
        super(qps);
    }

    @Override
    protected boolean tryAcquire() {
        return false;
    }
}
