package com.vassarlabs.fileupload.pojo.api;

import java.util.List;

import com.vassarlabs.common.utils.err.IErrorObject;

public interface IFileUploadResult {

	int getNoOfRecords();
	void setNoOfRecords(int noOfRecords);

	int getNoOfSuccessfulRecords();
	void setNoOfSuccessfulRecords(int noOfSuccessfulRecords);

	int getNoOfErrorRecords();
	void setNoOfErrorRecords(int noOfErrorRecords);

	List<IErrorObject> getErrorList();
	void setErrorList(List<IErrorObject> errorList);

	IFileUploadDetails getFileDetails();
	void setFileDetails(IFileUploadDetails fileDetails);

	int getUploadStatus();
	void setUploadStatus(int uploadStatus);
	
	int getNoOfInsertedRecords();
	void setNoOfInsertedRecords(int noOfInsertedRecords);

	int getNoOfUpdatedRecords();
	void setNoOfUpdatedRecords(int noOfUpdatedRecords);

	
}
