package com.vassarlabs.common.init.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.vassarlabs.cassandra.connection.pool.api.ICassandraConnectionPool;
import com.vassarlabs.common.dsp.cp.api.IDSConnectionPoolManager;
import com.vassarlabs.common.init.component.api.IAppComponentInitializer;

@Component
@Qualifier("ds_connection_pool")
public class DSConnectionPoolInitializer
	implements IAppComponentInitializer {

	protected final static String MY_NAME = "DS_CONNECTION_POOL_MANAGER";
	
	protected String[] cassDataStoreList = new String[] { "high_edu_data" };
		
	@Autowired
	protected IDSConnectionPoolManager dsConnectionPoolManager;

	@Autowired
	protected ICassandraConnectionPool cassPoolManager;
	
	@Override
	public boolean initialize() {
		/*
		for (String dataStoreName:dataStoreList) {
			dsConnectionPoolManager.initConnectionPool(dataStoreName);
		}*/
				
		// Cassandra pool initialization
		for (String dataStoreName:cassDataStoreList) {
			cassPoolManager.initConnectionPool(dataStoreName);
		}
		
		return true;
	}

	@Override
	public boolean reInitialize() {
		if (!shutdown()) {
			return false;
		}
		return initialize();
	}

	@Override
	public boolean shutdown() {
		return dsConnectionPoolManager.shutdownConnectionPool();
	}

	@Override
	public String getName() {
		return MY_NAME;
	}
}
