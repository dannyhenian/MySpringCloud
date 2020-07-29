package com.ssx.logging.logHandler;

import com.ssx.logging.BaseLogger;
import com.ssx.logging.model.LogBaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 告警日志
 *
 * @author danny
 * @create 2019-11-27 12:21
 */

public class AlarmLogHandler extends BaseLogger {
	private static Logger logger = LoggerFactory.getLogger(AlarmLogHandler.class);

	public static AlarmLogHandler getLogger(final Class<?> clazz) {
		return new AlarmLogHandler(clazz);
	}

	private AlarmLogHandler(final Class<?> clazz) {
		super(clazz);
	}

	public void warn(String msgContext, String levelCode, String methodName) {
		String tmp = super.convMessage(LOG_LEVEL_WARNING, levelCode, msgContext, methodName);
		logger.warn(tmp);
	}

	public void warn(String msgContext, String levelCode, LogBaseModel logVo, String methodName) {
		String tmp = super.convMessage(LOG_LEVEL_WARNING, levelCode, logVo, msgContext, methodName);
		logger.warn(tmp);
	}

	public void error(String msgContext, String levelCode, String methodName) {
        String tmp = super.convMessage(LOG_LEVEL_ERROR, levelCode, msgContext, methodName);
        logger.error(tmp);
    }

	public void error(String msgContext, String levelCode, LogBaseModel logVo, String methodName) {
		String tmp = super.convMessage(LOG_LEVEL_ERROR, levelCode, logVo, msgContext, methodName);
		logger.error(tmp);
	}

}
