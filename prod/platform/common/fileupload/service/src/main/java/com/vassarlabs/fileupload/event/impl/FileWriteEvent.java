package com.vassarlabs.fileupload.event.impl;

import java.util.List;

import com.vassarlabs.common.utils.err.IErrorObject;

public class FileWriteEvent {

	protected String filePath;
	protected String fileName;
	protected List<IErrorObject> errorObjectList;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}                                                                                              
	public List<IErrorObject> getErrorObjectList() {
		return errorObjectList;
	}
	public void setErrorObjectList(List<IErrorObject> errorObjectList) {
		this.errorObjectList = errorObjectList;
	}
	
	
	
	
}
