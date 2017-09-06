package com.danny.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.danny.cloud.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RestController
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private LoadBalancerClient loadBalancerClient;

  @GetMapping("/movie/{id}")
  public User findById(@PathVariable Long id, HttpServletRequest req, HttpServletResponse resp) {
    // http://localhost:7900/simple/
    // VIP virtual IP
    // HAProxy Heartbeat
    HttpHeaders headers = new HttpHeaders();
    Enumeration<String> headerNames = req.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = req.getHeader(key);
      headers.add(key, value);
    }
//    String restStrResult = restTemplate.postForObject(url, /*携带header*/new HttpEntity<String>(headers), String.class,
//            　　　　　　　　　　　　 /*携带parameter*/request.getParameterMap());

    //目前没有发现发送携带header信息的getForObject方法
    return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
  }

  @GetMapping("/test")
  public String test() {

    ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
    System.out.println("111" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

//    ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("microservice-provider-user2");
//    System.out.println("222" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());

    return "1";
  }
}
