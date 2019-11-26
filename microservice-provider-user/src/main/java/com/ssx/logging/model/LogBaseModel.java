package com.ssx.logging.model;

/**
 * 日志输出基类
 *
 * @author danny
 * @create 2019-11-26 18:00
 */

public class LogBaseModel {
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
