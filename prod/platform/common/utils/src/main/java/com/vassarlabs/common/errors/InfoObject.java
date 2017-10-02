package com.vassarlabs.common.errors;

import java.util.Arrays;



public class InfoObject extends MessageObject  {

	private static final long serialVersionUID = 8034942653819942283L;

	public InfoObject(int code, String msg, String customMsg){
		this.code = code;
		this.msg = msg;
		this.customMsg = customMsg;
		this.errorCode ="Info_"+code;
		msgType = MsgType.INFO;
	}
	
	@Override
	public MsgType getMsgType() {
		
		return MsgType.INFO;
	}

	@Override
	public String toString() {
		return "InfoObject [code=" + code + ", msg=" + msg + ", customMsg="
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
