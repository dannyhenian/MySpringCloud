package com.danny.cloud.service.impl;/**
 * Created by danny on 2017-09-05.
 */

import com.danny.cloud.service.HelloService;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author danny
 * @create 2017-09-05 17:51
 */

@Component
public class HelloServiceImplV1 implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("--------------Hello from V1---------------");
    }

    @Override
    public void sayHi() {
        System.out.println("--------------Hi from V1------------------");

    }

    public static void main(String[] args){
        HelloServiceImplV1 vo = new HelloServiceImplV1();
        Class clazz = vo.getClass();
        Field[] fields = clazz.getDeclaredFields();
    }
}
