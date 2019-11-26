package com.ssx.logging.alarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssx.logging.BaseLogger;
import com.ssx.logging.LogLocation;

public class AlarmLogHandler extends BaseLogger {
	private static Logger logger = LoggerFactory.getLogger(AlarmLogHandler.class);

	public static AlarmLogHandler getLogger(final Class<?> clazz) {
		return new AlarmLogHandler(clazz);
	}

	private AlarmLogHandler(final Class<?> clazz) {
		super(clazz);
	}

	public void warn(String msgContext) {
		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
		String tmp = super.convMessage(LOG_LEVEL_WARNING, msgContext, logLocation.getLineNumber());
		logger.warn(tmp);
	}
	
//	public void error(String msgContext) {
//		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
//		String tmp = super.convMessage(LOG_LEVEL_ERROR, msgContext, logLocation.getLineNumber());
//		if(needReplace){
//			logger.error(tmp,params);
//		}else{
//			logger.error(tmp);
//		}
//	}
//
//	public void error(String msgContext, Throwable t) {
//		LogLocation logLocation = new LogLocation(new Throwable(), getClassname());
//		String tmp = super.convMessage(LOG_LEVEL_ERROR, msgContext, logLocation.getLineNumber());
//		if (t == null) {
//			t = new Throwable();
//		}
//		if(needReplace){
//			logger.error(tmp+t,params);
//		}else{
//			logger.error(tmp,t);
//		}
//	}
}
