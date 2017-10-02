package com.vassarlabs.common.errors;


public enum MsgType {

	ERROR(4),
	WARNING(3),
	INFO(2),
	INPUT(1);
	
	private final int msgType;

	MsgType(int msgType) {
        this.msgType = msgType;
    }
    
    public int getMsgType() {
        return this.msgType;
    }
    
    public static MsgType getEnum(int value) throws InvalidMsgTypeException{
    	for(MsgType e: MsgType.values()) {
    		if(e.msgType == value) {
    			return e;
    		}
    	}
    	throw new InvalidMsgTypeException("No such message type is available");
    }

}
