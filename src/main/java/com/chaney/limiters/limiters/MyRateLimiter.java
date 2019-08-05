package com.chaney.limiters.limiters;

public class MyRateLimiter extends Limiter {

    MyRateLimiter(int qps) {
        super(qps);
    }

    @Override
    protected boolean tryAcquire() {
        return false;
    }
}
