package com.danny.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroserviceSimpleConsumerMovieApplication {

  @Bean(name = "rt1")
//  @Primary
  public RestTemplate rt1() {
    //RestTemplate作为HTTP客户端调用远程HTTP服务     JDK原生的URLConnection、Apache的Http Client、Netty的异步HTTP Client
    return new RestTemplate();
  }

  @Bean(name = "rt2")
  public RestTemplate rt2() {
    //RestTemplate作为HTTP客户端调用远程HTTP服务     JDK原生的URLConnection、Apache的Http Client、Netty的异步HTTP Client
    return new RestTemplate();
  }


  public static void main(String[] args) {
    SpringApplication.run(MicroserviceSimpleConsumerMovieApplication.class, args);
  }
}
