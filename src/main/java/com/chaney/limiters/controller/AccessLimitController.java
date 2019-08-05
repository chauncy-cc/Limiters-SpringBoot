package com.chaney.limiters.controller;

import com.chaney.limiters.enums.LimiterEnum;
import com.chaney.limiters.limiters.AccessLimit;
import com.chaney.limiters.result.CodeMsg;
import com.chaney.limiters.result.Result;
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

    @RequestMapping("/counter")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterEnum.COUNT_LIMITER)
    public Result counter() {
        if (accessLimitService.countAcquire()) {
            // （业务逻辑）
            return new Result(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new Result(CodeMsg.ACQUIRE_LIMITED);
    }

    @RequestMapping("/bucket")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterEnum.LEAKY_BUCKET_LIMITER)
    public Result bucket() {
        if (accessLimitService.budgetLimiterAcquire()) {
            // (业务逻辑)
            return new Result(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new Result(CodeMsg.ACQUIRE_LIMITED);
    }

    @RequestMapping("/rateLimiter")
    @ResponseBody
    @AccessLimit(qps = 10, limiterEnum = LimiterEnum.MYRATE_LIMITER)
    public Result rateLimiter() {
        if (accessLimitService.rateLimiterAcquire()) {
            // （业务逻辑）
            return new Result(CodeMsg.ACQUIRE_SUCCESS);
        }
        return new Result(CodeMsg.ACQUIRE_LIMITED);
    }
}
