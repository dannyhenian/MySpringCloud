package com.danny.cloud.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/*
*
*
* */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface TaskHandler {

    String taskType() default "";

}

