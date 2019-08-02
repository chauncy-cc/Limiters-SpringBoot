package com.chaney.limiters.service;

import com.chaney.limiters.limiters.CountLimiter;
import com.chaney.limiters.limiters.LeakyBucketLimiter;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * 提供限流服务（可选三种限流算法）默认一秒5个请求
 */
@Service
public class AccessLimitService {

    // 计数器实例
    private CountLimiter countLimiter = CountLimiter.create(1000, 5);
    // 漏桶实例
    private LeakyBucketLimiter leakyBucketLimiter = LeakyBucketLimiter.create(50, 5);
    // 令牌桶实例
    private RateLimiter rateLimiter = RateLimiter.create(5);


    // 谷歌开源包：令牌桶算法
    public boolean rateLimiterAcquire() {
        return rateLimiter.tryAcquire();
    }

    // 漏桶算法
    public boolean budgetAcquire() {
        // return leakyBucketLimiter.aquire();
        return false;
    }

    // 计算器算法
    public boolean countAcquire() {
        return countLimiter.acquire();
    }
}
