package com.danny.cloud.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.danny.cloud.utils.JsonObject;
import com.ssx.logging.logHandler.AlarmLogHandler;
import com.ssx.logging.logHandler.BusinessLogHandler;
import com.ssx.logging.logHandler.MessageLogHandler;
import com.ssx.logging.logHandler.SystemLogHandler;
import com.ssx.logging.model.LogBaseModel;
import com.ssx.logging.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

//import com.google.common.collect.Lists;
import com.danny.cloud.entity.User;
import com.danny.cloud.repository.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController extends BaseController {
//  private static SystemLogHandler systemLogger = SystemLogHandler.getLogger(UserController.class);
//  private static MessageLogHandler messageLogger = MessageLogHandler.getLogger(UserController.class);
//  private static AlarmLogHandler alarmLogger = AlarmLogHandler.getLogger(UserController.class);
//  private static BusinessLogHandler businessLogger = BusinessLogHandler.getLogger(UserController.class);
  @Value("${routing.key}")
  private String routing_key;

  public String server1 = "192.168.2.119:7900";
  public String server2 = "192.168.2.119:7901";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EurekaClient eurekaClient;

  @Autowired
  private DiscoveryClient discoveryClient;

  @GetMapping("/simple/{id}")
  public User findById(@PathVariable Long id) {
    return this.userRepository.getOne(id);

  }

  @GetMapping("/eureka-instance")
  public String serviceUrl() {
    InstanceInfo instance = this.eurekaClient.getNextServerFromEureka("MICROSERVICE-PROVIDER-USER", false);
    return instance.getHomePageUrl();
  }

  @GetMapping("/instance-info")
  public ServiceInstance showInfo() {
//    ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();

    return null;
  }

  @PostMapping("/user")
  public User postUser(@RequestBody User user) {
    return user;
  }

  @PostMapping("/getAllUser")
  public JsonObject getAllUser(@RequestParam(name="storeid") String storeid,
                               @RequestParam(name="userid") Integer userid
                               ,HttpServletRequest req, HttpServletResponse resp) {
//    JsonObject jsonObject = new JsonObject();

    JSONObject requestMsg = parseMsg(req);
    String url = routing_key.equalsIgnoreCase(server1) ? server2 : server1;

    return requestService(url,"/getUser?storeid="+storeid+"&userid="+userid, JSONObject.toJSONString(requestMsg));
//    return jsonObject;
  }

  @PostMapping("/getUser")
  public JsonObject getUser(@RequestParam(name="storeid") String storeid,
          @RequestParam(name="userid") Integer userid
          , HttpServletRequest req, HttpServletResponse resp) {
    JsonObject jsonObject = new JsonObject();
    ArrayList<User> list = new ArrayList();
    User user = new User(1L, "zhangsan");
    User user2 = new User(2L, "zhangsan");
    User user3 = new User(3L, "zhangsan");
    list.add(user);
    list.add(user2);
    list.add(user3);
    jsonObject.setResult(list);
    jsonObject.setMessage(routing_key);
    jsonObject.setState(userid);
    return jsonObject;
  }

  @PostMapping("/list-all")
  public List<User> listAll() {
    ArrayList<User> list = new ArrayList();
    User user = new User(1L, "zhangsan");
    User user2 = new User(2L, "zhangsan");
    User user3 = new User(3L, "zhangsan");
    list.add(user);
    list.add(user2);
    list.add(user3);
    return list;

  }
}
