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
 * BeanPostProcessor 可以修改实例对象
 * BeanFactoryPostProcessor 可以修改bean的配置信息而BeanPostProcessor不能，且调用比BeanPostProcessor早
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
//            f.setAccessible(true);   //值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
//            f.get(bean);    //返回指定对象上此 Field 表示的字段的值
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
        Map<String, Object> candidates = this.applicationContext.getBeansOfType(type); //获取到的子类必须注册到context中
        System.out.println("=============="+candidates.size()+"==================");
        for(String key : candidates.keySet()){
            System.out.println("================="+key+"---> "+ candidates.get(key));
        }
        field.setAccessible(true); //值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查
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
