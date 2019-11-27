package com.ssx.logging.logHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssx.logging.BaseLogger;
import com.ssx.logging.LogLocation;

/**
 * 系统日志
 *
 * @author danny
 * @create 2019-11-27 12:21
 */

public class SystemLogHandler extends BaseLogger {
    private static Logger logger = LoggerFactory.getLogger(SystemLogHandler.class);

    public static SystemLogHandler getLogger(final Class<?> clazz) {
        return new SystemLogHandler(clazz);
    }

    private SystemLogHandler(final Class<?> clazz) {
        super(clazz);
    }

    public void info(String msgContext) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_INFO, msgContext, logLocation.getLineNumber());
        logger.info(tmp);
    }

    public void warn(String msgContext) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_WARNING, msgContext, logLocation.getLineNumber());
        logger.warn(tmp);
    }

    public void error(String msgContext) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_ERROR, msgContext, logLocation.getLineNumber());
        logger.error(tmp);
    }

    public void debug(String msgContext) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_DEBUG, msgContext, logLocation.getLineNumber());
        logger.debug(tmp);
    }

}
