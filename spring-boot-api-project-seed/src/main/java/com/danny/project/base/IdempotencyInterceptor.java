package com.danny.project.base;/**
 * Created by danny on 2018-01-09.
 */

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sun.xml.internal.ws.client.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 幂等请求
 * @author danny
 * @create 2018-01-09 18:13
 */
@Component
public class IdempotencyInterceptor implements HandlerInterceptor {


    private static Logger LOGGER = LoggerFactory.getLogger(IdempotencyInterceptor.class);
    private String[] excludePathPatterns = {};
    private String[] pathPatterns = {"/**"};
    private static final String[] RID_METHODS = {"POST","PUT","PATCH","DELETE"};
    private static final LoadingCache<String,Info> loadingCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .expireAfterAccess(3, TimeUnit.MINUTES)
            .build(new CacheLoader<String, Info>() {
                @Override
                public Info load(String key) throws Exception {
                    if(true){
                        throw new RuntimeException("请求已失效,请重新操作");
                    }
                    return null;
                }
            });

    private boolean checkMethod(HttpServletRequest httpServletRequest){
        for(String type : RID_METHODS){
            if(type.equals(httpServletRequest.getMethod())){
                return true;
            }
        }
        return false;
    }

    private Info getInfo(HttpServletRequest httpServletRequest) throws Exception{
        //只要是POST,PUT,PATCH,DELETE请求方法，请求头都必需带有token字段，获取token值通过 http://项目路径/rtoken 获取，rtoken的值只能使用一次
        String rid = httpServletRequest.getHeader("rtoken");
        if(StringUtils.isBlank(rid)){
            throw new RuntimeException("请求头rtoken不能为空");
        }
        try {
            return loadingCache.get(rid);
        }catch (Exception e){
            throw new RuntimeException(e.getCause().getMessage());
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(checkMethod(httpServletRequest)){
            ActResult actResult = new ActResult();
            actResult.setCode(5);
            Info info = null;
            try {
                info = getInfo(httpServletRequest);
            }catch (Exception e){
                actResult.setMsg(e.getMessage());
                ResponseContext.writeData(actResult.toString());
                return false;
            }
            if(null==info.getStatus()){
                info.setStatus(Info.Status.PRE);
                info.setUrl(httpServletRequest.getRequestURI());
            }else if(Info.Status.PRE.equals(info.getStatus())){
                if(!info.getUrl().equals(httpServletRequest.getRequestURI())){
                    actResult.setMsg("请求已失效,请重新操作");
                    ResponseContext.writeData(actResult.toString());
                    return false;
                }
                actResult.setMsg("请求处理中,请稍后");
                ResponseContext.writeData(actResult.toString());
                return false;
            }else if(Info.Status.AFTER.equals(info.getStatus())){
                if(!info.getUrl().equals(httpServletRequest.getRequestURI())){
                    actResult.setMsg("请求已失效,请重新操作");
                    ResponseContext.writeData(actResult.toString());
                    return false;
                }
                LOGGER.info("请求重复提交,忽略处理");
                actResult.setMsg("请不要重复提交");
                if(null!=info.getResult()){
                    actResult = (ActResult) info.getResult();
                    actResult.setCode(0);
                }
                ResponseContext.writeData(actResult);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public static LoadingCache<String,Info> getLoadingCache(){
        return loadingCache;
    }

    public static void UpdateRepeatResult(HttpServletRequest request,ActResult actResult) {
        String rtoken = request.getHeader("rtoken");
        if(StringUtils.isNotBlank(rtoken)){
            try {
                Info info = getLoadingCache().get(rtoken);
                info.setResult(actResult);
                info.setStatus(Info.Status.AFTER);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

    public String[] getExcludePathPatterns() {
        return excludePathPatterns;
    }

    public void setExcludePathPatterns(String[] excludePathPatterns) {
        this.excludePathPatterns = excludePathPatterns;
    }

    public String[] getPathPatterns() {
        return pathPatterns;
    }

    public void setPathPatterns(String[] pathPatterns) {
        this.pathPatterns = pathPatterns;
    }
}