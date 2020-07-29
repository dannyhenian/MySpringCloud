package com.ssx.logging.utils;

import com.ssx.logging.logHandler.AlarmLogHandler;
import com.ssx.logging.logHandler.BusinessLogHandler;
import com.ssx.logging.logHandler.MessageLogHandler;
import com.ssx.logging.logHandler.SystemLogHandler;
import com.ssx.logging.model.LogBaseModel;

/**
 * 日志统一工具
 *
 * @author danny
 * @create 2019-11-28 12:21
 */

public class LogUtils {
    public static AlarmLogHandler alarmLog;
    public static BusinessLogHandler busLog;
    public static MessageLogHandler msgLog;
    public static SystemLogHandler sysLog;

    /* 告警类*/
    public static void alarm_warn(Class<?> clazz, String msgContext, String levelCode, String methodName) {
        alarmLog = AlarmLogHandler.getLogger(clazz);
        alarmLog.warn(msgContext, levelCode, methodName);
    }

    public static void alarm_warn(Class<?> clazz, String msgContext, String levelCode, LogBaseModel logVo, String methodName) {
        alarmLog = AlarmLogHandler.getLogger(clazz);
        alarmLog.warn(msgContext, levelCode, logVo, methodName);
    }

    public static void alarm_error(Class<?> clazz, String msgContext, String levelCode, String methodName) {
        alarmLog = AlarmLogHandler.getLogger(clazz);
        alarmLog.error(msgContext, levelCode, methodName);
    }

    public static void alarm_error(Class<?> clazz, String msgContext, String levelCode, LogBaseModel logVo, String methodName) {
        alarmLog = AlarmLogHandler.getLogger(clazz);
        alarmLog.error(msgContext, levelCode, logVo, methodName);
    }

    /* 业务类*/
    public static void bus_info(Class<?> clazz, String msgContext, String levelCode, LogBaseModel logVo, String methodName) {
        busLog = BusinessLogHandler.getLogger(clazz);
        busLog.info(msgContext, levelCode, logVo, methodName);
    }

    public static void bus_warn(Class<?> clazz, String msgContext, String levelCode, LogBaseModel logVo, String methodName) {
        busLog = BusinessLogHandler.getLogger(clazz);
        busLog.warn(msgContext, levelCode, logVo, methodName);
    }

    public static void bus_error(Class<?> clazz, String msgContext, String levelCode, LogBaseModel logVo, String methodName) {
        busLog = BusinessLogHandler.getLogger(clazz);
        busLog.error(msgContext, levelCode, logVo, methodName);
    }

//    public static void bus_debug(Class<?> clazz, String msgContext, String levelCode, LogBaseModel logVo, String methodName) {
//        busLog = BusinessLogHandler.getLogger(clazz);
//        busLog.debug(msgContext, levelCode, logVo, methodName);
//    }


    /* 消息类*/
    public static void msg_info(Class<?> clazz, String msgContext, String levelCode, String methodName) {
        msgLog = MessageLogHandler.getLogger(clazz);
        msgLog.info( msgContext, levelCode, methodName);
    }

    public static void msg_info(Class<?> clazz, String msgContext, String levelCode, LogBaseModel logVo, String methodName) {
        msgLog = MessageLogHandler.getLogger(clazz);
        msgLog.info(msgContext, levelCode, logVo, methodName);
    }


    /* 系统类*/
    public static void sys_info(Class<?> clazz,  LogBaseModel logVo, String methodName, String msgContext) {
        sysLog = SystemLogHandler.getLogger(clazz);
        sysLog.info(logVo, methodName, msgContext);
    }

    public static void sys_info(Class<?> clazz, String msgContext, String methodName) {
        sysLog = SystemLogHandler.getLogger(clazz);
        sysLog.info(msgContext, methodName);
    }

    public static void sys_warn(Class<?> clazz,  LogBaseModel logVo, String methodName, String msgContext) {
        sysLog = SystemLogHandler.getLogger(clazz);
        sysLog.warn(logVo, methodName, msgContext);
    }

    public static void sys_warn(Class<?> clazz, String msgContext, String methodName) {
        sysLog = SystemLogHandler.getLogger(clazz);
        sysLog.warn(msgContext, methodName);
    }

    public static void sys_error(Class<?> clazz,  LogBaseModel logVo, String methodName, String msgContext) {
        sysLog = SystemLogHandler.getLogger(clazz);
        sysLog.error(logVo, methodName, msgContext);
    }

    public static void sys_error(Class<?> clazz, String msgContext, String methodName) {
        sysLog = SystemLogHandler.getLogger(clazz);
        sysLog.error(msgContext, methodName);
    }

    public static void sys_debug(Class<?> clazz,  LogBaseModel logVo, String methodName, String msgContext) {
        sysLog = SystemLogHandler.getLogger(clazz);
        sysLog.debug(logVo, methodName, msgContext);
    }

    public static void sys_debug(Class<?> clazz, String msgContext, String methodName) {
        sysLog = SystemLogHandler.getLogger(clazz);
        sysLog.debug(msgContext, methodName);
    }



//    public static String getExecutingMethodName() {
//        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//        StackTraceElement e = stackTrace[2];
//        return e.getMethodName();
//    }





}
