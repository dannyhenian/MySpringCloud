package com.danny.project.web;/**
 * Created by danny on 2018-01-09.
 */

import com.danny.project.base.ActResult;
import com.danny.project.base.IdempotencyInterceptor;
import com.danny.project.base.Info;
import com.danny.project.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author danny
 * @create 2018-01-09 18:21
 */

@RestController
public class IdemAct {

    @Autowired(required = false)
    private IdempotencyInterceptor idempotencyFilter;

    @GetMapping("rtoken")
    public Result idem(HttpServletRequest request, HttpServletResponse response){
        if(null==idempotencyFilter){
            ActResult actResult = new ActResult("请联系管理员开启请求幂等功能",null);
            actResult.setCode(1);
            return actResult;
        }
        String uuid = UUID.randomUUID().toString();
        IdempotencyInterceptor.getLoadingCache().put(uuid,new Info());
        return new ActResult(null,uuid);
    }

}