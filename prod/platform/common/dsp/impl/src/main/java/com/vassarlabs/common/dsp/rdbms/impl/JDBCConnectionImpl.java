package com.vassarlabs.common.dsp.rdbms.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vassarlabs.common.dsp.context.DataStoreContext;
import com.vassarlabs.common.dsp.cp.api.IDSConnectionPoolManager;
import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.common.utils.StringUtils;
import com.vassarlabs.common.utils.UUIDUtils;
import com.vassarlabs.common.utils.err.BugInCodeErrorObject;

@Component
@Primary
@Scope("prototype")
public class JDBCConnectionImpl
	extends AJDBCConnection {
	
	protected Connection connection;
	
	protected String dataStoreName;
	
	protected String connectionOwnerKey;
	
	@Autowired
	protected IDSConnectionPoolManager dsConnectionPoolManager;
	
	// Lazy initialization
	@Override
	public String initializeConnection()
		throws DSPException {

		try {
			if (connection != null && !connection.isClosed()) {
				// TODO: Log warning
				// Bug in code, trying to reinitialize connection
				System.out.println("Bug in code, connection is already initialized - JDBCConnectionImpl : TID=" + Thread.currentThread().getName());
				Thread.dumpStack();
				throw new DSPException(new BugInCodeErrorObject("Connection already initialized, trying to re-initialize again : TID=" + Thread.currentThread().getName()));
			}
		} catch (SQLException e) {
			// TODO: Log error, while checking if connection is closed
			// e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO: Log warning
				// Do nothing
			}
			connection = null;
		}
		
		connection = createNewConnection(dataStoreName);
		connectionOwnerKey = UUIDUtils.getUUID();
		return connectionOwnerKey;
	}

	protected Connection createNewConnection(String dataStoreName)
		throws DSPException {
		
		return dsConnectionPoolManager.getNewConnection(dataStoreName);
	}

	@Override
	protected Connection getConnection()
		throws DSPException {
		
		try {
			if (connection != null && !connection.isClosed()) {
				return connection;
			}
		} catch (SQLException se) {
			// TODO: Log the error
			throw new DSPException("Error checking connections status", se);
		}
		
		// TODO: Log warning
		// Bug in code, trying to reinitialize connection
		System.out.println("Bug in code: Trying to access connection, no connection initialized");
		throw new DSPException(new BugInCodeErrorObject("Trying to access connection, no connection initialized"));
	}

	@Override
	protected boolean closeConnection(String connectionOwnerKey)
		throws DSPException {

		// For a reused Data store context the owner key is null
		if (StringUtils.isNullOrEmpty(connectionOwnerKey)
			&& DataStoreContext.isReusableDataStoreContext()) {
			// TODO: Log error - the call should not reach here
			// , the checks should have been done earlier
			System.out.println("Bug in Code, Trying to close a re-usable data store connection");
			throw new DSPException(new BugInCodeErrorObject("Bug in Code, Trying to close a re-usable data store connection"));
		}
		
		if (StringUtils.isNullOrEmpty(connectionOwnerKey)) {
			// TODO: Log error - Owner key should not be null
			System.out.println("Bug in Code, Trying to close a data store connection with null/empty owner key");
			throw new DSPException(new BugInCodeErrorObject("Bug in Code, Trying to close a data store connection with null/empty owner key"));
		}
		
		if (this.connectionOwnerKey.equals(connectionOwnerKey)) {
			try {
				getConnection().close();
				this.connectionOwnerKey = null;
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error closing connection : TID=" + Thread.currentThread().getName() + " : Exception : " + e.getMessage());
				e.printStackTrace();
				throw new DSPException("Error closing connection : TID=" + Thread.currentThread().getName(), e);
			}
		} else {
			System.out.println("Bug in code: Trying to close connection by non-owner : TID=" + Thread.currentThread().getName());
			throw new DSPException(new BugInCodeErrorObject("Trying to close connection by non-owner : TID=" + Thread.currentThread().getName()));
		}
	}

	@Override
	protected boolean commit() throws DSPException {

		try {
			//long startTS = System.currentTimeMillis();
			getConnection().commit();
			//System.out.println("PGZZZ : Time to commit at connection level : " + (System.currentTimeMillis() - startTS));
			return true;
		} catch (SQLException e) {
			// TODO: Log error
			throw new DSPException("Error committing transaction", e);
		}
	}

	@Override
	protected boolean rollback() throws DSPException {
		try {
			getConnection().rollback();
			return true;
		} catch (SQLException e) {
			// TODO: Log error
			throw new DSPException("Error rolling back transaction", e);
		}
	}

	/**
	 * Sets the data store name
	 * TODO: This should be injected into JDBC Connection from the RDBMS store
	 * @param dataStorName
	 */
	public void setDataStoreName(String dataStoreName) {
		this.dataStoreName = dataStoreName;
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isActive() {
		try {
			if (connection != null && !connection.isClosed()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error checking connection status - exception : " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
}