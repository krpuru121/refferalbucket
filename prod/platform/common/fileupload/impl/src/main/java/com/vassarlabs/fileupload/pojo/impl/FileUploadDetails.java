package com.vassarlabs.fileupload.pojo.impl;

import java.util.Map;

import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;
import com.vassarlabs.fileupload.utils.UploadType;


public class FileUploadDetails implements IFileUploadDetails{

	protected String fileName;
	protected String fullFilePath;
	protected char quotedChar;
	protected char delimiter;
	protected Long fileUploadTs;
	protected UploadType uploadType;
	protected Long totalNoOfRecords;;
	protected Integer batchSize;
	protected Integer batchNo;
	protected Map<String, String> columnNameMapping;
	
	@Override
	public String getFileName() {
		return this.fileName;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public char getDelimiter() {
		return this.delimiter;
	}

	@Override
	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}

	@Override
	public char getQuotedChar() {
		return this.quotedChar;
	}

	@Override
	public void setQuotedChar(char quotedChar) {
		this.quotedChar = quotedChar;
	}
	
	@Override
	public Long getFileUploadTs() {
		return this.fileUploadTs;
	}

	@Override
	public void setFileUploadTs(Long fileUploadTs) {
		this.fileUploadTs = fileUploadTs;
	}

	@Override
	public Integer getBatchSize() {
		return this.batchSize;
	}

	@Override
	public void setBatchSize(Integer batchSize) {
		this.batchSize = batchSize;
	}
	
	@Override
	public UploadType getUploadType() {
		return this.uploadType;
	}

	@Override
	public void setUploadType(UploadType uploadType) {
		this.uploadType = uploadType;
	}

	@Override
	public String getFileFullPath() {
		return this.fullFilePath;
	}

	@Override
	public void setFileFullPath(String fullFilePath) {
		this.fullFilePath = fullFilePath;
	}

	@Override
	public Long getTotalNoOfRecords() {
		return this.totalNoOfRecords;
	}

	@Override
	public void setTotalNoOfRecords(Long totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
	}
	
	@Override
	public Integer getBatchNo() {
		return this.batchNo;
	}

	@Override
	public void setBatchNo(Integer batchNo) {
		this.batchNo = batchNo;
	}

	@Override
	public Map<String, String> getColumnNameMapping() {
		return this.columnNameMapping;
	}

	@Override
	public void setColumnNameMapping(Map<String, String> columnNameMapping) {
		this.columnNameMapping = columnNameMapping;
	}

}
