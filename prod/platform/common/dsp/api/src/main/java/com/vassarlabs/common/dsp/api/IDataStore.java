package com.vassarlabs.common.dsp.api;

import com.vassarlabs.common.dsp.err.DSPException;

/**
 * Represents a Data store
 * 
 * @author gpradeep
 *
 */
public interface IDataStore {

	public static int RDBMS_TYPE = 0;
	public static int NOSQL_TYPE = 1;
	public static int OTHER_TYPE = 2;
	
	public int getDataStoreType();
	
	public String getDataStoreName();
	
	public void setDataStoreName(String dataStoreName);
	
	/**
	 * 
	 * Initializes Data store Connection and returns the connection owner key
	 * 
	 * @return
	 * @throws DSPException
	 */
	public String initDataConnection() throws DSPException;
	
	/**
	 * Closes the Data store connection
	 * 
	 * throws exception if owner key does not match
	 * @param connectionOwnerKey
	 * @throws DSPException
	 */
	public void releaseDataConnection(String connectionOwnerKey) throws DSPException;
}
