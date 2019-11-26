package com.ssx.logging;

import com.alibaba.fastjson.JSON;
import com.ssx.logging.model.LogBaseModel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    // 日志级别
    private String level;
    // 操作时间
    private String startTime;

    // 主机名
    private String hostName;
    // 主机IP
    private String hostIp;
    // 类名
    private String className;
    // 方法名
    private String methodName;
    // 消息
    private String msg;

    protected String convMessage(final String p_loglevel, final String msgContext, final String lineNumber) {
        LogBaseModel logBaseModel = new LogBaseModel();
        logBaseModel.setHostName(getHostName());
        logBaseModel.setHostIp(getHostIp());
        logBaseModel.setLevel(p_loglevel);
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

    public String getDateyyyyMMddHHmmss() {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        return myFormat.format(calendar.getTime());
    }




    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}