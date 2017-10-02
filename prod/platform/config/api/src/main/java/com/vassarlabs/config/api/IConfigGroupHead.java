package com.vassarlabs.config.api;

/**
 * Represents a Configuration group head e.g. Database, Logger etc.
 * 
 * @author pradeep@vassarlabs.com
 *
 */
public interface IConfigGroupHead {
	
	/**
	 * Get config group head id
	 * 
	 * @return
	 */
	public String getConfigGroupHeadId();
	
	/**
	 * Set config group head id
	 * 
	 * @param configGroupHeadId
	 */
	public void setConfigGroupHeadId(String configGroupHeadId);
	
	/**
	 * Returns the config group instance for input config group instance id. 
	 * Returns NULL if not found
	 * 
	 * @param configGroupInstanceId
	 * @return
	 */
	public IConfigGroupInstance getConfigGroupInstance(String configGroupInstanceId);
	
	/**
	 * Sets the config group instance.  If existing, then replaces
	 * 
	 * @param configGroupInstanceId
	 * @param configGroupInstance
	 */
	public void setConfigGroupInstance(String configGroupInstanceId, IConfigGroupInstance configGroupInstance);
}
