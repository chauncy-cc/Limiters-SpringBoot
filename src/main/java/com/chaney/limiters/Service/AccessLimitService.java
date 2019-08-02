package com.chaney.limiters.Service;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class AccessLimitService {

    // permission per second. 每秒5个请求
    RateLimiter rateLimiter = RateLimiter.create(5);

    public boolean rateLimiterTryAcquire() {
        return rateLimiter.tryAcquire();
    }



}
