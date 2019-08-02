package com.chaney.limiters.controller;

import com.chaney.limiters.service.AccessLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AccessLimitController {

    @Autowired
    private AccessLimitService accessLimitService;

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    @RequestMapping("/rateLimiter")
    @ResponseBody
    public String rateLimiter() {
        if (accessLimitService.rateLimiterTryAcquire()) {
            // 业务逻辑
            return "Success! " + Thread.currentThread().getName() + " " + df.format(new Date());
        } else {
            return "Fail! " + Thread.currentThread().getName() + " " + df.format(new Date());
        }
    }


    @RequestMapping("/counter")
    @ResponseBody
    public String counter() {
        if (accessLimitService.countAcquire()) {
            // 业务逻辑
            return "Success! " + Thread.currentThread().getName() + " " + df.format(new Date());
        } else return "Fail! " + Thread.currentThread().getName() + " " + df.format(new Date());
    }


    @RequestMapping("/bucket")
    @ResponseBody
    public String bucket() {

        return "Fail! " + Thread.currentThread().getName() + " " + df.format(new Date());
    }

}
