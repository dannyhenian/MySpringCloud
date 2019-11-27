package com.ssx.logging.logHandler;

import com.ssx.logging.BaseLogger;
import com.ssx.logging.LogLocation;
import com.ssx.logging.model.LogBaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务日志
 *
 * @author danny
 * @create 2019-11-27 12:40
 */
public class BusinessLogHandler extends BaseLogger {
    private static Logger logger = LoggerFactory.getLogger(BusinessLogHandler.class);

    public static BusinessLogHandler getLogger(final Class<?> clazz) {
        return new BusinessLogHandler(clazz);
    }

    private BusinessLogHandler(final Class<?> clazz) {
        super(clazz);
    }

    public void info(String msgContext, String levelCode, LogBaseModel logVo) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_INFO, levelCode, logVo, msgContext, logLocation.getLineNumber());
        logger.warn(tmp);
    }

    public void warn(String msgContext, String levelCode, LogBaseModel logVo) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_WARNING, levelCode, logVo, msgContext, logLocation.getLineNumber());
        logger.warn(tmp);
    }

    public void error(String msgContext, String levelCode, LogBaseModel logVo) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_ERROR, levelCode, logVo, msgContext, logLocation.getLineNumber());
        logger.warn(tmp);
    }

    public void debug(String msgContext, String levelCode, LogBaseModel logVo) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_DEBUG, levelCode, logVo, msgContext, logLocation.getLineNumber());
        logger.warn(tmp);
    }
}
