package com.danny.cloud;

import com.danny.cloud.annotation.ExcludeFromComponentScan;
import com.danny.cloud.core.RoutingBeanPostProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by danny on 2017-07-31.
 */

@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class)})
@MapperScan(basePackages = "com.danny.cloud.mapper")
@EnableTransactionManagement
@ServletComponentScan  //开启servlet注解
public class MySpringBootApplication {
    @Bean
    public RoutingBeanPostProcessor routingBeanPostProcessor() {
        return new RoutingBeanPostProcessor();
    }

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);

    }

}
