package com.vassarlabs.common.dsp.err;

import com.vassarlabs.common.utils.err.IErrorObject;

public class ObjectCreationException
	extends DSPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7714707020859967960L;

	public ObjectCreationException() {
		super();
	}

	public ObjectCreationException(IErrorObject errorObject, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public ObjectCreationException(IErrorObject errorObject, String message,
			Throwable cause) {
		super(errorObject, message, cause);
	}

	public ObjectCreationException(IErrorObject errorObject, String message) {
		super(errorObject, message);
	}

	public ObjectCreationException(IErrorObject errorObject, Throwable cause) {
		super(errorObject, cause);
	}

	public ObjectCreationException(IErrorObject errorObject) {
		super(errorObject);
	}

	public ObjectCreationException(String errorMessage) {
		super(errorMessage);
	}
	
	public ObjectCreationException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
