package com.ssx.logging;

import com.alibaba.fastjson.JSON;
import com.ssx.logging.model.LogBaseModel;
import com.ssx.logging.utils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 * 日志基类
 *
 * @author danny
 * @create 2019-11-26 14:47
 */

public class BaseLogger {
    protected BaseLogger(Class<?> clazz){
        this.initSystemParams(clazz);
    }

    // 日志级别
    public static final String LOG_LEVEL_DEBUG = "DEBUG";
    public static final String LOG_LEVEL_INFO = "INFO";
    public static final String LOG_LEVEL_WARNING = "WARN";
    public static final String LOG_LEVEL_ERROR = "ERROR";

    /******* 系统信息 *******/
    // 服务实例名
    private String service;
    // 主机名
    private String hostName;
    // 主机IP
    private String hostIp;
    // 类名
    private String className;
    // 方法名
    private String methodName;

    protected String convMessage(final String p_loglevel, final String msgContext, final String lineNumber) {
        LogBaseModel logBaseModel = new LogBaseModel();
        logBaseModel.setService(getService());
        logBaseModel.setHostName(getHostName());
        logBaseModel.setHostIp(getHostIp() + "_" + getLocalPort());
        logBaseModel.setLevel(p_loglevel);
        logBaseModel.setStartTime(getDateyyyyMMddHHmmss());
        logBaseModel.setMsg(msgContext);
        logBaseModel.setClassName(getClassName() + "["+lineNumber+"]");

        return JSON.toJSON(logBaseModel).toString().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }

    protected String convMessage(final String p_loglevel,final String levelCode, final String msgContext, final String lineNumber) {
        LogBaseModel logBaseModel = new LogBaseModel();
        logBaseModel.setService(getService());
        logBaseModel.setHostName(getHostName());
        logBaseModel.setHostIp(getHostIp() + "_" + getLocalPort());
        logBaseModel.setLevel(p_loglevel);
        logBaseModel.setLevel(levelCode);
        logBaseModel.setStartTime(getDateyyyyMMddHHmmss());
        logBaseModel.setMsg(msgContext);
        logBaseModel.setClassName(getClassName() + "["+lineNumber+"]");

        return JSON.toJSON(logBaseModel).toString().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }

    protected String convMessage(final String p_loglevel,final String levelCode,final LogBaseModel logVo, final String msgContext, final String lineNumber) {
        LogBaseModel logBaseModel = new LogBaseModel();
        BeanUtils.copyProperties(logVo,logBaseModel);
        logBaseModel.setService(getService());
        logBaseModel.setHostName(getHostName());
        logBaseModel.setHostIp(getHostIp() + "_" + getLocalPort());
        logBaseModel.setLevel(p_loglevel);
        logBaseModel.setLevel(levelCode);
        logBaseModel.setStartTime(getDateyyyyMMddHHmmss());
        logBaseModel.setMsg(msgContext);
        logBaseModel.setClassName(getClassName() + "["+lineNumber+"]");

        return JSON.toJSON(logBaseModel).toString().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }


    /**
     * 日志系统属性初始化
     * @param clazz
     */
    protected void initSystemParams(final Class<?> clazz){

        //设置包名+类名
        this.setClassName(clazz.getName());
        //设置服务实例名
        this.setService(getApplicationName());

        //设置主机IP和主机名
        try {
            String hostIp = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            if(null == hostIp){
                this.setHostIp("1.0.0.0");
            }else{
                this.setHostIp(hostIp);
            }
            if(null == hostName){
                this.setHostName("hostName");
            }else{
                this.setHostName(hostName);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String getApplicationName() {
        YamlPropertiesFactoryBean yamlMapFactoryBean = new YamlPropertiesFactoryBean();
        yamlMapFactoryBean.setResources(new ClassPathResource("bootstrap.yml"));
        Properties properties = yamlMapFactoryBean.getObject();
//        String name = properties.getProperty("spring.cloud.config.name");
//        String profile = properties.getProperty("spring.cloud.config.profile");
        String applicationName;
        applicationName = properties.getProperty("spring.application.name");

//        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(profile)) {
//            yamlMapFactoryBean.setResources(new ClassPathResource(name + "-" + profile + ".yml"));
//            applicationName = properties.getProperty("spring.application.name");
//        } else{
//            applicationName = name;
//        }
        return applicationName;
    }

    public String getDateyyyyMMddHHmmss() {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        return myFormat.format(calendar.getTime());
    }

    public String getLocalPort() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getServerPort() + "";
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}