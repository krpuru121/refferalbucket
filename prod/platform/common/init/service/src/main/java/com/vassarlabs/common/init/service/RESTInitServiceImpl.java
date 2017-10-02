package com.vassarlabs.common.init.service;

import org.springframework.stereotype.Component;

import com.vassarlabs.common.init.err.AppInitializationException;

@Component
public class RESTInitServiceImpl extends ApplicationInitServiceImpl{
	
	/*** TODO
	 * make a separate boncp config file for this class because on frnt end number of connections will be less
	 */
	@Override
	public boolean initialize()
		throws AppInitializationException {
		 
		return super.initialize();
		
	}
}
