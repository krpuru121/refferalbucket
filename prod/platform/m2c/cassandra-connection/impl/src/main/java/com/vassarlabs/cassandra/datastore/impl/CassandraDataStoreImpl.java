package com.vassarlabs.cassandra.datastore.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Statement;
import com.datastax.driver.mapping.Result;
import com.google.common.util.concurrent.ListenableFuture;
import com.vassarlabs.cassandra.connection.api.ICassandraConnection;
import com.vassarlabs.cassandra.datastore.api.ICassandraDataStore;
import com.vassarlabs.common.dsp.api.IDataStore;
import com.vassarlabs.common.dsp.context.DataStoreContext;
import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.config.spring.AppContext;

@Component
public class CassandraDataStoreImpl implements ICassandraDataStore {

	protected String dataStoreName;
	
	private Map<String , PreparedStatement> pSCachedMap = null;
	
	@Override
	public int getDataStoreType() {
		return IDataStore.NOSQL_TYPE;
	}

	@Override
	public String getDataStoreName() {
		return dataStoreName;
	}

	@Override
	public void setDataStoreName(String dataStoreName) {
		this.dataStoreName = dataStoreName;
	}

	@Override
	public String initDataConnection() throws DSPException {
		return initCassandraConnection(getDataStoreName());
	}

	@Override
	public void releaseDataConnection(String connectionOwnerKey) throws DSPException {
		getCassandraConnection().releaseConnection(connectionOwnerKey);
	}

	
	private ICassandraConnection getCassandraConnection() throws DSPException {
		
		ICassandraConnection conn = (ICassandraConnection) DataStoreContext.getDataStoreConnection();
		
		if (conn == null) {
			System.out.println("Connection not initialized!!");
			throw new DSPException("Error while getting cassandra connection , Connection not initialized");
		}
		
		return conn;
	}

	private String initCassandraConnection(String dataStoreName) throws DSPException {
		
		ICassandraConnection cassConnnection = AppContext.getApplicationContext().getBean(ICassandraConnection.class);
		cassConnnection.setDataStoreName(dataStoreName);
		String connectionOwnerKey = cassConnnection.initializeConnection();
		DataStoreContext.setDataStoreConnection(cassConnnection);
		
		return connectionOwnerKey;		
	}

	@Override
	public PreparedStatement createPreparedStatement(String sql) throws DSPException {
		
		if (pSCachedMap == null) {
			pSCachedMap = new ConcurrentHashMap<>();
		}
		
		if (pSCachedMap.get(sql) != null) return pSCachedMap.get(sql);
		else {
			PreparedStatement ps = getCassandraConnection().createPreparedStatement(sql);
			pSCachedMap.put(sql, ps);
			return ps;
		}		
	}

	@Override
	public ResultSet execute(Statement statement) throws DSPException {
		return getCassandraConnection().execute(statement);
	}
	
	@Override
	public ResultSet execute(Statement statement , ConsistencyLevel consistencyLevel) throws DSPException {
		return getCassandraConnection().execute(statement , consistencyLevel);
	}
	
	@Override
	public ResultSet execute(String query , Object... values) throws DSPException {
		return getCassandraConnection().execute(query , values);
	}
	
	@Override
	public ResultSet execute(String query) throws DSPException {
		return getCassandraConnection().execute(query);
	}

	@Override
	public ResultSet execute(String query , Map<String , Object> values) throws DSPException {
		return getCassandraConnection().execute(query , values);
	}
	
	@Override
	public ResultSet execute(String query, ConsistencyLevel consistencyLevel) throws DSPException {
		return getCassandraConnection().execute(query , consistencyLevel);
	}

	@Override
	public ResultSetFuture executeAsync(String query) throws DSPException {
		return getCassandraConnection().executeAsync(query); 
	}
	
	@Override
	public ResultSetFuture executeAsync(String query , Map<String , Object> values) throws DSPException {
		return getCassandraConnection().executeAsync(query , values);
	}
	
	@Override
	public ResultSetFuture executeAsync(String query , Object[] values) throws DSPException {
		return getCassandraConnection().executeAsync(query , values);
	}
	
	@Override
	public ResultSetFuture executeAsync(Statement statement) throws DSPException {
		return getCassandraConnection().executeAsync(statement); 
	}

	@Override
	public ResultSetFuture executeAsync(Statement statement , ConsistencyLevel consistencyLevel) throws DSPException {
		return getCassandraConnection().executeAsync(statement , consistencyLevel); 
	}
	
	@Override
	public ResultSetFuture executeAsync(String query, ConsistencyLevel consistencyLevel) throws DSPException {
		return getCassandraConnection().executeAsync(query , consistencyLevel); 
	}

	@Override
	public <T> void insert(T obj , Class<T> tableData , ConsistencyLevel consistencyLevel) throws DSPException {
		getCassandraConnection().insert(obj , tableData , consistencyLevel);
	}

	@Override
	public <T> ListenableFuture<Void> insertAsync(T obj , Class<T> tableData , ConsistencyLevel consistencyLevel) throws DSPException {
		return getCassandraConnection().insertAsync(obj , tableData , consistencyLevel);
	}
	
	@Override
	public <T> Statement getInsertStatement(T obj , Class<T> tableData) throws DSPException {
		return getCassandraConnection().getInsertStatement(obj , tableData);
	}
	
	@Override
	public <T> Result<T> select(String query, Class<T> classToMap) throws DSPException {
		return getCassandraConnection().select(query , classToMap);
	}	
	
	@Override
	public <T> Result<T> select(Statement statement, Class<T> classToMap) throws DSPException {
		return getCassandraConnection().select(statement , classToMap);
	}	
	
	@Override
	public <T> Result<T> select(Statement statement , ConsistencyLevel consistencyLevel ,  Class<T> classToMap) throws DSPException {
		return getCassandraConnection().select(statement , consistencyLevel , classToMap);
	}
		
}
