package com.vassarlabs.common.dsp.context;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.vassarlabs.common.dsp.api.IDataStore;
import com.vassarlabs.common.dsp.api.IDataStoreConnection;
import com.vassarlabs.common.dsp.err.DSPException;
import com.vassarlabs.common.logging.api.IVLLogService;
import com.vassarlabs.common.logging.api.IVLLogger;
import com.vassarlabs.common.utils.StringUtils;
import com.vassarlabs.common.utils.err.BugInCodeErrorObject;
import com.vassarlabs.common.utils.err.ObjectNotFoundException;

/**
 * ThreadLocal object which provides access to the the Data Store
 * 
 * @author gpradeep
 *
 */
public class DataStoreContext {

	protected static IVLLogger logger = null;
	
	@Autowired
	public synchronized void setLogger(IVLLogService logService) {
		if (logger == null) {
			logger = logService.getLogger(DataStoreContext.class);
		}
	}
	
	// Object Keys
	public static String DATASTORE = "DATASTORE";
	public static String DATASTORE_CONNECTION = "DATASTORE_CONNECTION";
	public static String CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
	public static String TRANSACTION_IN_PROGRESS = "TRANSACTION_IN_PROGRESS";
	public static String REUSABLE_DATASTORE = "REUSABLE_DATASTORE";
	
	private static String DATASTORE_OWNER_KEY = "DATASTORE_OWNER_KEY";
	private static String IS_DATASTORE_INITIALIZED = "IS_DATASTORE_INITIALIZED";
	private static String USER_SESSION_ID = "USER_SESSION_ID";

//	private static String dataStoreOwnerKey = null;
//	private static boolean isDataStoreInitialized = false;

	private static final ThreadLocal<HashMap<String, Object>> dataStoreThreadLocal = new ThreadLocal<HashMap<String,Object>>() {
		
		@Override
		public HashMap<String, Object> initialValue() {
			HashMap<String, Object> map = new HashMap<String, Object>();
			return map;
		}
	};

	public static IDataStore getDataStore() {
		return (IDataStore)get(DATASTORE);
	}

	public static String initReusableDataStoreContext(IDataStore dataStore)
		throws DSPException {
		
		if (isDataStoreInitialized()) {
			logger.debug("Re-usable datastore is already initialized for datastore : " + dataStore.getDataStoreName());
			return null;
		}

		initDataStoreContext(dataStore);
		setReusableDataStoreContext(true);
		return getDataStoreOwnerKey();
	}
	
	public static String initDataStoreContext(IDataStore dataStore)
		throws DSPException {
		
		// isDataStoreInitialized	||	isReusableDataStoreContext	||	Result
		//	TRUE					||	TRUE						||  Do Nothing, return null as owner key	
		//	TRUE					||	FALSE						||	Bug in code, someone trying to re-initialize 
		//	FALSE					||	TRUE						||	Bug in code, data store must have been initialized
		//	FALSE					||	FALSE						||	New request, create new context and return owner key

		if (isDataStoreInitialized() && isReusableDataStoreContext()) {
			logger.debug("Data store is initialized : " + dataStore.getDataStoreName());
			return null;
		}

		if (isDataStoreInitialized() && !isReusableDataStoreContext()) {
			// TODO: Log error
			throw new DSPException(new BugInCodeErrorObject("Bug in code, Data store already initialized for : " + dataStore.getDataStoreName()));
		}

		if (!isDataStoreInitialized() && isReusableDataStoreContext()) {
			// TODO: Log error
			throw new DSPException(new BugInCodeErrorObject("Bug in code, Data store was not initialized appropriately for : " + dataStore.getDataStoreName() + " - resuable data store was not initialized"));
		}

     		String dataStoreOwnerKey = dataStore.initDataConnection();
		set(DATASTORE, dataStore);
		setCurrentTS();
		setDataStoreInitialized(true);
		setDataStoreOwnerKey(dataStoreOwnerKey);
		return dataStoreOwnerKey;
	}

	private static void setDataStoreInitialized(boolean isDataStoreInitialized) {
		set(IS_DATASTORE_INITIALIZED, isDataStoreInitialized);
	}
	
	public static void setUserSessionID(long userSessionID) {
		set(USER_SESSION_ID, userSessionID);
	}

	private static boolean isDataStoreInitialized() {
		Boolean isDataStoreInitialized = (Boolean)get(IS_DATASTORE_INITIALIZED);
		if (isDataStoreInitialized == null) {
			return false;
		}
		return isDataStoreInitialized;
	}
	
	private static void setDataStoreOwnerKey(String dataStoreOwnerKey) {
		set(DATASTORE_OWNER_KEY, dataStoreOwnerKey);
	}
	
	private static String getDataStoreOwnerKey() {
		return (String)get(DATASTORE_OWNER_KEY);
	}
	
	public static long getUserSessionID() {
		return (long)get(USER_SESSION_ID);
	}
	
	protected static void setReusableDataStoreContext(boolean isReusableDataStoreContext) {
		set(REUSABLE_DATASTORE, isReusableDataStoreContext);
	}
	
	public static boolean isReusableDataStoreContext() {
		Boolean isReusableDataStoreContext = (Boolean)get(REUSABLE_DATASTORE);
		if (isReusableDataStoreContext == null) {
			return false;
		}
		return isReusableDataStoreContext;
	}
	
	public static void setDataStoreConnection(IDataStoreConnection dataStoreConnection) {
		set(DATASTORE_CONNECTION, dataStoreConnection);
	}
	
	public static IDataStoreConnection getDataStoreConnection() {
		if (contains(DATASTORE_CONNECTION)) {
			return (IDataStoreConnection)get(DATASTORE_CONNECTION);
		}
		return null;
	}
	
	public static void setCurrentTS() {
		set(CURRENT_TIMESTAMP, System.currentTimeMillis());
	}
	
	public static void setCurrentTS(long currentTS) {
		set(CURRENT_TIMESTAMP, currentTS);
	}
	
	public static long getCurrentTS() {
		if (contains(CURRENT_TIMESTAMP)) {
			return (Long)get(CURRENT_TIMESTAMP);
		};
		setCurrentTS();
		return (Long)get(CURRENT_TIMESTAMP);
	}
	
	public static void setTransactionInProgress() {
		set(TRANSACTION_IN_PROGRESS, true);
	}
	
	public static void clearTransactionInProgress() {
		set(TRANSACTION_IN_PROGRESS, false);
		remove(TRANSACTION_IN_PROGRESS);
	}

	public static boolean isTransactionInProgress() {
		
		Boolean transactionInProgress = (Boolean)get(TRANSACTION_IN_PROGRESS);
		if (transactionInProgress == null) {
			return false;
		}
		return transactionInProgress;
	}

	
	
	public static void clearDataStoreContext(String inDataStoreOwnerKey)
		throws DSPException {

		if (!isDataStoreInitialized()) {
			// TODO: Log error
			//logger.error("Bug in Code, error clearing data store context, Data store not initialized");
			throw new DSPException(new BugInCodeErrorObject("Error clearing data store context, Data store not initialized"));
		}
		
		if (StringUtils.isNullOrEmpty(inDataStoreOwnerKey) && isReusableDataStoreContext()) {
			// TODO: Log debug
			//logger.debug("Trying to clear re-usable data store context by non-owner");
			return;
		}
		
		if (StringUtils.isNullOrEmpty(inDataStoreOwnerKey)) {
			// TODO: Error
			//logger.error("Error clearing data store context - owner key is null/empty (it is possible the data store is being re-used without setting the re-used flag)");
			throw new DSPException("Error clearing data store context - owner key is null/empty (it is possible the data store is being re-used without setting the re-used flag)");
		}
		
		String dataStoreOwnerKey = getDataStoreOwnerKey();
		if (!inDataStoreOwnerKey.equals(dataStoreOwnerKey)) {
			// TODO: Error
			//logger.error("Error clearing data store context - owner key does not match : " + dataStoreOwnerKey + ":input owner key : " + inDataStoreOwnerKey);
			throw new DSPException("Error clearing data store context - owner key does not match : " + dataStoreOwnerKey + ":input owner key : " + inDataStoreOwnerKey);
		}

		getDataStoreConnection().releaseConnection(dataStoreOwnerKey);
		setDataStoreInitialized(false); // Redundant code
		setDataStoreOwnerKey(null); // Redundant code
		setUserSessionID(-1);
		dataStoreThreadLocal.get().clear();
	}

	/***************************************************************************
	 * 
	 * Utility methods to access the ThreadLocal HashMap
	 * 
	 **************************************************************************/
	public static void set(String name, Object value) {
		dataStoreThreadLocal.get().put(name, value);
	}
	
	public static Object get(String name) {
		return dataStoreThreadLocal.get().get(name);
	}
	
	protected static Object getObject(String name)
		throws ObjectNotFoundException {
			
		Object obj = get(name);
		if (obj == null) {
			// TODO: Log Error
			throw new ObjectNotFoundException(new BugInCodeErrorObject("Bug in code, [" + name + "] object is not set in DataStoreContext local"));
		}
		return obj;
	}
 
	protected static boolean contains(String name) {
		if (get(name) == null) {
			return false;
		} else {
			return true;
		}
	}

	public static Object remove(String name) {
		return dataStoreThreadLocal.get().remove(name);
	}
	
	public static void destroy() {
		dataStoreThreadLocal.get().clear();
	}
}