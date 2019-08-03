package com.chaney.limiters.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLimitControllerTest {

    @Autowired
    AccessLimitController accessLimitController;

    @Test
    public void rateLimiter() throws InterruptedException {
        for (int i= 0; i<20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(accessLimitController.rateLimiter());
                }
            }).start();
            Thread.sleep(100);
        }
    }

    @Test
    public void counter() throws InterruptedException {
        for (int i= 0; i<20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(accessLimitController.counter());
                }
            }).start();
            Thread.sleep(100);
        }
    }

    @Test
    public void bucket() throws InterruptedException {
        for (int i= 0; i<20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //System.out.println(accessLimitController.bucket());
                }
            }).start();
            Thread.sleep(100);
        }
    }
}