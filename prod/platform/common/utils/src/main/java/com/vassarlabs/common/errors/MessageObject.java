package com.vassarlabs.common.errors;

import java.io.Serializable;
import java.util.Map;

public abstract class MessageObject implements IMessageObject, Serializable, Cloneable {

	private static final long serialVersionUID = -3853770404137629920L;
	
	protected int code;
	protected String msg;
	protected String customMsg;
	protected Object[] data;
	protected Map<String, Object> dataMap;
	protected MsgType msgType;
	
	protected String errorCode;
	
	@Override
	public int getCode() {
		return code;
	}
	
	@Override
	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public String getMsg() {
		return msg;
	}
	
	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public Object[] getData() {
		return data;
	}
	
	@Override
	public void setData(Object[] data) {
		this.data = data;
	}
	
	@Override
	public abstract MsgType getMsgType();
	
	
	@Override
	public String getCustomMsg() {
		return customMsg;
	}

	@Override
	public void setCustomMsg(String customMsg) {
		this.customMsg = customMsg;
	}
	
	@Override
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	@Override
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
