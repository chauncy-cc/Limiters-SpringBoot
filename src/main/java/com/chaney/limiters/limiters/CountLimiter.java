package com.chaney.limiters.limiters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器限流算法
 */
public class CountLimiter extends Limiter {

    private int count;                          // 计数器
    private long lastTime;                      // 时间戳

    public CountLimiter(int qps) {
        super(qps);
        count = 0;
        lastTime = 0;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        if (now - lastTime > 1000) {
            lastTime = now>>3<<3;               // 保证时间戳后三位都是0.（是否这么做不太影响最后实现，但这样更精确）
            count = 1;
            return true;
        } else if (count < qps) {
            count++;
            return true;
        } else {
            return false;
        }
    }
}
