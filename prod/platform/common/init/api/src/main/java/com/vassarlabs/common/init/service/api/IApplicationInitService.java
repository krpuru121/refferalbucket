package com.vassarlabs.common.init.service.api;

import com.vassarlabs.common.init.err.AppInitializationException;

public interface IApplicationInitService {
	
	public boolean initialize()
		throws AppInitializationException;
	
	public boolean reInitialize()
		throws AppInitializationException;
	
	public boolean shutdown()
		throws AppInitializationException;
}
