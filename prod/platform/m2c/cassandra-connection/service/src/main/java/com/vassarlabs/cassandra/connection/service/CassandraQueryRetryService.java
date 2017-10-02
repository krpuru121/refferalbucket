package com.vassarlabs.cassandra.connection.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.exceptions.FrameTooLongException;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.OperationTimedOutException;
import com.datastax.driver.core.exceptions.WriteTimeoutException;
import com.vassarlabs.cassandra.connection.service.api.ICassandraQueryRetryService;
import com.vassarlabs.cassandra.datastore.api.ICassandraDataStore;
import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.common.logging.api.IVLLogService;
import com.vassarlabs.common.logging.api.IVLLogger;

@Component
public class CassandraQueryRetryService
	implements ICassandraQueryRetryService {

	@Autowired
	protected IVLLogService logFactory;
	
	protected IVLLogger logger;
	
	@PostConstruct
	public void init() {
		logger = logFactory.getLogger(getClass());
	}

	@Override
	public ResultSetFuture executeQueryAsync(ICassandraDataStore cassDataStore, BoundStatement bs, PreparedStatement ps, ConsistencyLevel consistencyLevel, String callerMsg)
		throws FrameTooLongException, DSPException {

		return (ResultSetFuture)executeQueryInternal(cassDataStore, bs, ps, consistencyLevel, callerMsg, false);
	}
	
	@Override
	public ResultSet executeQuery(ICassandraDataStore cassDataStore, BoundStatement bs, PreparedStatement ps, ConsistencyLevel consistencyLevel, String callerMsg)
		throws FrameTooLongException, DSPException {
		return (ResultSet)executeQueryInternal(cassDataStore, bs, ps, consistencyLevel, callerMsg, true);
	}
	
	protected Object executeQueryInternal(ICassandraDataStore cassDataStore, BoundStatement bs, PreparedStatement ps, ConsistencyLevel consistencyLevel, String callerMsg, boolean syncCall)
		throws FrameTooLongException, DSPException {
		
		int hostNotFoundRetries = 0;
		int opTimeOutRetries = 0;
		int frameTooLongRetries = 0;
		while (true) {
			
			try {
				if (syncCall) {
					ResultSet rs = cassDataStore.execute(bs, consistencyLevel);
					return rs;
				} else {
					ResultSetFuture rs = cassDataStore.executeAsync(bs, consistencyLevel);
					return rs;
				}
			} catch (NoHostAvailableException nhe) {
				++hostNotFoundRetries;

				if (hostNotFoundRetries > MAX_RETRIES) {
					logger.error("In CassandraQueryRetryService)() - " + callerMsg 
							+ " - query = " + ps.getQueryString() 
							+ " - no. of host not found retries : " + hostNotFoundRetries + " - max no. of retries " + MAX_RETRIES + " reached returning call", nhe);
					throw new DSPException("In CassandraQueryRetryService)() - " + callerMsg + " - query = " + ps.getQueryString() + " - no. of host not found retries : " + hostNotFoundRetries + " max no. of retries " + MAX_RETRIES + " reached returning call", nhe);
				}

				logger.error("In CassandraQueryRetryService)() - " + callerMsg 
						+ " - query = " + ps.getQueryString() + " -- Host Not Found Exception --- no. of retries : " 
						+ hostNotFoundRetries + " of " + MAX_RETRIES + " -- sleeeing for ms befor retry : " + ERROR_SLEEP_TIME);
			} catch (OperationTimedOutException | WriteTimeoutException ote) {
				++opTimeOutRetries;

				if (opTimeOutRetries > MAX_RETRIES) {
					logger.error("In CassandraQueryRetryService)() - " + callerMsg 
							+ " - query = " + ps.getQueryString() 
							+ " - no. of op time out retries : " + opTimeOutRetries + " - max no. of retries " + MAX_RETRIES + " reached returning call", ote);
					throw new DSPException("In CassandraQueryRetryService)() - " + callerMsg + " - query = " + ps.getQueryString() + " - no. of op time out retries : " + opTimeOutRetries + " max no. of retries " + MAX_RETRIES + " reached returning call", ote);
				}

				logger.error("In CassandraQueryRetryService)() - " + callerMsg 
						+ " - query = " + ps.getQueryString() + " -- Operation Timed Out Exception --- no. of retries : " 
						+ opTimeOutRetries + " of " + MAX_RETRIES + " -- sleeeing for ms befor retry : " + ERROR_SLEEP_TIME);
			} catch (FrameTooLongException fe) {
				++frameTooLongRetries;

				if (frameTooLongRetries > MAX_FRAME_TOO_LONG_RETRIES) {
					logger.error("In CassandraQueryRetryService)() - " + callerMsg 
							+ " - query = " + ps.getQueryString() 
							+ " - no. of frame too long retries : " + frameTooLongRetries + " - max no. of retries " + MAX_RETRIES + " reached returning call", fe);
					throw fe;
				}

				logger.error("In CassandraQueryRetryService)() - " + callerMsg 
						+ " - query = " + ps.getQueryString() + " -- Frame Too Long Exception --- no. of retries : " 
						+ frameTooLongRetries + " of " + MAX_FRAME_TOO_LONG_RETRIES + " -- sleeeping for ms befor retry : " + ERROR_SLEEP_TIME);
			}

			try {
				Thread.sleep(ERROR_SLEEP_TIME);
			} catch (InterruptedException ie) {
				// Do Nothing
			}
		}
	}
}
