package com.chaney.limiters.limiters;

import com.chaney.limiters.enums.LimiterEnum;
import com.chaney.limiters.exception.GlobalException;
import com.chaney.limiters.result.CodeMsg;

public class LimiterFactory {

    public static Limiter getCountLimiter(LimiterEnum limiterEnum, int qps) {
        switch (limiterEnum) {
            case COUNT_LIMITER:
                return new CountLimiter(qps);
            case LEAKY_BUCKET_LIMITER:
                return new LeakyBucketLimiter(qps);
            case MYRATE_LIMITER:
                return new MyRateLimiter(qps);
            default:
                throw new GlobalException(CodeMsg.UNKNOWN_LIMITER);
        }
    }

}
