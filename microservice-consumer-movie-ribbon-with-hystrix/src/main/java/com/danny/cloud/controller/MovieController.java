package com.danny.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.danny.cloud.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import rx.Observable;
import rx.Subscriber;

import java.util.List;
import java.util.concurrent.Future;

@RestController
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;



  @GetMapping("/movie/{id}")
//  @CacheResult(cacheKeyMethod = "findByIdCacheKey")  //设置请求缓存  @CacheKey
//  @CacheRemove()                      //清除缓存
  @HystrixCommand(fallbackMethod = "findByIdFallback")     //同步执行
//  @HystrixCommand(fallbackMethod = "findByIdFallback",   //线程隔离策略
//          commandProperties = { @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE") })
  public User findById(@PathVariable Long id) {
    return this.restTemplate.getForObject("http://microservice-provider-user/simple/" + id, User.class);
  }

  private Long findByIdCacheKey(Long id){
    return id;
  }

  @HystrixCommand                                      //异步执行
  public Future<User> getUserByIdAsync(final String id ){
    return new AsyncResult<User>() {
      @Override
      public User invoke() {
        return restTemplate.getForObject("http://microservice-provider-user/simple/{1}",User.class,id);
      }
    };
  }


   // 响应式执行
  @HystrixCommand(observableExecutionMode = ObservableExecutionMode.EAGER)
  public Observable<User> getUserById(final String id ){
    return  Observable.create(new Observable.OnSubscribe<User>() {
      @Override
      public void call(Subscriber<? super User> observer) {
        try {
          if(!observer.isUnsubscribed()){
            User user = restTemplate.getForObject("http://microservice-provider-user/simple/{1}",User.class,id);
            observer.onNext(user);
            observer.onCompleted();
          }
        } catch (RestClientException e) {
          observer.onError(e);
        }

      }
    });
  }


  public User findByIdFallback(Long id) {
    User user = new User();
    user.setId(0L);
    return user;
  }



  //=============合并请求
  @HystrixCollapser(batchMethod = "findAll",  //batchMethod指明批量请求的实现方法
          collapserProperties = {@HystrixProperty(name="timerDelayInMilliseconds",value = "100")})
    public User find (Long id){
        return null;
      }

  @HystrixCommand
  public List<User> findAll(List<Long> ids){
//      return restTemplate.getForObject("http://microservice-provider-user/simple/{1}",User.class, StringUtils.join(ids,","));
  return null;
    }


}
