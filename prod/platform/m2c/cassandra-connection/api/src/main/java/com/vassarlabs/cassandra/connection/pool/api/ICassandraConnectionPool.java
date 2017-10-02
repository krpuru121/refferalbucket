package com.vassarlabs.cassandra.connection.pool.api;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

public interface ICassandraConnectionPool {

	public void initConnectionPool(String dataStoreName);
	public void shutConnectionPool();
	
	public Session getNewSession(String dataStoreName);	
	public MappingManager getMappingManager(String dataStoreName);
	
	
}
