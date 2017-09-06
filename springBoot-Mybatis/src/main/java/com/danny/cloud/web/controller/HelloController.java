package com.danny.cloud.web.controller;/**
 * Created by danny on 2017-09-05.
 */

import com.danny.cloud.annotation.RoutingInjected;
import com.danny.cloud.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author danny
 * @create 2017-09-05 17:56
 */
@RestController
public class HelloController {
    @RoutingInjected
    private HelloService helloService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void sayHello(){
        this.helloService.sayHello();
    }

    public void sayHi(){
        this.helloService.sayHi();
    }


}
