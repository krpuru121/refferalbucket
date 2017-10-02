package com.vassarlabs.common.dsp.rdbms.cp.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import com.vassarlabs.common.dsp.cp.api.IDSConnectionPoolManager;
import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.common.dsp.rdbms.api.IJDBCConnection;
import com.vassarlabs.config.err.InvalidPropertyRequestException;
import com.vassarlabs.config.err.PropertyNotFoundException;
import com.vassarlabs.config.service.api.IConfigPropertyRepoService;
import com.vassarlabs.config.spring.AppContext;

/**
 * BoneCP connection pool manager instance
 * @author gpradeep
 *
 */
@Component
public class BoneCPConnectionPoolManager
	implements IDSConnectionPoolManager {

	protected Map<String, BoneCP> boneCPMap = null;
	
	public BoneCPConnectionPoolManager() {
		boneCPMap = new ConcurrentHashMap<String, BoneCP>();
	}

	@Override
	public boolean initConnectionPool(String dataStoreName) {

		if (boneCPMap.get(dataStoreName) != null) {
			return true;
		}

		BoneCP boneCP = null;
		try {
			boneCP = createNewConnectionPool(dataStoreName);
		} catch (ClassNotFoundException | InvalidPropertyRequestException
				| PropertyNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boneCPMap.put(dataStoreName, boneCP);
		return false;
	}

	protected BoneCP createNewConnectionPool(String dataStoreName)
		throws ClassNotFoundException, InvalidPropertyRequestException, PropertyNotFoundException, SQLException {

		IConfigPropertyRepoService configPropertyRepoService = AppContext.getApplicationContext().getBean(IConfigPropertyRepoService.class);
		
		String driverName = configPropertyRepoService.getProperty(IJDBCConnection.DB_ACCESS
				, dataStoreName, "connection.jdbc_driver");
		String dbUrl = configPropertyRepoService.getProperty(IJDBCConnection.DB_ACCESS
				, dataStoreName, "connection.db_url");
		String dbLogin = configPropertyRepoService.getProperty(IJDBCConnection.DB_ACCESS
				, dataStoreName, "connection.user_name");
		String dbPassword = configPropertyRepoService.getProperty(IJDBCConnection.DB_ACCESS
				, dataStoreName, "connection.password");

		int partitionCount = configPropertyRepoService.getIntProperty(IJDBCConnection.DB_ACCESS
				, dataStoreName, "bonecp.partitionCount", 5);
		int maxConnectionsPerPartition = configPropertyRepoService.getIntProperty(IJDBCConnection.DB_ACCESS
				, dataStoreName, "bonecp.maxConnectionsPerPartition", 10);
		int minConnectionsPerPartition = configPropertyRepoService.getIntProperty(IJDBCConnection.DB_ACCESS
				, dataStoreName, "bonecp.minConnectionsPerPartition", 10);
		int acquireIncrement = configPropertyRepoService.getIntProperty(IJDBCConnection.DB_ACCESS
				, dataStoreName, "bonecp.acquireIncrement", 10);

		Class.forName(driverName);

		BoneCPConfig config = new BoneCPConfig();
		config.setPoolName(dataStoreName+"-boneCP");
		config.setJdbcUrl(dbUrl);
		config.setUsername(dbLogin);
		config.setPassword(dbPassword);
		config.setDefaultAutoCommit(false);
		config.setPartitionCount(partitionCount);
		config.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
		config.setMinConnectionsPerPartition(minConnectionsPerPartition);
		config.setAcquireIncrement(acquireIncrement);
		
		// TODO: Log statement
		System.out.println("BoneCP configuration : [" + dataStoreName + "] config.getPartitionCount()="
				+config.getPartitionCount()
				+" : config.getMinConnectionsPerPartition()="+config.getMinConnectionsPerPartition()
				+" : config.getMaxConnectionsPerPartition()="+config.getMaxConnectionsPerPartition()
				+" : config.getAcquireIncrement()="+config.getAcquireIncrement()
				+" : config.getDefaultAutoCommit()="+config.getDefaultAutoCommit());
		
		BoneCP bcpConnectionPool = new BoneCP(config); 	// setup the connection pool

		return bcpConnectionPool;
	}

	@Override
	public Connection getNewConnection(String dataStoreName)
		throws DSPException {
		
		if (boneCPMap == null) {
			//TODO: Log error
			System.out.println("Connection pool for : " + dataStoreName + " not initialized");
			// TODO: throw appropriate error 
			throw new DSPException("Connection pool for : " + dataStoreName + " not initialized");
		}

		try {
			Connection connection = boneCPMap.get(dataStoreName).getConnection();
			return connection;
		} catch (SQLException e) {
			// TODO: Log error
			System.out.println("Error creating new connection for dataStore : " + dataStoreName + e.getMessage());
			e.printStackTrace();
			throw new DSPException("Error creating new connection for dataStore : " + dataStoreName, e);
		}
	}

	@Override
	public boolean shutdownConnectionPool() {
		if (boneCPMap == null
				|| boneCPMap.isEmpty()) {
			// TODO: Log warn message
			System.out.println("WARNING: Shutting down connection pool, but connection pool is empty");
			return true;
		}

		for (String dataStoreName : boneCPMap.keySet()) {
			boneCPMap.get(dataStoreName).shutdown();
		}
		boneCPMap.clear();
		return true;
	}
}
