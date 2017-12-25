package com.vassarlabs.cassandra.datastore.api;

import java.util.Map;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Statement;
import com.datastax.driver.mapping.Result;
import com.google.common.util.concurrent.ListenableFuture;
import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.common.dsp.nosql.api.INoSQLDataStore;

public interface ICassandraDataStore extends INoSQLDataStore{

	/**
	 * prepare statement based on sql
	 * @param sql
	 * @return
	 */
	public PreparedStatement createPreparedStatement(String sql) throws DSPException;
	
	/**
	 * executes statement with defined consistency level
	 * @param statement
	 * @return
	 */
	public ResultSet execute(Statement statement) throws DSPException;	
	
	
	public ResultSet execute(Statement statement, ConsistencyLevel consistencyLevel) throws DSPException;
	
	/**
	 * Executes query and set query parameters to values
	 * @param query
	 * @param values
	 * @return
	 * @throws DSPException
	 */
	public ResultSet execute(String query, Object... values) throws DSPException;
		
	/**
	 * executes the query with default consistency level
	 * @param query
	 * @return
	 */
	public ResultSet execute(String query) throws DSPException;
	
	/**
	 * Executes query with query parameters as map
	 * usage ::-
	 * select * from table where name = :name;
	 * create values map with ("name" , value)
	 * @param query
	 * @param values
	 * @return
	 * @throws DSPException
	 */
	public ResultSet execute(String query, Map<String, Object> values) throws DSPException;
	
	/**
	 * executes query asynchronously with given consistency level
	 * read operations :- consistency.one
	 * write operations :- consistency.quorom
	 * @param query
	 * @param consistencyLevel
	 * @return
	 */
	public ResultSet execute(String query, ConsistencyLevel consistencyLevel) throws DSPException;
	
	/**
	 * executes query asynchronously with default consistency level
	 * does not wait for query to executes
	 * return as soon as query passed
	 * return ResultSetFuture which contains information about executes query 
	 * @param query
	 * @return
	 */
	public ResultSetFuture executeAsync(String query) throws DSPException;
		
	/**
	 * executes statement asynchronously
	 * statement with defined consistency level
	 * @param statement
	 * @return
	 */
	public ResultSetFuture executeAsync(Statement statement) throws DSPException;


	public ResultSetFuture executeAsync(Statement statement , ConsistencyLevel consistencyLevel) throws DSPException;

	
	/**
	 * executes query asynchronously with given consistency level
	 * does not wait for query to executes
	 * return as soon as query passed
	 * return ResultSetFuture which contains information about executes query 
	 * @param query
	 * @return
	 */
	public ResultSetFuture executeAsync(String query, ConsistencyLevel consistencyLevel) throws DSPException;
			
	public ResultSetFuture executeAsync(String query, Map<String, Object> values) throws DSPException;

	public ResultSetFuture executeAsync(String query, Object[] values) throws DSPException;

	
	/**
	 * inserts table into cassandra table
	 * @param tableData
	 * @return
	 * @throws DSPException 
	 */
	public <T> void insert(T obj, Class<T> tableClass , ConsistencyLevel consistencyLevel) throws DSPException;

	/**
	 * insert asyncrounously
	 * @param obj
	 * @param tableData
	 * @return
	 * @throws DSPException 
	 */
	public <T> ListenableFuture<Void> insertAsync(T obj , Class<T> tableData , ConsistencyLevel consistencyLevel) throws DSPException;
	
	/**
	 * return resultset from plain query without any where clause
	 * @param query
	 * @param classToMap
	 * @return
	 * @throws DSPException
	 */
	public <T> Result<T> select(String query, Class<T> classToMap) throws DSPException;

	/**
	 * Executes statement and map the resultset to given class 
	 * @param statement
	 * @param classToMap
	 * @return
	 * @throws DSPException
	 */
	public <T> Result<T> select(Statement statement, Class<T> classToMap) throws DSPException;
	
	public <T> Result<T> select(Statement statement, ConsistencyLevel consistencyLevel, Class<T> classToMap) throws DSPException;
	
	/**
	 *returns the insert statement (cassandra.Statement object) 
	 * @param obj
	 * @param tableData
	 * @return
	 * @throws DSPException
	 */
	public <T> Statement getInsertStatement(T obj, Class<T> tableData) throws DSPException;


}
