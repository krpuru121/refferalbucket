package com.vassarlabs.config.err;

import com.vassarlabs.common.utils.err.IErrorObject;
import com.vassarlabs.common.utils.err.VLException;

public class PropertyNotFoundException
	extends VLException {

	private static final long serialVersionUID = -1353457014182398795L;

	public PropertyNotFoundException() {
		super();
	}

	public PropertyNotFoundException(IErrorObject errorObject, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public PropertyNotFoundException(IErrorObject errorObject, String message,
			Throwable cause) {
		super(errorObject, message, cause);
	}

	public PropertyNotFoundException(IErrorObject errorObject, String message) {
		super(errorObject, message);
	}

	public PropertyNotFoundException(IErrorObject errorObject, Throwable cause) {
		super(errorObject, cause);
	}

	public PropertyNotFoundException(IErrorObject errorObject) {
		super(errorObject);
	}

	public PropertyNotFoundException(String errorMessage) {
		super(errorMessage);
	}
	
	public PropertyNotFoundException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
