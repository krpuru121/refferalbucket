package com.vassarlabs.common.utils.err;

import org.springframework.stereotype.Component;

@Component
public class ErrorObject
	implements IErrorObject {

	public static int NO_ERROR = 0;
	
	protected String errorCodeStr;
	
	protected int errorCodeInt;
	
	protected String errorMessage;

	public ErrorObject() {
		this.errorCodeStr = null;
		this.errorCodeInt = NO_ERROR;
		this.errorMessage = null;
	}
	
	public ErrorObject(String errorCodeStr, int errorCodeInt,
			String errorMessage) {
		super();
		this.errorCodeStr = errorCodeStr;
		this.errorCodeInt = errorCodeInt;
		this.errorMessage = errorMessage;
	}

	public String getErrorCodeStr() {
		return errorCodeStr;
	}

	public void setErrorCodeStr(String errorCodeStr) {
		this.errorCodeStr = errorCodeStr;
	}

	public int getErrorCodeInt() {
		return errorCodeInt;
	}

	public void setErrorCodeInt(int errorCodeInt) {
		this.errorCodeInt = errorCodeInt;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorObject [errorCodeStr=" + errorCodeStr + ", errorCodeInt="
				+ errorCodeInt + ", errorMessage=" + errorMessage + "]";
	}
}
