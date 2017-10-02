package com.vassarlabs.common.pojo.api;

public interface IVLObject {

	public long getInsertTs();
	public void setInsertTs(long insertTs);

	public long getUpdateTs();
	public void setUpdateTs(long updateTs);

	public long getUserSessionId();
	public void setUserSessionId(long userSessionId);
}
