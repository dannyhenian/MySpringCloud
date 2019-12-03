package com.danny.cloud.controller;

import java.util.ArrayList;
import java.util.List;

import com.ssx.logging.logHandler.AlarmLogHandler;
import com.ssx.logging.logHandler.BusinessLogHandler;
import com.ssx.logging.logHandler.MessageLogHandler;
import com.ssx.logging.logHandler.SystemLogHandler;
import com.ssx.logging.model.LogBaseModel;
import com.ssx.logging.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.danny.cloud.entity.User;
import com.danny.cloud.repository.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class UserController {
  private static SystemLogHandler systemLogger = SystemLogHandler.getLogger(UserController.class);
  private static MessageLogHandler messageLogger = MessageLogHandler.getLogger(UserController.class);
  private static AlarmLogHandler alarmLogger = AlarmLogHandler.getLogger(UserController.class);
  private static BusinessLogHandler businessLogger = BusinessLogHandler.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EurekaClient eurekaClient;

  @Autowired
  private DiscoveryClient discoveryClient;

  @GetMapping("/simple/{id}")
  public User findById(@PathVariable Long id) {
//    LogUtils.alarm_error(UserController.class, "LogUtils测试告警", "40000", LogUtils.getExecutingMethodName());
////    systemLogger.info("系统日志");
////    messageLogger.info("消息日志","20000");
//    alarmLogger.warn("测试告警", "40000", "findById");
//    alarmLogger.error("测试错误", "50000", "findById");
//    messageLogger.info("消息INFO","10000", "findById");
////    messageLogger.debug("消息DEBUG","10001", "findById");
//    systemLogger.info("系统INFO", "findById");
//    systemLogger.debug("系统debug", "findById");
//    systemLogger.warn("系统warn", "findById");
//    systemLogger.error("系统error", "findById");
//    businessLogger.info("业务info","200",new LogBaseModel(), "findById");
//    businessLogger.debug("业务debug","201",new LogBaseModel(), "findById");
//    businessLogger.warn("业务warn","202",new LogBaseModel(), "findById");
//    businessLogger.error("业务error","400",new LogBaseModel(), "findById");
//    LogBaseModel logVo = new LogBaseModel();
//    logVo.setSys("01");
//    logVo.setBusinessCode("001");
//    logVo.setActivityCode("01001");
//    logVo.setTransID("010010100120191127120000");

//    businessLogger.info("测试告警", "40000", logVo);

    return this.userRepository.findOne(id);

  }

  @GetMapping("/eureka-instance")
  public String serviceUrl() {
    InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
    return instance.getHomePageUrl();
  }

  @GetMapping("/instance-info")
  public ServiceInstance showInfo() {
    ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
    return localServiceInstance;
  }

  @PostMapping("/user")
  public User postUser(@RequestBody User user) {
    return user;
  }

  // 该请求不会成功
  @GetMapping("/get-user")
  public User getUser(User user) {
    return user;
  }

  @GetMapping("/list-all")
  public List<User> listAll() {
    ArrayList<User> list = Lists.newArrayList();
    User user = new User(1L, "zhangsan");
    User user2 = new User(2L, "zhangsan");
    User user3 = new User(3L, "zhangsan");
    list.add(user);
    list.add(user2);
    list.add(user3);
    return list;

  }
}
