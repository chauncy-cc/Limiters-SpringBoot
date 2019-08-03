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

    @RequestMapping("/rateLimiter")
    @ResponseBody
    public boolean rateLimiter() {
        if (accessLimitService.rateLimiterAcquire()) {
            // （业务逻辑）
            return true;
        }
        return false;
    }

    @RequestMapping("/counter")
    @ResponseBody
    public boolean counter() {
        if (accessLimitService.countAcquire()) {
            // （业务逻辑）
            return true;
        }
        return false;
    }

//    @RequestMapping("/bucket")
//    @ResponseBody
//    public boolean bucket() {
//        if (accessLimitService.budgetAcquire()) {
//
//        }
//    }

}
