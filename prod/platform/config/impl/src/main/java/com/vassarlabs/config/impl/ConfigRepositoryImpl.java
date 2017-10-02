package com.vassarlabs.config.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.vassarlabs.common.utils.StringUtils;
import com.vassarlabs.config.api.IConfigGroupHead;
import com.vassarlabs.config.api.IConfigGroupInstance;
import com.vassarlabs.config.api.IConfigRepository;
import com.vassarlabs.config.err.InvalidPropertyRequestException;

@Component
public class ConfigRepositoryImpl
	implements IConfigRepository {

	protected Map<String, IConfigGroupHead> allConfigGroup;
	
	protected static Map<String, Map<String, String>> CONFIG_PROPERTIES;
	
	// TODO: Should be remove later
	static {
		CONFIG_PROPERTIES = new HashMap<String, Map<String,String>>();
		
		Map<String, String> dbAccess = new HashMap<String, String>();
		dbAccess.put("platform_data", "rdbms/platform_data.properties");
		dbAccess.put("transaction_data", "rdbms/transaction_data.properties");
		dbAccess.put("business_data", "rdbms/business_data.properties");
		dbAccess.put("large_object", "rdbms/large_object.properties");
		dbAccess.put("apsea_data", "rdbms/apsea_data.properties");
		CONFIG_PROPERTIES.put("DB_ACCESS", dbAccess);
		
		Map<String, String> zKAccess = new HashMap<String, String>();
		zKAccess.put("default_zk", "zookeeper/default_zk.properties");
		CONFIG_PROPERTIES.put("ZK_CONFIG", zKAccess);
		
		Map<String, String> stormAccess = new HashMap<String, String>();
		stormAccess.put("default_storm", "storm/default_storm.properties");
		CONFIG_PROPERTIES.put("STORM_CONFIG", stormAccess);
		
		Map<String, String> kafkaAccess = new HashMap<String, String>();
		kafkaAccess.put("default_kafka", "kafka/default_kafka.properties");
		CONFIG_PROPERTIES.put("KAFKA_CONFIG", kafkaAccess);
		
		Map<String, String> cacheConfig = new HashMap<String, String>();
		cacheConfig.put("default_cache", "cache/cache.properties");
		CONFIG_PROPERTIES.put("CACHE_CONFIG", cacheConfig);
		
		Map<String, String> emailConfig = new HashMap<String, String>();
		cacheConfig.put("default_email", "email/email.properties");
		CONFIG_PROPERTIES.put("EMAIL_CONFIG", emailConfig);
		
		Map<String, String> systemConfig = new HashMap<String, String>();
		systemConfig.put("default_system", "system/default_system.properties");
		CONFIG_PROPERTIES.put("SYSTEM_CONFIG", systemConfig);
		
		Map<String , String> nodeAddress = new HashMap<String , String>();		
		nodeAddress.put("host", "cassandra/host.properties");		
		CONFIG_PROPERTIES.put("CASS_CONFIG", nodeAddress);
	}
	
	public ConfigRepositoryImpl() {
		this.allConfigGroup = new HashMap<String, IConfigGroupHead>();
	}
	
	public ConfigRepositoryImpl(Map<String, IConfigGroupHead> allConfigGroup) {
		this.allConfigGroup = allConfigGroup;
	}
	
	@Override
	public IConfigGroupInstance getConfigGroupInstance(String configGroupHeadId, String configGroupInstanceId) throws InvalidPropertyRequestException {
		
		IConfigGroupHead configGroupHead = allConfigGroup.get(configGroupHeadId);
		if (configGroupHead == null) {
			configGroupHead = new ConfigGroupHeadImpl(configGroupHeadId);
			allConfigGroup.put(configGroupHeadId, configGroupHead);
		}

		// TODO: Should be auto-loaded later
		IConfigGroupInstance configGroupInstance = configGroupHead.getConfigGroupInstance(configGroupInstanceId);
		if (configGroupInstance == null) {
			configGroupInstance = loadConfigGroupInstance(configGroupHeadId, configGroupInstanceId);
			configGroupHead.setConfigGroupInstance(configGroupInstanceId, configGroupInstance);
		}

		return configGroupHead.getConfigGroupInstance(configGroupInstanceId);
	}

	@Override
	public void setConfigGroupInstance(String configGroupHeadId,
			IConfigGroupInstance configGroupInstance) {

		IConfigGroupHead configGroupHead = allConfigGroup.get(configGroupHeadId);
		if (configGroupHead == null) {
			configGroupHead = new ConfigGroupHeadImpl(configGroupHeadId);
			allConfigGroup.put(configGroupHeadId, configGroupHead);
		}
		configGroupHead.setConfigGroupInstance(configGroupInstance.getConfigGroupInstanceId(), configGroupInstance);
	}
	
	/**
	 * Load configuration from property file
	 *
	 * TODO: temporary method, configuration should not be loaded directly here
	 * @param configGroupHeadId
	 * @param configGroupInstanceId
	 * @return
	 * @throws InvalidPropertyRequestException
	 */
	protected IConfigGroupInstance loadConfigGroupInstance(String configGroupHeadId, String configGroupInstanceId)
		throws InvalidPropertyRequestException {
	
		Map<String, String> configHeadValues = CONFIG_PROPERTIES.get(configGroupHeadId);
		if (configHeadValues == null) {
			// TODO: Log value here
			throw new InvalidPropertyRequestException("No config properties head found for input head : " + configGroupHeadId + " : instance id : " + configGroupInstanceId);
		}
		
		String propFileName = configHeadValues.get(configGroupInstanceId);
		if (StringUtils.isNullOrEmpty(propFileName)) {
			// TODO: Log value here
			throw new InvalidPropertyRequestException("No config property file found for input head : " + configGroupHeadId + " : instance id : " + configGroupInstanceId);
		}

		IConfigGroupInstance configGroupInstance = null;
		InputStream inputStream = null;
		try {
			inputStream = this.getClass().getResourceAsStream("/" + propFileName);
			Properties properties = new Properties();
			properties.load(inputStream);
			
			configGroupInstance = new ConfigGroupInstanceImpl(configGroupInstanceId, properties);
			return configGroupInstance;
		} catch (FileNotFoundException fe) {
			// TODO: Log value here
			throw new InvalidPropertyRequestException("Property file : " + propFileName + " : not found for input head : " + configGroupHeadId + " : instance id : " + configGroupInstanceId, fe);
		} catch (IOException ioe) {
			// TODO: Log value here
			throw new InvalidPropertyRequestException("Error reading Property file : " + propFileName + " : for input head : " + configGroupHeadId + " : instance id : " + configGroupInstanceId, ioe);
		}
	}
	
	
	@Override
	public String toString() {
		return "DefaultConfigRepository [allConfigGroup=" + allConfigGroup
				+ "]";
	}
}
