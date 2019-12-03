package com.danny.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope  //开启更新功能
public class ConfigClientController {

  @Value("${profile}")
  private String profile;

  @GetMapping("/profile")
  public String getProfile() {
    return this.profile;
  }
}
