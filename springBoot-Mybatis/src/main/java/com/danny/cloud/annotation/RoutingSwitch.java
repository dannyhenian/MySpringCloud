package com.danny.cloud.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 是一个配置开关，声明了控制路由的开关属性
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingSwitch {

    /*
    * 在配置系统中开关的属性名称，应用系统将会实时读取配置系统中对应开关的值来决定是调用哪个版本
    * */
    String value() default "";
}
