package com.ssx.loggings.logHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssx.loggings.BaseLogger;
import com.ssx.loggings.LogLocation;

/**
 * 消息日志
 *
 * @author danny
 * @create 2019-11-27 12:21
 */

public class MessageLogHandler extends BaseLogger {
    private static Logger logger = LoggerFactory.getLogger(MessageLogHandler.class);

    public static MessageLogHandler getLogger(final Class<?> clazz) {
        return new MessageLogHandler(clazz);
    }

    private MessageLogHandler(final Class<?> clazz) {
        super(clazz);
    }

    public void info(String msgContext, String levelCode) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_INFO, levelCode, msgContext, logLocation.getLineNumber());
        logger.info(tmp);
    }

//    public void warn(String msgContext, String levelCode) {
//        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
//        String tmp = super.convMessage(LOG_LEVEL_WARNING, levelCode, msgContext, logLocation.getLineNumber());
//        logger.warn(tmp);
//    }
//
//    public void error(String msgContext, String levelCode) {
//        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
//        String tmp = super.convMessage(LOG_LEVEL_ERROR, levelCode, msgContext, logLocation.getLineNumber());
//        logger.error(tmp);
//    }

    public void debug(String msgContext, String levelCode) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_DEBUG, levelCode, msgContext, logLocation.getLineNumber());
        logger.debug(tmp);
    }

}
