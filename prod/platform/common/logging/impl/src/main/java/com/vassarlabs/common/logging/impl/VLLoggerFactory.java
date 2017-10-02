package com.vassarlabs.common.logging.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vassarlabs.common.logging.api.IVLLogger;
import com.vassarlabs.common.logging.api.IVLLoggerFactory;

@Component
public class VLLoggerFactory implements IVLLoggerFactory {
	
	//TODO remove this. log4j2.xml to be added to classpath
	//{
		//DOMConfigurator.configure("logging/log4j2.xml");
	//}
	
	@Override
	public IVLLogger getLogger(String name){
		final Logger wrapped = LoggerFactory.getLogger(name);
		return new VLLoggerImpl(wrapped);
	}
	
	@Override
	public IVLLogger getLogger(Class<?> clazz){
		final Logger wrapped = LoggerFactory.getLogger(clazz);
		return new VLLoggerImpl(wrapped);
	}
	
}
