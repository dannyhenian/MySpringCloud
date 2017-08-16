package com.danny.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceSimpleProviderUserApplication1 {

  public static void main(String[] args) {
    SpringApplication.run(MicroserviceSimpleProviderUserApplication1.class, args);
  }
}
