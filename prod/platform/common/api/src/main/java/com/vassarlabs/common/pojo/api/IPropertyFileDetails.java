package com.vassarlabs.common.pojo.api;

import java.util.Properties;

public interface IPropertyFileDetails {
	
	public String getFileUUID();
	public void setFileUUID(String fileUUID);
	
	public String getFilePath();
	public void setFilePath(String filePath);
	
	public String getFileName();
	public void setFileName(String fileName);
	
	public Properties getProperties();
	public void setProperties(Properties properties);
	
}
