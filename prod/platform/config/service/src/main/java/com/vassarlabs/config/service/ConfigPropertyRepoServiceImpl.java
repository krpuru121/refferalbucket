package com.vassarlabs.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vassarlabs.config.api.IConfigGroupInstance;
import com.vassarlabs.config.api.IConfigRepository;
import com.vassarlabs.config.err.InvalidPropertyRequestException;
import com.vassarlabs.config.err.InvalidPropertyValueException;
import com.vassarlabs.config.err.PropertyNotFoundException;
import com.vassarlabs.config.service.api.IConfigPropertyRepoService;

@Component
public class ConfigPropertyRepoServiceImpl
	implements IConfigPropertyRepoService {

	@Autowired
	protected IConfigRepository configRepository;
	
	@Override
	public IConfigGroupInstance getConfigPropertyInstance(String configGroupHeadId,
			String configGroupInstanceId) throws InvalidPropertyRequestException {
		
		return configRepository.getConfigGroupInstance(configGroupHeadId, configGroupInstanceId);
	}

	@Override
	public String getProperty(String configGroupId, String configGroupInstanceId,
			String propertyKey) throws PropertyNotFoundException, InvalidPropertyRequestException {

		IConfigGroupInstance configGroupInstance = getConfigPropertyInstance(configGroupId, configGroupInstanceId);
		if (configGroupInstance == null) {
			// TODO: Log warning message here
			throw new PropertyNotFoundException("Property not found for : " + configGroupId + " : " + configGroupInstanceId + " : " + propertyKey);
		}
		
		return configGroupInstance.getConfigProperty(propertyKey);
	}

	@Override
	public String getProperty(String configGroupId, String configGroupInstanceId,
			String propertyKey, String defaultValue) {
		
		String propertyValue = defaultValue;
		try {
			propertyValue = getProperty(configGroupId, configGroupInstanceId, propertyKey);
		} catch (PropertyNotFoundException pnfe) {
			// Log Warning and return default value;
			System.out.println("Property not found for : " + configGroupId + ":" + configGroupInstanceId + ":" + propertyKey);
		} catch (InvalidPropertyRequestException e) {
			// Log Warning and return default value;
			System.out.println("Invalid property requested : " + configGroupId + ":" + configGroupInstanceId + ":" + propertyKey);
		}
		return propertyValue;
	}

	@Override
	public int getIntProperty(String configGroupId,
			String configGroupInstanceId, String propertyKey)
			throws InvalidPropertyValueException,
			InvalidPropertyRequestException, PropertyNotFoundException {
		
		String propValueStr = getProperty(configGroupId, configGroupInstanceId, propertyKey);
		
		try {
			int propValue = Integer.parseInt(propValueStr);
			return propValue;
		} catch (NumberFormatException nfe) {
			// TODO: Log Error
			System.out.println("Invalid property value found : " + propValueStr + " for : " + configGroupId + ":" + configGroupInstanceId + ":" + propertyKey);
			throw new InvalidPropertyValueException("Invalid property value found : " + propValueStr + " for : " + configGroupId + ":" + configGroupInstanceId + ":" + propertyKey, nfe);
		}
	}

	@Override
	public int getIntProperty(String configGroupId,
			String configGroupInstanceId, String propertyKey, int defaultValue) {

		String propValueStr = getProperty(configGroupId, configGroupInstanceId, propertyKey, String.valueOf(defaultValue));
		try {
			int propValue = Integer.parseInt(propValueStr);
			return propValue;
		} catch (NumberFormatException nfe) {
			// TODO: Log Error
			System.out.println("Invalid property value found : " + propValueStr + " for : " + configGroupId + ":" + configGroupInstanceId + ":" + propertyKey + " returning defualt value " + defaultValue);
		}
		return defaultValue;
	}
}
