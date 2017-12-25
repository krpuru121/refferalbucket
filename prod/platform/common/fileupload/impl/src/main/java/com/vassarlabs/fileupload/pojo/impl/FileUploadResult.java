package com.vassarlabs.fileupload.pojo.impl;

import java.util.List;

import com.vassarlabs.common.utils.err.IErrorObject;
import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;
import com.vassarlabs.fileupload.pojo.api.IFileUploadResult;

public class FileUploadResult implements IFileUploadResult{

	protected int noOfRecords;
	protected int noOfSuccessfulRecords;
	protected int noOfErrorRecords;
	protected int uploadStatus;
	protected int noOfInsertedRecords;
	protected int noOfUpdatedRecords;
	protected List<IErrorObject> errorList;
	protected IFileUploadDetails fileDetails;
	
	@Override
	public int getNoOfInsertedRecords() {
		return noOfInsertedRecords;
	}
	@Override
	public void setNoOfInsertedRecords(int noOfInsertedRecords) {
		this.noOfInsertedRecords = noOfInsertedRecords;
	}
	@Override
	public int getNoOfUpdatedRecords() {
		return noOfUpdatedRecords;
	}
	@Override
	public void setNoOfUpdatedRecords(int noOfUpdatedRecords) {
		this.noOfUpdatedRecords = noOfUpdatedRecords;
	}
	@Override
	public int getNoOfRecords() {
		return noOfRecords;
	}
	@Override
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	
	@Override
	public int getNoOfSuccessfulRecords() {
		return noOfSuccessfulRecords;
	}
	@Override
	public void setNoOfSuccessfulRecords(int noOfSuccessfulRecords) {
		this.noOfSuccessfulRecords = noOfSuccessfulRecords;
	}
	
	@Override
	public int getNoOfErrorRecords() {
		return noOfErrorRecords;
	}
	@Override
	public void setNoOfErrorRecords(int noOfErrorRecords) {
		this.noOfErrorRecords = noOfErrorRecords;
	}

	@Override
	public List<IErrorObject> getErrorList() {
		return this.errorList;
	}
	@Override
	public void setErrorList(List<IErrorObject> errorList) {
		this.errorList = errorList;
	}

	@Override
	public IFileUploadDetails getFileDetails() {
		return fileDetails;
	}
	@Override
	public void setFileDetails(IFileUploadDetails fileDetails) {
		this.fileDetails = fileDetails;
	}
	
	@Override
	public int getUploadStatus() {
		return uploadStatus;
	}
	@Override
	public void setUploadStatus(int uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	
}
