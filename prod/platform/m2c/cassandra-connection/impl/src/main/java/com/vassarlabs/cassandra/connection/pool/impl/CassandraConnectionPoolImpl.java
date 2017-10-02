package com.vassarlabs.cassandra.connection.pool.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.vassarlabs.cassandra.connection.pool.api.ICassandraConnectionPool;
import com.vassarlabs.config.err.InvalidPropertyRequestException;
import com.vassarlabs.config.err.PropertyNotFoundException;
import com.vassarlabs.config.service.api.IConfigPropertyRepoService;
import com.vassarlabs.config.spring.AppContext;

@Component
public class CassandraConnectionPoolImpl implements ICassandraConnectionPool {
	
	protected Cluster cluster = null;
	
	protected Map<String , Session> cPool = new ConcurrentHashMap<String , Session>();
	
	protected Map<String , MappingManager> mapManager = new ConcurrentHashMap<String , MappingManager>();
	
	@Override
	public void initConnectionPool(String dataStoreName) {
		
		
		System.out.println("DataStore Coming::" +dataStoreName);
		if (cPool.get(dataStoreName) != null) {
			System.out.println("Already initialized pool for dataStore::" +dataStoreName);
			return;
		}
		
		Session session = createNewSession(dataStoreName);		
		cPool.put(dataStoreName, session);
		if (session != null) {
			// without this condition if session not initialized map manager will create a session
			mapManager.put(dataStoreName, new MappingManager(session));
		}
	}
	
	
	@Override
	public Session getNewSession(String dataStoreName) {
		
		if (cPool.get(dataStoreName) == null) {
			System.out.println("Session not initialized!!!=" +dataStoreName);
			// throw cassandra connection excpetion
			return null;
		}		
		Session session = cPool.get(dataStoreName);		
		return session;		
	}	
		
	@Override
	public MappingManager getMappingManager(String dataStoreName) {		
		if (cPool.get(dataStoreName) == null || mapManager.get(dataStoreName) == null) {
			System.out.println("Session not initialized!!!=" +dataStoreName);
			// throw cassandra connection excpetion
			return null;
		}		
		MappingManager manager = mapManager.get(dataStoreName);		
		
		return manager;
		 
	}
	
	private Session createNewSession(String dataStoreName) {

		try {
			if (cluster == null) {
				initializeCluster();
			}
		}
		catch (InvalidPropertyRequestException | PropertyNotFoundException e) {
			System.out.println("Error initializing cluster!!" +e);
			return null;			
		}
		
		Session session = getCluster().connect(dataStoreName);		
		return session;
	}

	private synchronized void initializeCluster() 
	throws InvalidPropertyRequestException, PropertyNotFoundException {
		
		IConfigPropertyRepoService configPropertyRepoService = AppContext.getApplicationContext().getBean(IConfigPropertyRepoService.class);
		String hosts = configPropertyRepoService.getProperty("CASS_CONFIG", "host" , "nodes");		
		addHostToCluster(hosts.split(","));
		
	}
	

	@SuppressWarnings("deprecation")
	private boolean addHostToCluster(String[] hostIp) 
	throws InvalidPropertyRequestException, PropertyNotFoundException {
				
		IConfigPropertyRepoService configPropertyRepoService = AppContext.getApplicationContext().getBean(IConfigPropertyRepoService.class);
		
		PoolingOptions poolOptions = new PoolingOptions();
		
		int localCoreConnections = Integer.parseInt(configPropertyRepoService.getProperty("CASS_CONFIG", "host", "local_core_connections" ,"4"));
		int localMaxConnections = Integer.parseInt(configPropertyRepoService.getProperty("CASS_CONFIG", "host" , "local_max_connections" ,"4"));
		int remoteMaxConnections = Integer.parseInt(configPropertyRepoService.getProperty("CASS_CONFIG", "host" , "remote_max_connections" ,"4"));
		int remoteCoreConnections = Integer.parseInt(configPropertyRepoService.getProperty("CASS_CONFIG", "host" , "remote_core_connections" ,"4"));
		int localMaxReqPerConnections = Integer.parseInt(configPropertyRepoService.getProperty("CASS_CONFIG", "host" , "local_max_req_per_connection" ,"4"));
		int remoteMaxReqPerConnections = Integer.parseInt(configPropertyRepoService.getProperty("CASS_CONFIG", "host" , "remote_max_req_per_connection" ,"4"));
		String user = configPropertyRepoService.getProperty("CASS_CONFIG", "host" , "user_name");
		String password = configPropertyRepoService.getProperty("CASS_CONFIG", "host" , "password");

		System.out.println("Building cluster with propertes::= "
				+ " localCoreCOnnections:: " + localCoreConnections 
				+"localMaxConnections:: " + localMaxConnections 
				+"remoteMaxConnections:: " + remoteMaxConnections); 
		
		cluster = Cluster.builder()
					.addContactPoints(hostIp)
					.withProtocolVersion(ProtocolVersion.V3)
					.withCredentials(user, password)
					.withPoolingOptions(poolOptions
					.setConnectionsPerHost(HostDistance.LOCAL, localCoreConnections , localMaxConnections)
					.setConnectionsPerHost(HostDistance.REMOTE,remoteCoreConnections , remoteMaxConnections)
					.setMaxRequestsPerConnection(HostDistance.LOCAL, localMaxReqPerConnections)
					.setMaxRequestsPerConnection(HostDistance.REMOTE, remoteMaxReqPerConnections)
					.setHeartbeatIntervalSeconds(0)
					.setMaxQueueSize(256*4)
					.setPoolTimeoutMillis(30000))
					.build();

		return true;
	}

	private Cluster getCluster() {
		return cluster;
	}

	@Override
	public void shutConnectionPool() {
	
		for (String dataStore : cPool.keySet()) {
			cPool.get(dataStore).close();
		}
		cPool.clear();		
		mapManager.clear();
	}
}
