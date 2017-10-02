package com.vassarlabs.common.logging.api;

public interface IVLLogService {

	IVLLogger getLogger(String name);
	
	IVLLogger getLogger(Class<?> clazz);
	
}
