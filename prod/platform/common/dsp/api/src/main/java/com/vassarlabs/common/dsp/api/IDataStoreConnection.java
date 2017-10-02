package com.vassarlabs.common.dsp.api;

import com.vassarlabs.common.dsp.err.DSPException;

/**
 * Represents Data store connection instance
 * 
 * @author gpradeep
 *
 */
public interface IDataStoreConnection {

	// returns true if the Data Store connection is active
	// returns false if the Data Store connection is closed or inactive
	public boolean isActive();
	
	/**
	 * Initializes a new connection and returns the owner key
	 * 
	 * If the connection is already initialized then throws error
	 * @return
	 * @throws DSPException
	 */
	public String initializeConnection() throws DSPException;

	/**
	 * Releases a connection back to the pool, if available or closes
	 * the connection.
	 * 
	 * @return
	 * @throws DSPException
	 */
	public boolean releaseConnection(String connectionOwnerKey)
		throws DSPException;
}
