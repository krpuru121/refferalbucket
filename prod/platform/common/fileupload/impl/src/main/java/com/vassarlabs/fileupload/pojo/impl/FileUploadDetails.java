package com.vassarlabs.fileupload.pojo.impl;

import java.util.Map;
import java.util.Properties;

import com.vassarlabs.fileupload.pojo.api.IFileUploadDetails;

public class FileUploadDetails implements IFileUploadDetails{

	protected String fileName;
	protected String fullFilePath;
	protected char quotedChar;
	protected char delimiter;
	protected Long fileUploadTs;
	protected Integer batchSize;
	protected Properties properties;
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
	public String getFileFullPath() {
		return this.fullFilePath;
	}

	@Override
	public void setFileFullPath(String fullFilePath) {
		this.fullFilePath = fullFilePath;
	}
	
	@Override
	public Properties getProperties() {
		return this.properties;
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	@Override
	public Map<String, String> getColumnNameMapping() {
		return this.columnNameMapping;
	}

	@Override
	public void setColumnNameMapping(Map<String, String> columnNameMapping) {
		this.columnNameMapping = columnNameMapping;
	}

	@Override
	public String toString() {
		return "FileUploadDetails [fileName=" + fileName + ", fullFilePath="
				+ fullFilePath + ", delimiter="
				+ delimiter + ", fileUploadTs=" + fileUploadTs
				+ ", batchSize=" + batchSize + ", properties=" + properties
				+ ", columnNameMapping=" + columnNameMapping + ", quotedChar=" + quotedChar + "]";
	}

}
