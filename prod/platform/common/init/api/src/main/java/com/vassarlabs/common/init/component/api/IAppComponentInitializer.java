package com.vassarlabs.common.init.component.api;

public interface IAppComponentInitializer {

	public boolean initialize();
	
	/**
	 * Returns name of this Application Component
	 * 
	 * @return
	 */
	public String getName();
	
	public boolean reInitialize();
	
	public boolean shutdown();
}
