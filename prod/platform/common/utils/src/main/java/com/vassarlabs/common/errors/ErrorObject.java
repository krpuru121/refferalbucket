package com.vassarlabs.common.errors;

import java.util.Arrays;


public class ErrorObject extends MessageObject  {

	private static final long serialVersionUID = 809087479174351159L;

	public ErrorObject(int code, String msg, String customMsg){
		this.code = code;
		this.msg = msg;
		this.customMsg = customMsg;
		this.errorCode ="Err_"+code;
		msgType = MsgType.ERROR;
	}

	@Override
	public MsgType getMsgType() {
		
		return MsgType.ERROR;
	}

	@Override
	public String toString() {
		return "ErrorObject [code=" + code + ", msg=" + msg + ", customMsg="
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
