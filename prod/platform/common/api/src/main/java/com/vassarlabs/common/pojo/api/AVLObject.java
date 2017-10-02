package com.vassarlabs.common.pojo.api;

public abstract class AVLObject
	implements IVLObject {

	protected long insertTs;
	protected long updateTs;
	protected long userSessionId;
	
	public AVLObject() {
		super();
	}

	protected AVLObject(long insertTs, long updateTs, long userSessionId) {
		super();
		this.insertTs = insertTs;
		this.updateTs = updateTs;
		this.userSessionId = userSessionId;
	}

	@Override
	public long getInsertTs() {
		return insertTs;
	}

	@Override
	public void setInsertTs(long insertTs) {
		this.insertTs = insertTs;
	}

	@Override
	public long getUpdateTs() {
		return updateTs;
	}

	@Override
	public void setUpdateTs(long updateTs) {
		this.updateTs = updateTs;
	}

	@Override
	public long getUserSessionId() {
		return userSessionId;
	}

	@Override
	public void setUserSessionId(long userSessionId) {
		this.userSessionId = userSessionId;
	}

	@Override
	public String toString() {
		return "VLObject [insertTs=" + insertTs + ", updateTs=" + updateTs
				+ ", userSessionId=" + userSessionId
				+ "]";
	}
}
