package com.danny.cloud;/**
 * Created by danny on 2017-09-04.
 */

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * FeignClients增加 RequestInterceptor
 *
 * @author danny
 * @create 2017-09-04 15:31
 */
//@Configuration
public class FeignClientsConfiguration {
    @Bean
    public RequestInterceptor headerInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                        .getRequestAttributes();
                HttpServletRequest request = attributes.getRequest();
//                HttpServletRequest request = RequestUtils.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        Enumeration<String> values = request.getHeaders(name);
                        while (values.hasMoreElements()) {
                            String value = values.nextElement();
                            requestTemplate.header(name, value);
                        }
                    }
                }

            }
        };

    }


}
