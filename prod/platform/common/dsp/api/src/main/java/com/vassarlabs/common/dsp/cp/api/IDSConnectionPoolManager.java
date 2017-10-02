package com.vassarlabs.common.dsp.cp.api;

import java.sql.Connection;

import com.vassarlabs.common.dsp.err.DSPException;

/**
 * DataStore connection pool
 * 
 * @author gpradeep
 *
 */
public interface IDSConnectionPoolManager {

	public boolean initConnectionPool(String dataStoreName);
	
	public Connection getNewConnection(String dataStoreName)
		throws DSPException;
	
	public boolean shutdownConnectionPool();
}
