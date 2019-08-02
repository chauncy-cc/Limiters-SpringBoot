package com.chaney.limiters.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestLimitControllerTest {

    @Autowired
    TestLimitController testLimitController;

    @Test
    public void rateLimiter() throws InterruptedException {
        for (int i= 0; i<20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(testLimitController.rateLimiter());
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
                    System.out.println(testLimitController.counter());
                }
            }).start();
            Thread.sleep(100);
        }
    }

    @Test
    public void bucket() {
    }
}