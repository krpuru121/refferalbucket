package com.vassarlabs.config.impl;

import java.util.Properties;

import org.springframework.stereotype.Component;

import com.vassarlabs.common.utils.StringUtils;
import com.vassarlabs.config.api.IConfigGroupInstance;

@Component
public class ConfigGroupInstanceImpl
	implements IConfigGroupInstance {

	protected String configGroupInstanceId;
	protected Properties configProperties;

	public ConfigGroupInstanceImpl() {
		// Do Nothing
	}

	public ConfigGroupInstanceImpl(String configGroupInstanceId) {
		this.configGroupInstanceId = configGroupInstanceId;
	}
	
	public ConfigGroupInstanceImpl(String configGroupInstanceId, Properties configProperties) {
		this.configGroupInstanceId = configGroupInstanceId;
		this.configProperties = configProperties;
	}
	
	@Override
	public String getConfigGroupInstanceId() {
		return this.configGroupInstanceId;
	}

	@Override
	public void setConfigGroupInstanceId(String configGroupInstanceId) {
		this.configGroupInstanceId = configGroupInstanceId;
	}

	@Override
	public Properties getConfigProperties() {
		return this.configProperties;
	}

	@Override
	public void setConfigProperties(Properties configProperties) {
		this.configProperties = configProperties;
	}

	@Override
	public void addOrUpdateConfigProperty(String property, String value) {
		this.configProperties.put(property, value);		
	}

	@Override
	public void removeConfigProperty(String property) {
		this.configProperties.remove(property);
	}

	@Override
	public boolean checkConfigPropertyExists(String property) {
		return this.configProperties.containsKey(property);
	}

	@Override
	public void setConfigProperty(String property, String value) {
		addOrUpdateConfigProperty(property, value);
	}

	@Override
	public String getConfigProperty(String property) {
		return this.configProperties.getProperty(property);
	}

	@Override
	public String getConfigProperty(String property, String defaultValue) {
		String value = getConfigProperty(property);
		if (StringUtils.isNullOrEmpty(value)) {
			value = defaultValue;
		}
		return value;
	}

	@Override
	public String toString() {
		return "ConfigGroupInstanceImpl [configGroupInstanceId=" + configGroupInstanceId
				+ ", configProperties=" + configProperties + "]";
	}
}
