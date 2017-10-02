package com.vassarlabs.common.errors;

import java.util.Arrays;



public class WarningObject extends MessageObject {


	private static final long serialVersionUID = 4356641433808461650L;
	
	public WarningObject(int code, String msg, String customMsg){
		this.code = code;
		this.msg = msg;
		this.customMsg = customMsg;
		this.errorCode ="Warn_"+code;
		msgType = MsgType.WARNING;
	}
	@Override
	public MsgType getMsgType() {
		
		return MsgType.WARNING;
	}
	@Override
	public String toString() {
		return "WarningObject [code=" + code + ", msg=" + msg + ", customMsg="
				+ customMsg + ", data=" + Arrays.toString(data) + ", dataMap="
				+ dataMap + ", msgType=" + msgType.name() + ", errorCode=" + errorCode
				+ "]";
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
}
