package com.vassarlabs.common.logging.api;

public interface IVLLoggerFactory {

	IVLLogger getLogger(String name);
	
	IVLLogger getLogger(Class<?> clazz);
	
}
