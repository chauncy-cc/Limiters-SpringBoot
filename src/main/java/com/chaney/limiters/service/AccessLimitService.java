package com.chaney.limiters.service;

import com.chaney.limiters.enums.LimiterEnum;
import com.chaney.limiters.limiters.Limiter;
import com.chaney.limiters.limiters.LimiterFactory;
import org.springframework.stereotype.Service;

/**
 * 提供限流服务（可选三种限流算法）默认qps一秒10个请求
 */
@Service
public class AccessLimitService {

    // 计数器实例
    private Limiter countLimiter = LimiterFactory.getCountLimiter(LimiterEnum.COUNT_LIMITER, 10);
    // 漏桶实例
    private Limiter leakyBucketLimiter = LimiterFactory.getCountLimiter(LimiterEnum.LEAKY_BUCKET_LIMITER, 10);
    // 令牌桶实例
    private Limiter rateLimiter = LimiterFactory.getCountLimiter(LimiterEnum.RATE_LIMITER, 10);

    // 谷歌开源包：令牌桶算法
    public boolean rateLimiterAcquire() {
        return rateLimiter.tryAcquire();
    }

    // 漏桶算法
    public boolean budgetLimiterAcquire() {
        return leakyBucketLimiter.tryAcquire();
    }

    // 计算器算法
    public boolean countAcquire() {
        return countLimiter.tryAcquire();
    }
}
