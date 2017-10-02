package com.vassarlabs.config.api;

import com.vassarlabs.config.err.InvalidPropertyRequestException;

/**
 * Configuration repository - maintains map of Configuration groups and the property values
 * 
 * @author pradeep@vassarlabs.com
 *
 */
public interface IConfigRepository {

	/**
	 * Get the Config group instance for input group head id and instance id
	 * Returns null if instance does not exist
	 * 
	 * @param configGroupHeadId
	 * @param configGroupInstanceId
	 * @return
	 * @throws InvalidPropertyRequestException 
	 */
	public IConfigGroupInstance getConfigGroupInstance(String configGroupHeadId, String configGroupInstanceId) throws InvalidPropertyRequestException;
	
	/**
	 * Sets the Config group instance for input head id
	 * Replaces/updates if the config group instance exists
	 *  
	 *  
	 * @param configGroupHeadId
	 * @param configuGroupInstance
	 */
	public void setConfigGroupInstance(String configGroupHeadId, IConfigGroupInstance configuGroupInstance);
}
