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
	public static int FILE_UPLOAD_ERROR_CODE = 4;
	public static int EMPTY_FIELD_ERROR_CODE = 5;
	public static int WRONG_FIELD_FORMAT_ERROR_CODE = 6;
	public static int DUPLICATE_FOUND_ERROR_CODE = 7;
	public static int FILE_PROPERTY_IS_MISSING_CODE = 8;
	
	public static String EMPTY_FIELD_ERROR_MESSAGE = "EMPTY_FIELD_ERROR";
	public static String WRONG_FIELD_FORMAT_MESSAGE = "WRONG_FIELD_FORMAT";
	public static String DUPLICATE_FOUND_ERROR_MESSAGE = "DUPLICATE_FOUND_ERROR";
	public static String FILE_UPLOAD_ERROR_MESSAGE = "FILE_UPLOAD_ERROR";
	public static String FILE_PROPERTY_IS_MISSING_MESSAGE = "FILE_PROPERTY_IS_MISSING";
	
	public static String ROW_UPLOADED_SUCCESSFULLY_MESSAGE = "ROW_UPLOADED_SUCCESSFULLY";
	public static String ROW_NOT_UPLOADED_SUCCESSFULLY_MESSAGE = "ROW_NOT_UPLOADED_SUCCESSFULLY";
	
	
	public int getLineNo();

	public void setLineNo(int lineNo);
	
	public String getErrorType();

	public void setErrorType(String errorCodeStr);

	public int getErrorCode();

	public void setErrorCode(int errorCodeInt);

	public String getErrorMessage();

	public void setErrorMessage(String errorMessage);
	
	public String getRowUploadStatus();

	public void setRowUploadStatus(String rowUploadStatus);
	
	public String getRowData();

	public void setRowData(String rowData);
}