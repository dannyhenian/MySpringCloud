package com.danny.cloud;

import com.danny.cloud.ExcludeFromComponentScan;
import com.netflix.client.config.IClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
@ExcludeFromComponentScan
public class TestConfiguration {
//    @Autowired
//    IClientConfig config;

  //定义使用的负载规则是哪种
  @Bean
  public IRule ribbonRule() {
    return new RandomRule();
  }
}
