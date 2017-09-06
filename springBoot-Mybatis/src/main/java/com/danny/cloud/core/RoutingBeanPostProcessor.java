package com.danny.cloud.core;

import com.danny.cloud.annotation.RoutingInjected;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;
import java.util.Map;

/**
 *
 *
 * @author danny
 * @create 2017-09-05 11:28
 */

public class RoutingBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private ApplicationContext applicationContext;

    //bean初始化方法调用前执行
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    //bean初始化方法调用后执行
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields){
            if(f.isAnnotationPresent(RoutingInjected.class)){
                if(!f.getType().isInterface()){
                    throw new BeanCreationException("RoutingInjected field must be declared as an interface: "
                            + f.getName() + " @clazz " + f.getClass());
                }

                try {
                    this.handleRoutingInjected(f, bean, f.getType());
                } catch (IllegalAccessException e) {
                    throw new BeanCreationException("Exception throw when handleRoutingInjected: " + e);
                }
            }
        }

        return bean;
    }

    private void handleRoutingInjected(Field field, Object bean, Class type) throws IllegalAccessException{
        Map<String, Object> candidates = this.applicationContext.getBeansOfType(type);
        field.setAccessible(true);
        if(candidates.size() == 1){
            field.set(bean,candidates.values().iterator().next());
        } else if (candidates.size() == 2){
            Object proxy = RoutingBeanProxyFactory.createProxy(type,candidates);
            field.set(bean, proxy);
        } else {
            throw new IllegalArgumentException("Find more than 2 beans for type: " + type);
        }
    }


}
