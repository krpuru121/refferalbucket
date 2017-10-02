package com.vassarlabs.common.dsp.rdbms.impl;

import org.springframework.stereotype.Component;

import com.vassarlabs.common.dsp.context.DataStoreContext;
import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.common.dsp.rdbms.api.IJDBCConnection;
import com.vassarlabs.common.utils.err.BugInCodeErrorObject;
import com.vassarlabs.config.spring.AppContext;

@Component
public class RDBMSDataStoreImpl 
	extends ARDBMSDataStore {

	public RDBMSDataStoreImpl() {
		super();
	}
	
	/**
	 * 
	 * Initializes JDBC Connection and returns the connection owner key
	 * 
	 * @return
	 * @throws DSPException
	 */
	public String initDataConnection() throws DSPException {
		return initJDBCConnection(getDataStoreName());
	}
	
	protected String initJDBCConnection(String dataSourceName)
		throws DSPException {
		
		// JDBCConnection	|| IsReUsableDataStoreContext	|| Action
		// NOT-NULL			|| TRUE							|| Bug in Code, the connection is already initialized
		// NOT-NULL			|| FALSE						|| Bug in Code, the connection is already initialized
		// NULL				|| TRUE							|| Something Wrong, the connection cannot be null
		// NULL				|| FALSE						|| Create new connection
		
		IJDBCConnection jdbcConnection = (IJDBCConnection)DataStoreContext.getDataStoreConnection();
		boolean isReusableDataStore = DataStoreContext.isReusableDataStoreContext();

		if (jdbcConnection != null && jdbcConnection.isActive()) {
			if (isReusableDataStore) {
				// TODO: log error
				System.out.println("Bug in code, re-usable connection is already initialized - RDBMSDataStore : " + dataStoreName);
				Thread.dumpStack();
				throw new DSPException(new BugInCodeErrorObject("Re-usable connection already initialized, trying to re-initialize again : " + dataStoreName));
			} else {
				// TODO: log error
				System.out.println("Bug in code, connection is already initialized - RDBMSDataStore : " + dataStoreName);
				Thread.dumpStack();
				throw new DSPException(new BugInCodeErrorObject("Connection already initialized, trying to re-initialize again : " + dataStoreName));
			}
		}
		
		// Connection should have already been initialized, the call should not be coming here
		if (jdbcConnection == null && DataStoreContext.isReusableDataStoreContext()) {
			// TODO: log error
			System.out.println("Bug in code, re-usable connection is not initialized, invalid call - RDBMSDataStore : " + dataStoreName);
			Thread.dumpStack();
			throw new DSPException(new BugInCodeErrorObject("Re-usable connection is not initialized, invalid call : " + dataStoreName));
		}
		
		//TODO: What happens when jdbcConnection is not NULL, but is closed or inactive
		if ((jdbcConnection != null && !jdbcConnection.isActive())
				|| (jdbcConnection == null && !DataStoreContext.isReusableDataStoreContext())) {
			jdbcConnection = (IJDBCConnection)AppContext.getApplicationContext().getBean(IJDBCConnection.class);
			jdbcConnection.setDataStoreName(dataSourceName);

			String connectionOwnerKey = jdbcConnection.initializeConnection();
			DataStoreContext.setDataStoreConnection(jdbcConnection);
			
			return connectionOwnerKey;
		}

		// TODO: Log error: Should not be here
		System.out.println("Bug in code, error initializing datastore - RDBMSDataStore : " + dataStoreName);
		Thread.dumpStack();
		throw new DSPException(new BugInCodeErrorObject("Error initializing datastore - RDBMSDataStore : " + dataStoreName));
	}
	
	@Override
	protected IJDBCConnection getJDBCConnection()
		throws DSPException {
		
		IJDBCConnection jdbcConnection = (IJDBCConnection)DataStoreContext.getDataStoreConnection();
		if (jdbcConnection != null) {
			return jdbcConnection;
		}

		// TODO: Log warning
		// Bug in code, trying to reinitialize connection
		System.out.println("Bug in code: Trying to get non-initialized connection - " + dataStoreName);
		throw new DSPException(new BugInCodeErrorObject("Trying to get uninitialized connection - " + dataStoreName));
	}
	
	/**
	 * Closes the Data store connection
	 * 
	 * throws exception if owner key does not match
	 * @param connectionOwnerKey
	 * @throws DSPException
	 */
	public void releaseDataConnection(String connectionOwnerKey) throws DSPException {
		getJDBCConnection().releaseConnection(connectionOwnerKey);
	}
}
