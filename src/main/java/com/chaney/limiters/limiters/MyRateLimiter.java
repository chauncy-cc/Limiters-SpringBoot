package com.chaney.limiters.limiters;

import com.google.common.util.concurrent.RateLimiter;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;

public class MyRateLimiter {

    private final int maxTokenNum;
    private volatile int curTokenNum;  // volatile

    private static Unsafe unsafe = null;
    private static long valueOffset;         // 主要是为了后面的compareAndSwap
    private final Object lock = new Object();

    static {
        try {
            Class<?> clazz = Unsafe.class;
            Field f = clazz.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe)f.get(clazz);
            valueOffset = unsafe.objectFieldOffset(MyRateLimiter.class.
                    getDeclaredField("curTokenNum"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    MyRateLimiter(int i) {
        this.maxTokenNum = i;
        this.curTokenNum = i;
    }

    public static MyRateLimiter create(int i) {
        return new MyRateLimiter(i);
    }

    public boolean tryAcquire() {

        return false;
    }

    private boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    // 迅速补充token，这里是补充单个token的方法
    private void addToken(final ExecutorService executorService) {
        this.curTokenNum = maxTokenNum;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        });
    }

    // 最后再写吧！
}
