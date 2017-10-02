package com.vassarlabs.config.err;

import com.vassarlabs.common.utils.err.IErrorObject;
import com.vassarlabs.common.utils.err.VLException;

public class InvalidPropertyRequestException
	extends VLException {

	private static final long serialVersionUID = 404030581085163391L;

	public InvalidPropertyRequestException() {
		super();
	}

	public InvalidPropertyRequestException(IErrorObject errorObject,
			String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidPropertyRequestException(IErrorObject errorObject,
			String message, Throwable cause) {
		super(errorObject, message, cause);
	}

	public InvalidPropertyRequestException(IErrorObject errorObject,
			String message) {
		super(errorObject, message);
	}

	public InvalidPropertyRequestException(IErrorObject errorObject,
			Throwable cause) {
		super(errorObject, cause);
	}

	public InvalidPropertyRequestException(IErrorObject errorObject) {
		super(errorObject);
	}

	public InvalidPropertyRequestException(String errorMessage) {
		super(errorMessage);
	}
	
	public InvalidPropertyRequestException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
