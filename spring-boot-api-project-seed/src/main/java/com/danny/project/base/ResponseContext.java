package com.danny.project.base;/**
 * Created by danny on 2018-01-09.
 */

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author danny
 * @create 2018-01-09 18:09
 */

public final class ResponseContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseContext.class);

    private ResponseContext(){}

    public static HttpServletResponse get(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    private static HttpServletResponse init(){
        HttpServletResponse response = get();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        return response;
    }

    public static void writeData(String data){
        try {
            init().getWriter().print(data);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void writeData(Object data){
        try {
            init().getWriter().print(JSON.toJSON(data));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void writeData(HttpServletResponse response,Object data){
        try {
            init().getWriter().print(JSON.toJSON(data));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void writeData(HttpServletResponse response,String data){
        try {
            init().getWriter().print(data);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
