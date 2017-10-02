package com.vassarlabs.common.errors;

import java.util.Arrays;


public class InputObject extends MessageObject {

	private static final long serialVersionUID = 6702345655014566791L;

	public InputObject(int code, String msg, String customMsg){
		this.code = code;
		this.msg = msg;
		this.customMsg = customMsg;
		this.errorCode ="Input_"+code;
		msgType = MsgType.INPUT;
	}
	
	@Override
	public MsgType getMsgType() {
		
		return MsgType.INPUT;
	}

	@Override
	public String toString() {
		return "InputObject [code=" + code + ", msg=" + msg + ", customMsg="
				+ customMsg + ", data=" + Arrays.toString(data) + ", dataMap="
				+ dataMap + ", msgType=" + msgType + ", errorCode=" + errorCode
				+ "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
}
