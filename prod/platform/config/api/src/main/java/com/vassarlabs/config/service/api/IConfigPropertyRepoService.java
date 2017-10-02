package com.vassarlabs.config.service.api;

import com.vassarlabs.config.api.IConfigGroupInstance;
import com.vassarlabs.config.err.InvalidPropertyRequestException;
import com.vassarlabs.config.err.InvalidPropertyValueException;
import com.vassarlabs.config.err.PropertyNotFoundException;

/**
 * Interface to get configuration properties from various sources
 * 
 * The properties are organized into groups and in hierarchy structure
 * within the groups. e.g. for database the properties are organized into
 * 
 * 	Database	--> Configuration Group - identified by IConfigGroupHead
 * 		iot_platform database --> Named Configuration Instance - identified by IConfigGroupInstance
 *			property key		--> Property Key
 * 			property value		--> Property value
 * 
 * @author pradeep@vasssarlabs.com
 *
 */
public interface IConfigPropertyRepoService {
	
	/**
	 * Returns instance of {@link IConfigGroupInstance}.
	 * e.g. for configGroupHeadId = DB_ACCESS, and configInstanceId = "iot_platform" returns 
	 * @param configGroupId
	 * @param configGroupInstanceId
	 * @return
	 * @throws InvalidPropertyRequestException
	 */
	public IConfigGroupInstance getConfigPropertyInstance(String configGroupHeadId, String configGroupInstanceId)
		throws InvalidPropertyRequestException;
	
	/**
	 * Returns property value for input. If not found throws {@link PropertyNotFoundException}
	 * 
	 * @param configGroupId
	 * @param configGroupInstanceId
	 * @param propertyKey
	 * @param defaultValue
	 * @return
	 * @throws PropertyNotFoundException
	 */
	public String getProperty(String configGroupId, String configGroupInstanceId, String propertyKey)
		throws InvalidPropertyRequestException, PropertyNotFoundException;
	
	/**
	 * Returns property value for input. If not found returns the default value
	 * 
	 * @param configGroupId
	 * @param configInstanceId
	 * @param propertyKey
	 * @param defaultValue
	 * @return
	 */
	public String getProperty(String configGroupId, String configGroupInstanceId, String propertyKey, String defaultValue);
	
	/**
	 * Returns int value of a property. If not found throws {@link PropertyNotFoundException}.
	 * If the property has invalid value throws {@link InvalidPropertyValueException}
	 * 
	 * @param configGroupId
	 * @param configGroupInstanceId
	 * @param propertyKey
	 * @return
	 * @throws InvalidPropertyValueException
	 * @throws InvalidPropertyRequestException
	 * @throws PropertyNotFoundException
	 */
	public int getIntProperty(String configGroupId, String configGroupInstanceId, String propertyKey)
		throws InvalidPropertyValueException, InvalidPropertyRequestException, PropertyNotFoundException;
	
	/**
	 * Returns int value of a property. If not found throws {@link PropertyNotFoundException}.
	 * If the property has invalid value logs error and returns default value
	 * 
	 * @param configGroupId
	 * @param configGroupInstanceId
	 * @param propertyKey
	 * @param defaultValue
	 * @return
	 */
	public int getIntProperty(String configGroupId, String configGroupInstanceId, String propertyKey, int defaultValue);
}
