package com.vassarlabs.common.utils.err;

/**
 * Interface represents an Error Object to hold the error specific details
 * 
 * @author pradeep
 *
 */
public interface IErrorObject {

	public static int EMPTY_ERROR_CODE = 0;
	public static int BUG_IN_CODE_ERROR_CODE = 1;
	public static int INFO = 2;
	public static int ERROR = 3;
	public static int FILE_UPLOAD_ERROR = 4;
	
	public String getErrorCodeStr();

	public void setErrorCodeStr(String errorCodeStr);

	public int getErrorCodeInt();

	public void setErrorCodeInt(int errorCodeInt);

	public String getErrorMessage();

	public void setErrorMessage(String errorMessage);
}
