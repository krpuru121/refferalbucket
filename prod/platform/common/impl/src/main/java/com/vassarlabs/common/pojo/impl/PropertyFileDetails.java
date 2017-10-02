package com.vassarlabs.common.pojo.impl;

import java.util.Properties;

import com.vassarlabs.common.pojo.api.IPropertyFileDetails;

public class PropertyFileDetails implements IPropertyFileDetails{

	protected String fileUUID;
	protected String filePath;
	protected String fileName;
	protected Properties properties;
	
	@Override
	public String getFileUUID() {
		return this.fileUUID;
	}

	@Override
	public void setFileUUID(String fileUUID) {
		this.fileUUID = fileUUID;
	}

	@Override
	public String getFilePath() {
		return this.filePath;
	}

	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String getFileName() {
		return this.fileName;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String toString() {
		return "PropertyFileDetails [fileUUID=" + fileUUID + ", filePath="
				+ filePath + ", fileName=" + fileName + ", properties="
				+ properties + "]";
	}

}
