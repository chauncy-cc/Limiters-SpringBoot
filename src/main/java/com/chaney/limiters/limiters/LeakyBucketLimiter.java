package com.chaney.limiters.limiters;

import com.sun.org.apache.xpath.internal.operations.String;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 漏桶限流算法
 */
public class LeakyBucketLimiter extends Limiter {

    private final long capacity;                                    // 水桶容量, 一秒流光
    private long remaining;                                         // 目前水桶剩下的水量
    private long lastTime;                                          // 时间戳

    LeakyBucketLimiter(int qps) {
        super(qps);
        capacity = qps;
        remaining = capacity;
        lastTime = 0;
    }

    @Override
    protected boolean tryAcquire() {
        long now = System.currentTimeMillis();
        long outWater = ((now - lastTime)/1000)*capacity;           // 计算这段时间匀速流出的水
        if (outWater > remaining) {
            remaining = 1;
            return true;
        } else {
            remaining -= outWater;
            if (remaining + 1 < capacity) {
                remaining += 1;
                return true;
            } else return false;
        }
    }
}
