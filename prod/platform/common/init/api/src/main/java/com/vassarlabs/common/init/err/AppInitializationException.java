package com.vassarlabs.common.init.err;

import com.vassarlabs.common.utils.err.IErrorObject;
import com.vassarlabs.common.utils.err.VLException;

public class AppInitializationException
	extends VLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7179929129859611050L;

	public AppInitializationException() {
		super();
	}

	public AppInitializationException(IErrorObject errorObject, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public AppInitializationException(IErrorObject errorObject, String message,
			Throwable cause) {
		super(errorObject, message, cause);
	}

	public AppInitializationException(IErrorObject errorObject, String message) {
		super(errorObject, message);
	}

	public AppInitializationException(IErrorObject errorObject, Throwable cause) {
		super(errorObject, cause);
	}

	public AppInitializationException(IErrorObject errorObject) {
		super(errorObject);
	}

	public AppInitializationException(String errorMessage) {
		super(errorMessage);
	}
	
	public AppInitializationException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
