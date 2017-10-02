package com.vassarlabs.common.init.err;

import com.vassarlabs.common.utils.err.IErrorObject;
import com.vassarlabs.common.utils.err.VLException;

public class AppComponentInitException
	extends VLException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 227309900839450600L;

	public AppComponentInitException() {
		super();
	}

	public AppComponentInitException(IErrorObject errorObject, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public AppComponentInitException(IErrorObject errorObject, String message,
			Throwable cause) {
		super(errorObject, message, cause);
	}

	public AppComponentInitException(IErrorObject errorObject, String message) {
		super(errorObject, message);
	}

	public AppComponentInitException(IErrorObject errorObject, Throwable cause) {
		super(errorObject, cause);
	}

	public AppComponentInitException(IErrorObject errorObject) {
		super(errorObject);
	}

	public AppComponentInitException(String errorMessage) {
		super(errorMessage);
	}
	
	public AppComponentInitException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
