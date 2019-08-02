package com.chaney.limiters.controller;

import com.chaney.limiters.Service.AccessLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TestLimitController {

    @Autowired
    private AccessLimitService accessLimitService;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/rateLimiter")
    @ResponseBody
    public String rateLimiter() {
        if (accessLimitService.rateLimiterTryAcquire()) {
            return "Success! " + Thread.currentThread().getName() + " " + df.format(new Date());
        } else {
            return "Fail! " + Thread.currentThread().getName() + " " + df.format(new Date());
        }
    }


    @RequestMapping("/counter")
    @ResponseBody
    public String counter() {

        return "Fail! " + Thread.currentThread().getName() + " " + df.format(new Date());
    }


    @RequestMapping("/bucket")
    @ResponseBody
    public String bucket() {

        return "Fail! " + Thread.currentThread().getName() + " " + df.format(new Date());
    }

}
