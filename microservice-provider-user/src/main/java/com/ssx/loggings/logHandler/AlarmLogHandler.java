package com.ssx.loggings.logHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssx.loggings.LogLocation;

public class AlarmLogHandler extends com.ssx.loggings.BaseLogger {
	private static Logger logger = LoggerFactory.getLogger(AlarmLogHandler.class);

	public static AlarmLogHandler getLogger(final Class<?> clazz) {
		return new AlarmLogHandler(clazz);
	}

	private AlarmLogHandler(final Class<?> clazz) {
		super(clazz);
	}

	public void warn(String msgContext, String levelCode) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
		String tmp = super.convMessage(LOG_LEVEL_WARNING, levelCode, msgContext, logLocation.getLineNumber());
		logger.warn(tmp);
	}

	public void error(String msgContext, String levelCode) {
        LogLocation logLocation = new LogLocation(new Throwable(), getClassName());
        String tmp = super.convMessage(LOG_LEVEL_ERROR, levelCode, msgContext, logLocation.getLineNumber());
        logger.error(tmp);
    }

}
