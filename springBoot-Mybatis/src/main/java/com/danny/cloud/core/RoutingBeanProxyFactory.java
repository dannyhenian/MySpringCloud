package com.danny.cloud.core;/**
 * Created by danny on 2017-09-05.
 */

import com.danny.cloud.annotation.RoutingInjected;
import com.danny.cloud.annotation.RoutingSwitch;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StringUtils;


import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author danny
 * @create 2017-09-05 11:49
 */

public class RoutingBeanProxyFactory {

    public static Object createProxy(Class targetClass, Map<String, Object> beans) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(targetClass);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(targetClass, beans));
        return proxyFactory.getProxy();
    }

    static class VersionRoutingMethodInterceptor implements MethodInterceptor {
        private String classSwitch;  //hello.switch
        private Object beanofSwitchOn; //HelloServiceImplV2
        private Object beanofSwitchOff; //HelloServiceImplV1

        public VersionRoutingMethodInterceptor(Class targetClass, Map<String, Object> beans) {
            String interfaceName = StringUtils.uncapitalize(targetClass.getSimpleName());   //首字母小写
            if(targetClass.isAnnotationPresent(RoutingSwitch.class)) {
                this.classSwitch = ((RoutingSwitch) targetClass.getAnnotation(RoutingSwitch.class)).value();
            }

            this.beanofSwitchOn = beans.get(this.buildBeanName(interfaceName,true));
            this.beanofSwitchOff = beans.get(this.buildBeanName(interfaceName,false));

        }

        private String buildBeanName(String interfaceName, boolean isSwitchOn) {
            String beanName = interfaceName + "Impl" +(isSwitchOn ? "V2" : "V1");
            System.out.println("================"+beanName+"===============");
            return beanName;
        }

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            Method method = invocation.getMethod();
            String switchName = this.classSwitch;
            if (method.isAnnotationPresent(RoutingSwitch.class)) {
                switchName = method.getAnnotation(RoutingSwitch.class).value();
            }
            if(StringUtils.isEmpty(switchName)) {
                throw new IllegalStateException("RoutingSwitch's value is blank, method: " + method.getName());
            }

            return invocation.getMethod().invoke(getTargetBean(switchName),invocation.getArguments());
        }

        public Object getTargetBean(String switchName) {
            boolean switchOn;
            if(RoutingVersion.A.equals(switchName)) {
                switchOn = false;
            } else if (RoutingVersion.B.equals(switchName)) {
                switchOn = true;
            } else {
                switchOn = FunctionSwitch.isSwitchOpened(switchName);
            }
            return switchOn ? beanofSwitchOn : beanofSwitchOff;
        }
    }



}
