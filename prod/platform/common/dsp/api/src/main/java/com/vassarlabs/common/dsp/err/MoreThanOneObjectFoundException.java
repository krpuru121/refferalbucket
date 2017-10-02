package com.vassarlabs.common.dsp.err;

import com.vassarlabs.common.utils.err.IErrorObject;

public class MoreThanOneObjectFoundException
	extends DSPException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -152193814833573453L;

	public MoreThanOneObjectFoundException() {
		super();
	}

	public MoreThanOneObjectFoundException(IErrorObject errorObject, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public MoreThanOneObjectFoundException(IErrorObject errorObject, String message,
			Throwable cause) {
		super(errorObject, message, cause);
	}

	public MoreThanOneObjectFoundException(IErrorObject errorObject, String message) {
		super(errorObject, message);
	}

	public MoreThanOneObjectFoundException(IErrorObject errorObject, Throwable cause) {
		super(errorObject, cause);
	}

	public MoreThanOneObjectFoundException(IErrorObject errorObject) {
		super(errorObject);
	}

	public MoreThanOneObjectFoundException(String errorMessage) {
		super(errorMessage);
	}
	
	public MoreThanOneObjectFoundException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
