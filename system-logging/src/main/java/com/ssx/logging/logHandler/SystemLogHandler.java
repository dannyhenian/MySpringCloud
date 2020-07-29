package com.ssx.logging.logHandler;

import com.ssx.logging.BaseLogger;
import com.ssx.logging.LogLocation;
import com.ssx.logging.model.LogBaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

    public void info(LogBaseModel logVo, String methodName, String msgContext) {
        String tmp = super.convMessage(LOG_LEVEL_INFO, logVo, methodName, msgContext);
        logger.info(tmp);
    }

    public void info(String msgContext, String methodName) {
        String tmp = super.convMessage(LOG_LEVEL_INFO, msgContext, methodName);
        logger.info(tmp);
    }

    public void warn(LogBaseModel logVo, String methodName, String msgContext) {
        String tmp = super.convMessage(LOG_LEVEL_WARNING, logVo, methodName, msgContext);
        logger.warn(tmp);
    }

    public void warn(String msgContext, String methodName) {
        String tmp = super.convMessage(LOG_LEVEL_WARNING, msgContext, methodName);
        logger.warn(tmp);
    }

    public void error(LogBaseModel logVo, String methodName, String msgContext) {
        String tmp = super.convMessage(LOG_LEVEL_ERROR, logVo, methodName, msgContext);
        logger.error(tmp);
    }

    public void error(String msgContext, String methodName) {
        String tmp = super.convMessage(LOG_LEVEL_ERROR, msgContext, methodName);
        logger.error(tmp);
    }

    public void debug(LogBaseModel logVo, String methodName, String msgContext) {
        String tmp = super.convMessage(LOG_LEVEL_DEBUG, logVo, methodName, msgContext);
        logger.debug(tmp);
    }

    public void debug(String msgContext, String methodName) {
        String tmp = super.convMessage(LOG_LEVEL_DEBUG, msgContext, methodName);
        logger.debug(tmp);
    }

}
