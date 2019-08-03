package com.chaney.limiters.limiters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器限流算法
 */
public class CountLimiter {

    private final long msec;                            // 每过msec毫秒重置remainCount
    private final int count;                            // msec毫秒的周期内允许处理count个请求
    private AtomicInteger remainCount;                  // msec毫秒的周期内还能处理remainCount个请求
    private boolean running;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private CountLimiter(long msec, int count) {
        this.msec = msec;
        this.count = count;
        this.remainCount = new AtomicInteger(count);
        this.running = false;
    }

    public static CountLimiter create(long msec, int count) {
        return new CountLimiter(msec, count);
    }

    public boolean acquire() {
        if (!this.running) startRun();
        if (remainCount.get() > 0) {
            remainCount.decrementAndGet();
            System.out.println("成功处理一个请求，remainCount:" + getRemainCount()+ " " + df.format(new Date()));
            return true;
        }  else {
            System.out.println("请求被回绝！"+ " " + df.format(new Date()));
            return false;
        }
    }

    private void startRun() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remainCount.set(count);
                System.out.println("CountLimiter: 已重置count=" + count + ", " + df.format(new Date()));
            }
        }, 0, msec);
        this.running = true;
    }

    public int getRemainCount() {
        return remainCount.get();
    }
}
