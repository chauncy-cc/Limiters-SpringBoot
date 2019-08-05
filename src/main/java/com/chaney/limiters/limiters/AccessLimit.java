package com.chaney.limiters.limiters;

import com.chaney.limiters.enums.LimiterEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
    int qps();
    LimiterEnum limiterEnum();
}
