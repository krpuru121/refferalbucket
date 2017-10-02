package com.vassarlabs.cassandra.connection.service.api;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.vassarlabs.cassandra.datastore.api.ICassandraDataStore;
import com.vassarlabs.common.dsp.err.DSPException;

public interface ICassandraQueryRetryService {

	public static int MAX_RETRIES = 20;
	public static int MAX_FRAME_TOO_LONG_RETRIES = 5;
	public static long ERROR_SLEEP_TIME = 30000; // 30 secs

	/**
	 * Executes input prepared statement with retry logic - sync call
	 * 
	 * @param cassDataStore
	 * @param bs
	 * @param ps
	 * @param callerMsg
	 * @return
	 * @throws DSPException
	 */
	public ResultSet executeQuery(ICassandraDataStore cassDataStore, BoundStatement bs, PreparedStatement ps
			, ConsistencyLevel consistencyLevel, String callerMsg) throws DSPException;

	/**
	 * Executes input prepared statement with retry logic - async call
	 * 
	 * @param cassDataStore
	 * @param bs
	 * @param ps
	 * @param consistencyLevel
	 * @param callerMsg
	 * @return
	 * @throws DSPException
	 */
	public ResultSetFuture executeQueryAsync(ICassandraDataStore cassDataStore, BoundStatement bs, PreparedStatement ps,
			ConsistencyLevel consistencyLevel, String callerMsg) throws DSPException;
}
