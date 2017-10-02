package com.vassarlabs.config.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.vassarlabs.config.api.IConfigGroupHead;
import com.vassarlabs.config.api.IConfigGroupInstance;

@Component
public class ConfigGroupHeadImpl
	implements IConfigGroupHead {

	protected String configGroupHeadId;
	protected Map<String, IConfigGroupInstance> allConfigGroupInstance;

	public ConfigGroupHeadImpl() {
		this.allConfigGroupInstance = new HashMap<String, IConfigGroupInstance>();
	}
	
	public ConfigGroupHeadImpl(String configGroupHeadId) {
		this.configGroupHeadId = configGroupHeadId;
		this.allConfigGroupInstance = new HashMap<String, IConfigGroupInstance>();
	}
	
	public ConfigGroupHeadImpl(String configGroupId, Map<String, IConfigGroupInstance> allConfigGroupInstance) {
		this.configGroupHeadId = configGroupId;
		this.allConfigGroupInstance = allConfigGroupInstance;
	}
	
	@Override
	public String getConfigGroupHeadId() {
		return configGroupHeadId;
	}

	@Override
	public void setConfigGroupHeadId(String configGroupHeadId) {
		this.configGroupHeadId = configGroupHeadId;
	}

	@Override
	public IConfigGroupInstance getConfigGroupInstance(
			String configGroupInstanceId) {
		return allConfigGroupInstance.get(configGroupInstanceId);
	}

	@Override
	public void setConfigGroupInstance(String configGroupInstanceId,
			IConfigGroupInstance configGroupInstance) {
		allConfigGroupInstance.put(configGroupInstanceId, configGroupInstance);
	}

	@Override
	public String toString() {
		return "ConfigGroupHeadImpl [configGroupHeadId=" + configGroupHeadId
				+ ", allConfigGroupInstance=" + allConfigGroupInstance + "]";
	}
}
