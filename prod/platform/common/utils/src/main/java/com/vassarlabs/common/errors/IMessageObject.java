package com.vassarlabs.common.errors;

import java.util.Map;


public interface IMessageObject extends Cloneable {

	public void setCode(int code) ;
	public int getCode() ;
	public String getMsg() ;
	public void setMsg(String msg) ;
	public Object[] getData() ;
	public void setData(Object[] data) ;
	public MsgType getMsgType() ;
	//public void setMsgType(MsgType msgType) ;
	//public void setMsgType() ;
	public String getCustomMsg();
	public void setCustomMsg(String customMsg);
	public Map<String, Object> getDataMap();
	public void setDataMap(Map<String, Object> dataMap);
}
