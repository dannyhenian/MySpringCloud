package com.danny.cloud.service.impl;/**
 * Created by danny on 2017-09-05.
 */

import com.danny.cloud.service.HelloService;

/**
 * @author danny
 * @create 2017-09-05 17:51
 */

public class HelloServiceImplV2 implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("--------------Hello from V2---------------");

    }

    @Override
    public void sayHi() {
        System.out.println("--------------Hi from V2------------------");

    }
}
