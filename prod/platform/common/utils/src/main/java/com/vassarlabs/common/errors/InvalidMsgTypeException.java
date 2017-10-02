package com.vassarlabs.common.errors;

import com.vassarlabs.common.utils.err.VLException;

public class InvalidMsgTypeException  extends VLException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9194416130933703815L;

	public InvalidMsgTypeException(){
		super();
	}
	
	public InvalidMsgTypeException(String excMessage){
		super(excMessage);
	}

}
