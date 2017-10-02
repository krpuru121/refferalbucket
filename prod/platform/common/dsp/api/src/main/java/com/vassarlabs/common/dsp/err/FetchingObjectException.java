package com.vassarlabs.common.dsp.err;

import com.vassarlabs.common.utils.err.IErrorObject;

public class FetchingObjectException
	extends DSPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6895736044953412995L;

	public FetchingObjectException() {
		super();
	}

	public FetchingObjectException(IErrorObject errorObject, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public FetchingObjectException(IErrorObject errorObject, String message,
			Throwable cause) {
		super(errorObject, message, cause);
	}

	public FetchingObjectException(IErrorObject errorObject, String message) {
		super(errorObject, message);
	}

	public FetchingObjectException(IErrorObject errorObject, Throwable cause) {
		super(errorObject, cause);
	}

	public FetchingObjectException(IErrorObject errorObject) {
		super(errorObject);
	}

	public FetchingObjectException(String errorMessage) {
		super(errorMessage);
	}
	
	public FetchingObjectException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
