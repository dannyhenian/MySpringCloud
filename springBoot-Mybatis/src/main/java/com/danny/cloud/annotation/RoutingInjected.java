package com.danny.cloud.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 类似Autowired,声明了该注解的属性将会被注入一个路由代理类实例
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingInjected {
}
