package com.danny.cloud.web.controller;/**
 * Created by danny on 2017-09-05.
 */

import com.danny.cloud.annotation.RoutingInjected;
import com.danny.cloud.service.HelloService;
import org.springframework.stereotype.Controller;

/**
 * @author danny
 * @create 2017-09-05 17:56
 */
@Controller
public class HelloController {
    @RoutingInjected
    private HelloService helloService;

    public void sayHello(){
        this.helloService.sayHello();
    }

    public void sayHi(){
        this.helloService.sayHi();
    }


}
