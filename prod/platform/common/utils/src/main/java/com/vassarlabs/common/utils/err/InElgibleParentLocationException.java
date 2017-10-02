package com.vassarlabs.common.utils.err;

public class InElgibleParentLocationException
	extends VLException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3889914400461206582L;

	public InElgibleParentLocationException() {
		super();
	}

	public InElgibleParentLocationException(IErrorObject errorObject, String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(errorObject, message, cause, enableSuppression, writableStackTrace);
	}

	public InElgibleParentLocationException(IErrorObject errorObject, String message,
			Throwable cause) {
		super(errorObject, message, cause);
	}

	public InElgibleParentLocationException(IErrorObject errorObject, String message) {
		super(errorObject, message);
	}

	public InElgibleParentLocationException(IErrorObject errorObject, Throwable cause) {
		super(errorObject, cause);
	}

	public InElgibleParentLocationException(IErrorObject errorObject) {
		super(errorObject);
	}

	public InElgibleParentLocationException(String errorMessage) {
		super(errorMessage);
	}
	
	public InElgibleParentLocationException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
