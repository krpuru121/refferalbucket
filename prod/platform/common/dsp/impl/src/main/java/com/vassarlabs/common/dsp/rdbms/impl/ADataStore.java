package com.vassarlabs.common.dsp.rdbms.impl;

import com.vassarlabs.common.dsp.api.IDataStore;

public abstract class ADataStore
	implements IDataStore {

	protected String dataStoreName;
	
	@Override
	public String getDataStoreName() {
		return this.dataStoreName;
	}

	@Override
	public void setDataStoreName(String dataStoreName) {
		this.dataStoreName = dataStoreName;
	}

}
