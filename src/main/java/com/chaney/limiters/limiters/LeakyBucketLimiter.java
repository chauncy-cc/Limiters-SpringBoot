package com.chaney.limiters.limiters;

import com.sun.org.apache.xpath.internal.operations.String;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 漏桶限流算法
 */
public class LeakyBucketLimiter {

    private final int outRate;                      // 每秒处理outRate个请求
    private final int capacity;                     // 漏斗容量
    private LinkedBlockingQueue<Integer> queue;     // 生产消费者队列
    private boolean isRunning = false;              // 是否开始运行不断处理请求的线程

    private LeakyBucketLimiter(int capacity, int outRate) {
        this.capacity = capacity;
        this.outRate = outRate;
        this.queue = new LinkedBlockingQueue<>(capacity);
    }

    public static LeakyBucketLimiter create(int capacity, int outRate) {
        return new LeakyBucketLimiter(capacity, outRate);
    }

    private void startRunning() {
        // 1秒钟要处理请求
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < outRate; i++) {
                    try {
                        int requestIndex = queue.take();    // 阻塞取头部

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1000);
        this.isRunning = true;
    }


    // 放入队列等待处理
    public boolean acquire() {
        if (!isRunning) startRunning();
        if (queue.size() < capacity) {
            try {
                queue.put(new Integer(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
