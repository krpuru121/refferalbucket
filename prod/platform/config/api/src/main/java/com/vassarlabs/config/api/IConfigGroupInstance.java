package com.vassarlabs.config.api;

import java.util.Properties;

/**
 * This interface represents properties for a config group (instance)
 * e.g. for DB Connection
 * 				- Config Instance Id = 'iot_platform'
 * 				- property, value = <jdbc_url, "jdbc:mysql://localhost/iot_platform"> --> java.util.Properties
 * 
 * @author pradeep@vassarlabs.com
 *
 */
public interface IConfigGroupInstance {

	/**
	 * Get the Configuration group instance Id
	 * @return
	 */
	public String getConfigGroupInstanceId();
	
	/**
	 * Set the Configuration group instance Id
	 * @param configInstanceId
	 */
	public void setConfigGroupInstanceId(String configInstanceId);
	
	/**
	 * Get all Configuration properties belonging to this instance
	 * 
	 * @return
	 */
	public Properties getConfigProperties();
	
	/**
	 * Get all Configuration properties belonging to this instance
	 * 
	 * @param configProperties
	 */
	public void setConfigProperties(Properties configProperties);
	
	/**
	 * Add or update a property to the group
	 * 
	 * @param property
	 * @param value
	 */
	public void addOrUpdateConfigProperty(String property, String value);
	
	/**
	 * Remove a config property from this group
	 * If property not found, will not throw error
	 * 
	 * @param property
	 */
	public void removeConfigProperty(String property);

	/**
	 * Returns true if the property exists, else false
	 * 
	 * @param property
	 * @return
	 */
	public boolean checkConfigPropertyExists(String property);
	
	/**
	 * Set a property in the group.  This is same as addOrUpdateConfigProperty() method
	 * 
	 * @param property
	 * @param value
	 */
	public void setConfigProperty(String property, String value);

	/**
	 * Return property value for input property key.  If not found returns null
	 * 
	 * @param property
	 * @return
	 */
	public String getConfigProperty(String property);
	
	/**
	 * Return property value for input property key, if not found return the input
	 * default value
	 * 
	 * @param property
	 * @param defaultValue
	 * @return
	 */
	public String getConfigProperty(String property, String defaultValue);
}	
