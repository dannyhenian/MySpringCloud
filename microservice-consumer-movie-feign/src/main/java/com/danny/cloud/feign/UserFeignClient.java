package com.danny.cloud.feign;

import com.netflix.ribbon.proxy.annotation.Http;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.danny.cloud.entity.User;

@FeignClient("microservice-provider-user")
public interface UserFeignClient {
  @Headers("X-Auth-Token: {id}")
  @RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id); // 两个坑：1. @GetMapping不支持   2. @PathVariable得设置value
                     //@RequestParam
  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public User postUser(@RequestBody User user);   //@RequestHeader

  // 该请求不会成功，只要参数是复杂对象，即使指定了是GET方法，feign依然会以POST方法进行发送请求。可能是我没找到相应的注解或使用方法错误。
  @RequestMapping(value = "/get-user", method = RequestMethod.GET)
  public User getUser(User user);

//  @RequestMapping(value = "/hello2", method = RequestMethod.GET)
//  User hello(@RequestHeader("name") String name,@RequestHeader("age") Integer age);
}
