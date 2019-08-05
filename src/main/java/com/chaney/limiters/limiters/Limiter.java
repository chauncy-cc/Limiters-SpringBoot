package com.chaney.limiters.limiters;

public abstract class Limiter {

    final int qps;

    Limiter(int qps) {
        this.qps = qps;
    }

    // 获取继续执行的资格（非阻塞）立刻返回成功失败
    protected abstract boolean tryAcquire();

}
