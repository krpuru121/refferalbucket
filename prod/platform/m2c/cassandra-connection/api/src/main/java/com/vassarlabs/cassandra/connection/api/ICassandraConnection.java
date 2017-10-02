package com.vassarlabs.cassandra.connection.api;

import java.util.Map;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Statement;
import com.datastax.driver.mapping.Result;
import com.google.common.util.concurrent.ListenableFuture;
import com.vassarlabs.common.dsp.api.IDataStoreConnection;

/**
 * Class contains cassandra specific query execution methods
 * @author shubham
 *
 */
public interface ICassandraConnection extends IDataStoreConnection{


	/**
	 * sets dataStore Name
	 * @param dataStoreName
	 */
	public void setDataStoreName(String dataStoreName);	
	
	/**
	 * prepare statement based on sql
	 * @param sql
	 * @return
	 */
	public PreparedStatement createPreparedStatement(String sql);
	
	/**
	 * executes statement with defined consistency level
	 * @param statement
	 * @return
	 */
	public ResultSet execute(Statement statement);	
	
	public ResultSet execute(Statement statement, ConsistencyLevel consistencyLevel);
	
	/**
	 * executes the query with default consistency level
	 * @param query
	 * @return
	 */
	public ResultSet execute(String query);
	
	/**
	 * 
	 * @param query
	 * @param values
	 * @return
	 */
	public ResultSet execute(String query, Map<String, Object> values);

	/**
	 * 
	 * @param query
	 * @param values
	 * @return
	 */
	public ResultSet execute(String query, Object[] values);
	
	/**
	 * executes query asynchronously with given consistency level
	 * read operations :- consistency.one
	 * write operations :- consistency.quorom
	 * @param query
	 * @param consistencyLevel
	 * @return
	 */
	public ResultSet execute(String query, ConsistencyLevel consistencyLevel);
	
	/**
	 * executes query asynchronously with default consistency level
	 * does not wait for query to executes
	 * return as soon as query passed
	 * return ResultSetFuture which contains information about executes query 
	 * @param query
	 * @return
	 */
	public ResultSetFuture executeAsync(String query);
		
	
	public ResultSetFuture executeAsync(String query, Map<String, Object> values);

	public ResultSetFuture executeAsync(String query, Object[] values);
	
	/**
	 * executes statement asynchnously
	 * statement with defined consistency level
	 * @param statement
	 * @return
	 */
	public ResultSetFuture executeAsync(Statement statement);

	public ResultSetFuture executeAsync(Statement statement, ConsistencyLevel consistencyLevel);
	
	/**
	 * executes query asynchronously with given consistency level
	 * does not wait for query to executes
	 * return as soon as query passed
	 * return ResultSetFuture which contains information about executes query 
	 * @param query
	 * @return
	 */
	public ResultSetFuture executeAsync(String query, ConsistencyLevel consistencyLevel);
	
	public <T> void insert(T obj, Class<T> tableClass , ConsistencyLevel consistenctLevel);

	public <T> ListenableFuture<Void> insertAsync(T obj, Class<T> tableData ,  ConsistencyLevel consistenctLevel);
	
	public <T> Result<T> select(String query, Class<T> classToMap);

	public <T> Result<T> select(Statement stat, Class<T> classToMap);
	
	public <T> Result<T> select(Statement statement, ConsistencyLevel consistencyLevel, Class<T> classToMap);
	
	public <T>  Statement getInsertStatement(T obj, Class<T> tableClass);

	
}
