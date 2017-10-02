package com.vassarlabs.cassandra.connection.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Mapper.Option;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.google.common.util.concurrent.ListenableFuture;
import com.vassarlabs.cassandra.connection.api.ICassandraConnection;
import com.vassarlabs.cassandra.connection.pool.api.ICassandraConnectionPool;
import com.vassarlabs.common.dsp.api.IDataStoreConnection;
import com.vassarlabs.common.dsp.context.DataStoreContext;
import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.common.utils.UUIDUtils;

/**
 * Provides implementation of ICassandraConnection
 * Creates a new Object everytime
 * but session is always one for entire application as defines in cassandrapool
 * @author shubham
 *
 */
@Component
@Primary
@Scope("prototype")
public class CassandraConnectionImpl implements ICassandraConnection {

	protected Session session;
	
	protected MappingManager manager;
	
	protected String dataStoreName;
	
	protected String connectionOwnerKey;
	
	@Autowired
	protected ICassandraConnectionPool cPool;

	protected Session getSession() {
		return session;
	}
	
	protected MappingManager getMappingManager() {
		
		if (session != null) {
			return manager;
		}
		return null;
	}
	
	@Override
	public boolean isActive() {
		if (session != null) {
			return true;
		}		
		return false;
	}

	@Override
	public String initializeConnection() throws DSPException {
		
		if (session != null) {
			System.out.println("Session/Connection already initialized!!");
			throw new DSPException("Session/Connection already initialized!!");
		}
		
		session = cPool.getNewSession(dataStoreName);
		manager = cPool.getMappingManager(dataStoreName);
		connectionOwnerKey = UUIDUtils.getUUID();
	
		return connectionOwnerKey;
		
	}

	@Override
	public boolean releaseConnection(String connectionOwnerKey) throws DSPException {		
		// Session does not needs to be closed at everytime
		// this method does not do anything
		// just checking that a connection is there for there to release
		
		IDataStoreConnection conn = DataStoreContext.getDataStoreConnection();
		
		if (conn == null) {
			System.out.println("Without initializing trying to release connection...");
			throw new DSPException("Without initializing trying to release connection..");
		}
		
		return true;		
	}

	@Override
	public void setDataStoreName(String dataStoreName) {
		this.dataStoreName = dataStoreName;
	}

	@Override
	public PreparedStatement createPreparedStatement(String cql) {
		return getSession().prepare(cql);
	}
	
	@Override
	public ResultSet execute(String query) {
		return getSession().execute(query);
	}
	
	@Override
	public ResultSet execute(String query , Map<String , Object> values) {
		return getSession().execute(query,values);
	
	}

	@Override
	public ResultSet execute(String query , Object[] values) {
		return getSession().execute(query,values);
	}
	
	@Override
	public ResultSet execute(String query, ConsistencyLevel consistencyLevel) {
		return getSession().execute(new SimpleStatement(query).setConsistencyLevel(consistencyLevel));	
	}
			
	@Override
	public ResultSet execute(Statement statement) {
		return getSession().execute(statement);
	}
	
	@Override
	public ResultSet execute(Statement statement , ConsistencyLevel consistencyLevel) {
		statement.setConsistencyLevel(consistencyLevel);
		return getSession().execute(statement);
	}
	
	@Override
	public ResultSetFuture executeAsync(String query) {
		return getSession().executeAsync(query);
	}

	@Override
	public ResultSetFuture executeAsync(String query , Map<String , Object> values) {
		return getSession().executeAsync(query,values);	
	}

	@Override
	public ResultSetFuture executeAsync(String query , Object[] values) {
		return getSession().executeAsync(query,values);
	}

	
	@Override
	public ResultSetFuture executeAsync(Statement statement) {
		return getSession().executeAsync(statement);
	}
	
	@Override
	public ResultSetFuture executeAsync(Statement statement , ConsistencyLevel consistencyLevel) {
		statement.setConsistencyLevel(consistencyLevel);
		return getSession().executeAsync(statement);
	}
	
	@Override
	public ResultSetFuture executeAsync(String query, ConsistencyLevel consistencyLevel) {	
		return getSession().executeAsync(new SimpleStatement(query).setConsistencyLevel(consistencyLevel));		
	}

	@Override
	public <T> void insert(T obj , Class<T> tableClass , ConsistencyLevel consistencyLevel) {
		Mapper<T> mapper = getMappingManager().mapper(tableClass);		
		Option opt = Option.consistencyLevel(consistencyLevel);
		mapper.save(obj , opt);
	} 

	@Override
	public <T> ListenableFuture<Void> insertAsync(T obj , Class<T> tableClass , ConsistencyLevel consistencyLevel) {
		Mapper<T> mapper = getMappingManager().mapper(tableClass);			
		Option opt = Option.consistencyLevel(consistencyLevel);
		return mapper.saveAsync(obj, opt);
	}
	
	@Override
	public <T> Statement getInsertStatement(T obj , Class<T> tableClass) {
		Mapper<T> mapper = getMappingManager().mapper(tableClass);		
		return mapper.saveQuery(obj);
	}
	
	@Override
	public <T> Result<T> select(String query, Class<T> classToMap) {
		ResultSet resultSet = execute(query);
		Mapper<T> mapper = getMappingManager().mapper(classToMap);		
		Result<T> results = mapper.map(resultSet);
		
		return results;
	}
	
	@Override
	public <T> Result<T> select(Statement stat, Class<T> classToMap) {
		//System.out.println(stat.getConsistencyLevel());
		ResultSet resultSet = execute(stat);		
		Mapper<T> mapper = getMappingManager().mapper(classToMap);		
		Result<T> results = mapper.map(resultSet);		
		
		
		return results;
	}
	
	@Override
	public <T> Result<T> select(Statement stat, ConsistencyLevel consistencyLevel ,  Class<T> classToMap) {
		stat.setConsistencyLevel(consistencyLevel);
		//System.out.println(stat.getConsistencyLevel());
		ResultSet resultSet = execute(stat);		
		Mapper<T> mapper = getMappingManager().mapper(classToMap);		
		Result<T> results = mapper.map(resultSet);		
				
		return results;
	}
}
