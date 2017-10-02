package com.vassarlabs.common.dsp.err;

import com.vassarlabs.common.utils.err.IErrorObject;

public class DSPPropertyException
	extends DSPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2330461354286006200L;

	public DSPPropertyException() {
		super();
	}

	public DSPPropertyException(IErrorObject errorObject, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public DSPPropertyException(IErrorObject errorObject, String message,
			Throwable cause) {
		super(errorObject, message, cause);
	}

	public DSPPropertyException(IErrorObject errorObject, String message) {
		super(errorObject, message);
	}

	public DSPPropertyException(IErrorObject errorObject, Throwable cause) {
		super(errorObject, cause);
	}

	public DSPPropertyException(IErrorObject errorObject) {
		super(errorObject);
	}

	public DSPPropertyException(String errorMessage) {
		super(errorMessage);
	}
	
	public DSPPropertyException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
