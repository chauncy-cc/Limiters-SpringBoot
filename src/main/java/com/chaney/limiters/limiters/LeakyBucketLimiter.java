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
public class LeakyBucketLimiter {

    private final int outRate;                      // 每秒处理outRate个请求
    private final int capacity;                     // 漏斗容量
    private LinkedBlockingQueue<Integer> queue;     // 生产消费者队列
    private boolean isRunning = false;              // 是否开始运行不断处理请求的线程
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

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
                try {
                    int requestIndex = queue.take();
                    // 可以根据requestIndex选择对应的业务逻辑来处理。应该可以autowired进来一个service
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("success！" + Thread.currentThread().getName() + " " + df.format(new Date()));
            }
        }, 0, 1000/outRate);
        this.isRunning = true;
    }

    // 放入队列等待处理
    public boolean acquire() {
        if (!isRunning) startRunning();
        if (queue.size() < capacity) {
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }
        return true;
    }
}
