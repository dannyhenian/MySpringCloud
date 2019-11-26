package com.ssx.logging;

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
    private String classname;
    // 线程名
    private String threadName;
    // 消息
    private String msg;

    /**
     * 格式化日志
     * @param p_loglevel
     * @param msgContext
     * @param lineNumber
     * @return
     */
    protected String convMessage(final String p_loglevel, final String msgContext, final String lineNumber) {

        StringBuilder sb_tmp = new StringBuilder();
        // ...........固定数据项部分
        sb_tmp.append("BL#");
        sb_tmp.append("#");
        //日志级别
        sb_tmp.append(p_loglevel);
        sb_tmp.append("#");
        //系统时间
        sb_tmp.append(getDateyyyyMMddHHmmss());
        sb_tmp.append("#");
        //主机名称
        sb_tmp.append(getHostName());
        sb_tmp.append("#");
        //包名.类名[行数]
        sb_tmp.append(getClassname()).append("[").append(lineNumber).append("]");
        sb_tmp.append("#");
        sb_tmp.append(msgContext);
        sb_tmp.append("#");
        sb_tmp.append("#LB");

        return sb_tmp.toString().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }

    /**
     * 日志系统属性初始化
     * @param clazz
     */
    protected void initSystemParams(final Class<?> clazz){
        //设置包名+类名
        this.setClassname(clazz.getName());

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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}