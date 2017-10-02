package com.vassarlabs.config.err;

import com.vassarlabs.common.utils.err.IErrorObject;
import com.vassarlabs.common.utils.err.VLException;

public class InvalidPropertyValueException
	extends VLException {

	private static final long serialVersionUID = -7051443720552393822L;

	public InvalidPropertyValueException() {
		super();
	}

	public InvalidPropertyValueException(IErrorObject errorObject,
			String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidPropertyValueException(IErrorObject errorObject,
			String message, Throwable cause) {
		super(errorObject, message, cause);
	}

	public InvalidPropertyValueException(IErrorObject errorObject,
			String message) {
		super(errorObject, message);
	}

	public InvalidPropertyValueException(IErrorObject errorObject,
			Throwable cause) {
		super(errorObject, cause);
	}

	public InvalidPropertyValueException(IErrorObject errorObject) {
		super(errorObject);
	}

	public InvalidPropertyValueException(String errorMessage) {
		super(errorMessage);
	}
	
	public InvalidPropertyValueException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
