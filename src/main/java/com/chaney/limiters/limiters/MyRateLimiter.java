package com.chaney.limiters.limiters;

/**
 * 令牌桶限流算法
 */
public class MyRateLimiter extends Limiter {

    MyRateLimiter(int qps) {
        super(qps);
    }

    @Override
    protected boolean tryAcquire() {
        return false;
    }
}
