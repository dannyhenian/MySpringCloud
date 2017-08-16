package com.danny.cloud.entity;

import lombok.Data;

import java.lang.annotation.Annotation;

/**
 * Created by danny on 2017-07-06.
 */
@Data(staticConstructor="aa")
public class Person {
    private String name;
    private int   age;

    public Person(String name , int age){
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Person vo = new Person("lishi",12);
        Data a = vo.getClass().getAnnotation(Data.class);
        System.out.println(a.staticConstructor());

//        Class clazz = Class.forName("com.danny.cloud.entity.User");
//        Annotation[] annotations = clazz.getAnnotations();
//        for (Annotation annotation : annotations) {
//            Data testA = (Data) annotation;
//            System.out.println("type name = "+clazz.getName() + "  |  id = " + testA.staticConstructor());
//        }

    }
}
