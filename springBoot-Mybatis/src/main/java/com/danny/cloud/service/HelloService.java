package com.danny.cloud.service;

import com.danny.cloud.annotation.RoutingSwitch;

/**
 * Created by danny on 2017-09-05.
 */
@RoutingSwitch("hello.switch")
public interface HelloService {

    @RoutingSwitch("A")
    void sayHello();

    void sayHi();
}
